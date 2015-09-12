import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

/**
 * Class that provides JUnit tests for Gitlet, as well as a couple of utility
 * methods.
 * 
 * @author Joseph Moghadam
 * 
 *         Some code adapted from StackOverflow:
 * 
 *         http://stackoverflow.com/questions
 *         /779519/delete-files-recursively-in-java
 * 
 *         http://stackoverflow.com/questions/326390/how-to-create-a-java-string
 *         -from-the-contents-of-a-file
 * 
 *         http://stackoverflow.com/questions/1119385/junit-test-for-system-out-
 *         println
 * 
 */
public class GitletPublicTest {
    private static final String GITLET_DIR = "gitlet/";
    private static final String TESTING_DIR = "test_files/";

    /* matches either unix/mac or windows line separators */
    private static final String LINE_SEPARATOR = "\r\n|[\r\n]";

    /**
     * Deletes existing gitlet system, resets the folder that stores files used
     * in testing.
     * 
     * This method runs before every @Test method. This is important to enforce
     * that all tests are independent and do not interact with one another.
     */
    @Before
    public void setUp() {
        File f = new File(GITLET_DIR);
        if (f.exists()) {
            recursiveDelete(f);
        }
        f = new File(TESTING_DIR);
        if (f.exists()) {
            recursiveDelete(f);
        }
        f.mkdirs();
    }

    /**
     * Tests that init creates a .gitlet directory. Does NOT test that init
     * creates an initial commit, which is the other functionality of init.
     */
    //@Test
    public void testBasicInitialize() {
        gitlet("init");
        File f = new File(GITLET_DIR);
        assertTrue(f.exists());
    }

    /**
     * Tests that checking out a file name will restore the version of the file
     * from the previous commit. Involves init, add, commit, and checkout.
     */
    //@Test
    public void testBasicCheckout() {
        String wugFileName = TESTING_DIR + "wug.txt";
        String wugText = "This is a wug.";
        createFile(wugFileName, wugText);
        gitlet("init");
        gitlet("add", wugFileName);
        gitlet("commit", "added wug");
        writeFile(wugFileName, "This is not a wug.");
        gitlet("checkout", wugFileName);
        assertEquals(wugText, getText(wugFileName));
    }

    /**
     * Tests that log prints out commit messages in the right order. Involves
     * init, add, commit, and log.
     */
    //@Test
    public void testBasicLog() {
        gitlet("init");
        String commitMessage1 = "initial commit";

        String wugFileName = TESTING_DIR + "wug.txt";
        String wugText = "This is a wug.";
        createFile(wugFileName, wugText);
        gitlet("add", wugFileName);
        String commitMessage2 = "added wug";
        gitlet("commit", commitMessage2);

        String logContent = gitlet("log");
        assertArrayEquals(new String[] { commitMessage2, commitMessage1 },
                extractCommitMessages(logContent));
    }

    //@Test 
    public void testBranch(){
        String commitMessage1 = "initial commit";
        String commitMessage2 = "fan master";
        String commitMessage3 = "wug cool";
        String commitMessage4 = "fantasy";

        String fanFileName = "fan.txt";
        String wugFileName = "wug.txt";
        String fantasyFileName = "fantasy.txt"; 

        String fanText = "fan";
        String wugText = "wug";
        String fantasyText = "fantasy";

        createFile(fanFileName,fanText);
        createFile(wugFileName,wugText);
        createFile(fantasyFileName,fantasyText);

        gitlet("init");

        gitlet("add",fanFileName);
        gitlet("commit",commitMessage2);

        String logContent = gitlet("log");
        assertArrayEquals(new String[] { commitMessage2, commitMessage1 },
                extractCommitMessages(logContent));

        gitlet("branch","cool");
        gitlet("checkout","cool");

        gitlet("add",wugFileName);
        gitlet("commit",commitMessage3);

        logContent = gitlet("log");
        assertArrayEquals(new String[] { commitMessage3, commitMessage2, commitMessage1 },
                extractCommitMessages(logContent));

        gitlet("checkout","master");

        logContent = gitlet("log");
        assertArrayEquals(new String[] {commitMessage2, commitMessage1 },
                extractCommitMessages(logContent));

        gitlet("add",fantasyFileName);
        gitlet("commit",commitMessage4);

        logContent = gitlet("log");
        assertArrayEquals(new String[] {commitMessage4,commitMessage2, commitMessage1 },
                extractCommitMessages(logContent));

        gitlet("checkout","cool");


        logContent = gitlet("log");
        assertArrayEquals(new String[] { commitMessage3, commitMessage2, commitMessage1 },
                extractCommitMessages(logContent));
    }

    //@Test 
    public void merge(){
        String commitMessage1 = "initial commit";
        String commitMessage2 = "fan master";
        String commitMessage3 = "wug cool";
        String commitMessage4 = "fantasy";

        String A = "A.txt";
        String B = "B.txt";
        String C = "C.txt"; 

        String AText = "fan";
        String BText = "wug";
        String CText = "fantasy";

        createFile(A,AText);
        createFile(B,BText);
        createFile(C,CText);

        gitlet("init");

        gitlet("add",A);
        gitlet("commit",commitMessage1);

        gitlet("branch","fan");
        gitlet("checkout","fan");

        gitlet("add",A);
        gitlet("add",B);
        gitlet("commit",commitMessage2);

        gitlet("checkout","master");

        gitlet("add",A);
        gitlet("add",C);
        gitlet("commit",commitMessage3);
        

        String logContent = gitlet("log");
        assertArrayEquals(new String[] { commitMessage3,commitMessage1 },
                extractCommitMessages(logContent));
        //gitlet("merge","fan");


        logContent = gitlet("log");
        assertArrayEquals(new String[] { commitMessage3, commitMessage2, commitMessage1 },
                extractCommitMessages(logContent));

    }

