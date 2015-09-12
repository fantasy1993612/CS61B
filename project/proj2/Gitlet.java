import java.io.*;
import java.nio.file.*;
import java.util.*; 
import java.text.*;
import java.util.Date;

public class Gitlet implements Serializable{

	
	private int lastCommitedId;
	private static String GitPath = "gitlet";
	private String branchName;
	private Map<String,Branch> branchToCommit;
	private Map<Integer,Branch> gitBranch;
	private List<String> addList;
	private List<String> removeFiles;
	private List<Integer> rebaseBranch;
 	
	public Gitlet(){
		lastCommitedId = -1;
		branchName = "master";
		branchToCommit = new HashMap<String,Branch>();
		addList = new LinkedList<String>();
		gitBranch = new HashMap<Integer,Branch>();
		removeFiles = new LinkedList<String>();
		rebaseBranch = new LinkedList<Integer>();

	}

	/*
	Usage: java Gitlet init
	Creates a new gitlet version control system in the current directory. 
	This system will automatically start with one commit: a commit that contains no files 
	and has the commit message initial commit.
	RunTime: O(1)
	*/
	public void init(){
		File gitFile = new File(GitPath);
		if(gitFile.exists()){
			System.out.println("A gitlet version control system already exists in the current directory.");
		}else{
			gitFile.mkdir();
		}

		int curCommitId = lastCommitedId + 1;
		File initial = new File(GitPath+"/"+curCommitId);
	    initial.mkdir();
		String curDate = getTime();
		Branch initialCommit = new Branch(curCommitId,lastCommitedId,"initial commit",curDate);
	    lastCommitedId = curCommitId;
	    branchToCommit.put(branchName,initialCommit);
	    gitBranch.put(lastCommitedId,initialCommit);
	}