    @Test
    public void testRebase(){
        String commitMessage0 = "initial commit";
        String commitMessage1 = "added wugText1";
        String commitMessage2 = "added wugText2";
        String commitMessage3 = "added wugText3";
        String commitMessage4 = "added wugText4";
        String commitMessage5 = "added wugText5";
        String commitMessage6 = "added wugText6";

        String wugFileName = TESTING_DIR + "wug.txt";

        String wugText1 = "This is a wg";
        String wugText2 = "This is a wug";
        String wugText3 = "This is wug.";
        String wugText4 = "This is wug...";
        String wugText5 = "This is wug!";
        String wugText6 = "This is wug!!!";

        createFile(wugFileName, wugText1);

        gitlet("init");

        gitlet("add", wugFileName);
        gitlet("commit", commitMessage1);

        writeFile(wugFileName,wugText2);

        gitlet("add", wugFileName);
        gitlet("commit", commitMessage2);

        gitlet("branch","cool");
        gitlet("checkout","cool");

        writeFile(wugFileName,wugText3);

        gitlet("add", wugFileName);
        gitlet("commit", commitMessage3);

        writeFile(wugFileName,wugText4);

        gitlet("add", wugFileName);
        gitlet("commit", commitMessage4);

        /*String logContent = gitlet("log");
        assertArrayEquals(new String[] { commitMessage4, commitMessage3, commitMessage2,commitMessage1,commitMessage0},
                extractCommitMessages(logContent));
        */
        gitlet("checkout","master");

        writeFile(wugFileName, wugText5);

        gitlet("add", wugFileName);
        gitlet("commit", commitMessage5);

        writeFile(wugFileName, wugText6);

        gitlet("add", wugFileName);
        gitlet("commit", commitMessage6);

        /*logContent = gitlet("log");
        assertArrayEquals(new String[] { commitMessage6, commitMessage5, commitMessage2,commitMessage1,commitMessage0 },
                extractCommitMessages(logContent));
                */
        
        gitlet("checkout","cool");
        gitlet("rebase","master");

        gitlet("checkout","master");

        String logContent = gitlet("log");
        assertArrayEquals(new String[] { commitMessage4, commitMessage3, commitMessage6, commitMessage5,commitMessage2,commitMessage1,commitMessage0 },
                extractCommitMessages(logContent));
     
    }
    /**
     * Convenience method for calling Gitlet's main. Anything that is printed
     * out during this call to main will NOT actually be printed out, but will
     * instead be returned as a string from this method.
     * 
     * Prepares a 'yes' answer on System.in so as to automatically pass through
     * dangerous commands.
     * 
     * The '...' syntax allows you to pass in an arbitrary number of String
     * arguments, which are packaged into a String[].
     */
    private static String gitlet(String... args) {
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;
        ByteArrayOutputStream printingResults = new ByteArrayOutputStream();
        try {
            /*
             * Below we change System.out, so that when you call
             * System.out.println(), it won't print to the screen, but will
             * instead be added to the printingResults object.
             */
            System.setOut(new PrintStream(printingResults));

            /*
             * Prepares the answer "yes" on System.In, to pretend as if a user
             * will type "yes". You won't be able to take user input during this
             * time.
             */
            String answer = "yes";
            InputStream is = new ByteArrayInputStream(answer.getBytes());
            System.setIn(is);

            /* Calls the main method using the input arguments. */
            Gitlet.main(args);

        } finally {
            /*
             * Restores System.out and System.in (So you can print normally and
             * take user input normally again).
             */
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
        return printingResults.toString();
    }

    /**
     * Returns the text from a standard text file (won't work with special
     * characters).
     */
    private static String getText(String fileName) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(fileName));
            return new String(encoded, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * Creates a new file with the given fileName and gives it the text
     * fileText.
     */
    private static void createFile(String fileName, String fileText) {
        File f = new File(fileName);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeFile(fileName, fileText);
    }

    /**
     * Replaces all text in the existing file with the given text.
     */
    private static void writeFile(String fileName, String fileText) {
        FileWriter fw = null;
        try {
            File f = new File(fileName);
            fw = new FileWriter(f, false);
            fw.write(fileText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Deletes the file and all files inside it, if it is a directory.
     */
    private static void recursiveDelete(File d) {
        if (d.isDirectory()) {
            for (File f : d.listFiles()) {
                recursiveDelete(f);
            }
        }
        d.delete();
    }

    /**
     * Returns an array of commit messages associated with what log has printed
     * out.
     */
    private static String[] extractCommitMessages(String logOutput) {
        String[] logChunks = logOutput.split("====");
        int numMessages = logChunks.length - 1;
        String[] messages = new String[numMessages];
        for (int i = 0; i < numMessages; i++) {
            System.out.println(logChunks[i + 1]);
            String[] logLines = logChunks[i + 1].split(LINE_SEPARATOR);
            messages[i] = logLines[3];
        }
        return messages;
    }

    public static void main(String[] args) {
         jh61b.junit.textui.runClasses(GitletPublicTest.class);
    } 
}