	/*
	 Usage: java Gitlet add [file name]
	 Indicates you want the file to be included in the upcoming commit as having been changed.
	 Adding a file is also called staging the file. If the file had been marked for removal, 
	 instead just unmark it.
	 RunTime: O(N)
	*/
	public void add(String fileName){

		if(fileName.equals("")){
			System.out.println("Please input correct file name");
			return;
		}

		File stagedFile = new File(fileName);
		if(!stagedFile.exists()){
			System.out.println("file does not exists");
		}

		if(addList.contains(fileName)){
			System.out.println("This file has already been added");
            return;
		}

		for(String removal : removeFiles){
			if(removal.equals(fileName)){
				removeFiles.remove(removal);
			}
		}
		//if the file exists,compare the content,if have the same content do nothing
		//else add the staged file
		
		if(gitBranch.get(lastCommitedId).containsFile(fileName)){
			String commitedFileName = gitBranch.get(lastCommitedId).getFilePath(fileName);
			try{
				Path path1 = Paths.get(commitedFileName);//!!!
				Path path2 = Paths.get(fileName); 
				if(isSameContent(path1,path2)){
					System.out.println("This file hasn't been changed since last commit");
					return;
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		
		addList.add(fileName);

	} 

	/*
	 Saves a snapshot of certain files that can be viewed or restored at a later time.
	 The files in a commit's snapshot come from two sources: files that were newly added 
	 to this commit (staged prior to the commit), and files that were inherited from the previous commit.
	 We'll refer to these two groups of files as "the commit's added files" and "the commit's old files" 
	 respectively. In general, a new commit inherits all of the files in the previous commit as its old files
	 (both the previous commit's added and old files). However, don't inherit files that were added to the new 
	 commit, becuase the added file takes precedent over the old one. Remember that adding a file indicates you 
	 want to save a new version of the file, so if you added the file it means that you don't need the old version 
	 anymore.
	 RunTime: O(N)
	*/
	 public void commit(String message){
	 	
	 	if(message.equals("null")){
	 		System.out.println("Please enter a commit message");
	 	}else if(addList.size() == 0){
	 		System.out.println("There are no file to commit");
	 		return;
	 	}

	    int curCommitedId = lastCommitedId + 1;

	 	makeDir(GitPath + "/" + curCommitedId);
	 	String curDate = getTime();
	 	int branchCommitId = branchToCommit.get(branchName).getCurCommitedId();
	 	Branch curCommit;
	 	Map<String,String> lastCommitedFiles;

	 	//if you switch branch get the last commit version
	 	if(lastCommitedId != branchCommitId){
	 		 curCommit = new Branch(curCommitedId,branchCommitId,message,curDate);
	 		 lastCommitedFiles = gitBranch.get(branchCommitId).getAllCommitedFiles();
	 	}else{
	 		 curCommit = new Branch(curCommitedId,lastCommitedId,message,curDate);
	 		 lastCommitedFiles = gitBranch.get(lastCommitedId).getAllCommitedFiles();
	 	}

	 	//get the last version files
	 	for(String filename : lastCommitedFiles.keySet()){
	 		if(!addList.contains(filename)){
	 			curCommit.addFile(filename,lastCommitedFiles.get(filename));
	 		}
	 	}
		
	 	for(String stagedFile : addList){
	 		String[] s = stagedFile.split("\\/");
	 		curCommit.addFile(s[s.length-1],GitPath+"/"+curCommitedId+"/"+s[s.length-1]);
	 		File sourceFile = new File(stagedFile);
            File targetFile = new File(GitPath + "/"+curCommitedId + "/" + s[s.length-1]);
            try {
                Files.copy(sourceFile.toPath(), targetFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.out.println("There occurs an IOEception when commiting files");
            }
	 	}

	 	gitBranch.put(curCommitedId,curCommit);
	 	branchToCommit.put(branchName,curCommit);
	 	lastCommitedId = curCommitedId;
	 	addList = new LinkedList<String>();//ready to make a new version 
	}

	 /*remove file on stage*/
	public void rm(String fileName){	
		for(int i = 0;i < addList.size();i++){
			if(addList.get(i).equals(fileName)){
				removeFiles.add(fileName);
				addList.remove(i);
			}
		}
	}

	/*log*/
	public String log(){
		//int commitedId = lastCommitedId;
		StringBuilder logString = new StringBuilder();
		int commitedId  = branchToCommit.get(branchName).getCurCommitedId();
		while(commitedId >= 0){
			logString.append("====");
			logString.append("\n");
			logString.append("Commit ");
			logString.append(commitedId);
			logString.append("\n");
			logString.append(gitBranch.get(commitedId).getCommitDate());
			logString.append("\n");
			logString.append(gitBranch.get(commitedId).getCommitMessage());
			logString.append("\n");
			logString.append("\n");
			commitedId = gitBranch.get(commitedId).getLastCommitedId();
		}

		return logString.toString();
	}

	/*log*/
	public String globalLog(){
		int commitedId = lastCommitedId;
		StringBuilder logString = new StringBuilder();
		
		while(commitedId >= 0){
			logString.append("====");
			logString.append("\n");
			logString.append("Commit");
			logString.append(commitedId);
			logString.append("\n");
			logString.append(gitBranch.get(commitedId).getCommitDate());
			logString.append("\n");
			logString.append(gitBranch.get(commitedId).getCommitMessage());
			logString.append("\n");
			logString.append("\n");
			commitedId--;
		}

		return logString.toString();
		
	}

	/*print out the id when gives the commit message*/
	public void find(String message){
		int curCommitId = lastCommitedId;
        boolean hasFind = false;
        while (curCommitId != -1) {
            if (gitBranch.get(curCommitId).getCommitMessage().equals(message)) {
                System.out.println(curCommitId);
                hasFind = true;
            }
            curCommitId = curCommitId - 1;
        }
        if (!hasFind) {
            System.out.println("Found no commit with that message");
        }
	}

	
	public void checkout(String fileName){
		String[] s = fileName.split("\\/");
		String path = branchToCommit.get(branchName).getFilePath(s[s.length-1]);
		File target = new File(fileName);
	 	File source = new File(path);
	 	try{
		 	Files.copy(source.toPath(),target.toPath(),StandardCopyOption.REPLACE_EXISTING);
		 }catch(IOException e){
		 	System.out.println("There occurs an IOEception when commiting files");
		 }
	}

	/*2.Restores all files in the working directory to their versions 
	in the commit at the head of the given branch. Considers the given 
	branch to now be the current branch. */
	public void checkBranch(String branchname){
		
		branchName = branchname;

	}

	/*branch */
	public void branch(String branchname){
		if(branchname.equals("") || branchname == null){
			System.out.println("enter the branch name");
		}

		for(String branch : branchToCommit.keySet()){
			if(branch.equals(branchname)){
				System.out.println("A branch with that name already exists.");
            	return;
			}
		}
		branchToCommit.put(branchname,branchToCommit.get(branchName));	
	}

	/*Displays what branches currently exist, 
      and marks the current branch with a *. 
      Also displays what files have been staged or marked for removal. 
      An example of the exact format it should follow is as follows.*/
      /*
	   === Branches ===
		*master
		other-branch

		=== Staged Files ===
		wug.txt
		some_folder/wugs.txt

		=== Files Marked for Removal ===
		goodbye.txt
      */
      public void status(){
      	System.out.println("=== Branches ===");
      	

      	for(String branchname : branchToCommit.keySet()){
      		if(branchname == branchName){
      			System.out.println("*"+branchname);
      			continue;
      		}
      		System.out.println(branchname);
      	}
      	System.out.print("\n");
      	System.out.println("=== Staged Files ===");

      	for(String stagedFile : addList){
      		System.out.println(stagedFile);
      	}
      	System.out.print("\n");
      	System.out.println("=== Files Marked for Removal ===");

      	for(String removal : removeFiles){
      		System.out.println(removal);
      	}

      	System.out.print("\n");
      }

      public void rebase(String branchname){

      	int branchNameId1 = branchToCommit.get(branchname).getCurCommitedId();
      	int branchNameId2 = branchToCommit.get(branchName).getCurCommitedId();
     

      	rebaseBranch.add(branchNameId2);
      	checkBranch(branchname);

      	while(branchNameId1 >= 0 && branchNameId2 >= 0){
      		if(gitBranch.get(branchNameId1).getLastCommitedId() == gitBranch.get(branchNameId2).getLastCommitedId()){

      			for(int i = rebaseBranch.size() -1 ; i >= 0 ; i--){
      				Map<String,String> commitedFiles = new HashMap<String,String>();

      				commitedFiles = gitBranch.get(rebaseBranch.get(i)).getAllCommitedFiles();
      				String commitMessage = gitBranch.get(rebaseBranch.get(i)).getCommitMessage();

      				for(String file : commitedFiles.keySet()){
	      				addList.add(file);
	      			}

	      			commit(commitMessage);
      			}
      			break;
      		}

      		branchNameId1 = gitBranch.get(branchNameId1).getLastCommitedId();
      		branchNameId2 = gitBranch.get(branchNameId2).getLastCommitedId();
      		rebaseBranch.add(branchNameId2);
      	}

      	checkBranch(branchName);
      	

      }

      /*
      public merge(String branchname){
		int curCommitId = lastCommitedId + 1;
		Map<String,String> branchCommitFiles = new HashMap<String,String>();
		Map<String,String> masterCommitFiles = new HashMap<String,String>();

		branchCommitFiles = branchToCommit.get(branchname).getAllCommitedFiles();
		masterCommitFiles = branchToCommit.get("master").getAllCommitedFiles();

		for(String file : branchCommitFiles){
			add(file);
		}

		for(String file : masterCommitFiles){
			add(file);
		}

		commit("merge " + branchname);

	  }
	*/
	/*save all the information with object*/
	public static void saveGit(Gitlet saveGit){
		try{
			File saveFile = new File(GitPath+"/FantasyGit.ser");
			FileOutputStream fileStream = new FileOutputStream(saveFile);
			ObjectOutputStream  os = new ObjectOutputStream(fileStream);
			os.writeObject(saveGit);
			os.close();
			fileStream.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	/*read all the information with object*/
	public static Gitlet readGit(){

		Gitlet readGit = null;
		if(new File(GitPath+"/FantasyGit.ser").exists()){
			try{	
				ObjectInputStream is = new ObjectInputStream(new FileInputStream(GitPath+"/FantasyGit.ser"));
				readGit = (Gitlet) is.readObject();
				is.close();
			}catch(IOException | ClassNotFoundException e){
				System.out.println("Could not read previous gitlet state!");
                System.out.println(e);
                System.exit(1);
			}
		}
		
		return readGit;
	}

	
	/*make directory*/
	private void makeDir(String fileName){
		File gitFileName = new File(fileName);
		if(!gitFileName.exists()){
			gitFileName.mkdir();
		}
	}

	/*get current time*/
	public String getTime(){
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	/*compare whether two files store the same content*/
	public boolean isSameContent(Path file1,Path file2) throws IOException{
		long size = Files.size(file1);

		if(size != Files.size(file2)){
			return false;
		}

		if (size < 4096){
        	return Arrays.equals(Files.readAllBytes(file1), Files.readAllBytes(file2));
        }


    	try (InputStream is1 = Files.newInputStream(file1);
         	InputStream is2 = Files.newInputStream(file2)) {
        	int data;
        	while ((data = is1.read()) != -1)
            	if (data != is2.read())
                	return false;
    	}

    	return true;

	}

	public static void main(String[] args){

		
		Gitlet git = new Gitlet();

		
		if(Gitlet.readGit() != null ){
			git = Gitlet.readGit();
			//System.out.println("read success");
		}

		String commandString = args[0];
		switch(commandString){
			case "init": git.init(); 
				break;
			case "add": git.add(args[1]);
				break;
			case "commit": git.commit(args[1]);
				break;
			case "rm": git.rm(args[1]);
				break;
			case "log": String logcontent = git.log(); System.out.println(logcontent);
				break;
			case "global-log": String log = git.globalLog(); System.out.println(log);
				break;
			case "checkout": git.checkBranch(args[1]);
				break;
			case "branch": git.branch(args[1]);
				break;
			case "remove": git.rm(args[1]);
				break;
			case "status": git.status();
				break;
			case "find": git.find(args[1]);
				break;
			case "rebase": git.rebase(args[1]);
				break;
			default: System.out.println("error command");
				break;
		}

		Gitlet.saveGit(git);
	}
	
}
