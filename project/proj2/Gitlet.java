import java.io.*;
import java.nio.file.*;
import java.util.*; 
import java.text.*;
import java.util.Date;

public class Gitlet implements Serializable{

	List<String> addList;
	private int lastCommitedId;
	private static String GitPath = ".gitlet";
	Map<Integer,List<String>> gitLog;
	Map<String,Integer> findCommitId;

	public Gitlet(){
		lastCommitedId = -1;
		addList = new LinkedList<String>();
		gitLog = new HashMap<Integer,List<String>>();
		findCommitId = new HashMap<String,Integer>();

	}

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
		//lastCommitedId += 1;
		
		//first commit
	    File initial = new File(GitPath+"/0");
	    initial.mkdir();
	    commit("initial commit");
	}

	/*
	 Usage: java Gitlet add [file name]
	 Indicates you want the file to be included in the upcoming commit as having been changed.
	 Adding a file is also called staging the file. If the file had been marked for removal, 
	 instead just unmark it.
	 RunTime: O(N)
	*/
	public void add(String fileName){
		String fileName2 = GitPath+"/"+lastCommitedId+"/"+fileName;
		File fileExist = new File(fileName);
		File file2 = new File(fileName2);
		
		if(!fileExist.exists()){
			System.out.println("file does not exists");
		}

		if(addList.contains(fileName)){
			Path path1 = Paths.get(fileName2);
			Path path2 = Paths.get(fileName);
			if(file2.exists()){
				try{
					if(isSameContent(path1,path2)){
						System.out.println("the same file ");
					}else{
						addList.add(fileName);
						System.out.println("diffrent file");
					}
				}catch(IOException e){
					e.printStackTrace();
				}
			}
				 
		}else{
			addList.add(fileName);
		}
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
	 	}else if(addList.size() == 0 && message.equals("initial commit.")){
	 		System.out.println("first commit.");
	 	
	 	}else{
	 		//file that were newly added	
	 		lastCommitedId += 1;
	 		makeDir(GitPath+"/"+lastCommitedId);

	 		for(String fileName : addList){
	 			File source = new File(fileName);
	 			String[] s = fileName.split("\\/");
	 			File target = new File(GitPath+"/"+lastCommitedId+"/"+s[s.length-1]);
	 			try{
		 			Files.copy(source.toPath(),target.toPath(),StandardCopyOption.REPLACE_EXISTING);
		 		}catch(IOException e){
		 			System.out.println("There occurs an IOEception when commiting files");
		 		}
	 		}
	 	}
	 		String time = getTime();
	 		List<String> commitList = new LinkedList<String>();

	 		commitList.add("Commit "+lastCommitedId);
	 		commitList.add(time);
	 		commitList.add(message);
	 		gitLog.put(lastCommitedId,commitList);
	 		findCommitId.put(message,lastCommitedId);

	 }

	 /*remove file on stage*/
	public void remove(String fileName){
		if(!addList.contains(fileName)){
			System.out.println(" No reason to remove the file.");
		}

		for(int i = 0;i < addList.size();i++){
			if(addList.get(i).equals(fileName)){
				addList.remove(i);
				break;
			}
		}
	}

	/*log*/
	public String log(){
		int commitedId = lastCommitedId;
		StringBuilder logString = new StringBuilder();
		
		while(commitedId >= 0){
			//System.out.println("====");
			logString.append("====");
			logString.append("\n");
			for(int i = 0 ; i < 3 ; i++){
				logString.append(gitLog.get(commitedId).get(i));

				logString.append("\n");

			}

			
			logString.append("\n");
			commitedId--;
		}

		return logString.toString();
		
	}

	/*print out the id when gives the commit message*/
	public void find(String message){
		System.out.println(findCommitId.get(message));
	}

	/*1. restore the version of the file from the previous commit.*/
	public void checkout(String fileName){

		File target = new File(fileName);
		String[] s = fileName.split("\\/");
	 	File source = new File(GitPath+"/"+lastCommitedId+"/"+s[s.length-1]);
	 	try{
		 	Files.copy(source.toPath(),target.toPath(),StandardCopyOption.REPLACE_EXISTING);
		 }catch(IOException e){
		 	System.out.println("There occurs an IOEception when commiting files");
		 }
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
			case "rm": git.remove(args[1]);
				break;
			case "log": String logcontent = git.log(); System.out.println(logcontent);
				break;
			case "checkout": git.checkout(args[1]);
				break;
			default: System.out.println("error command");
				break;
		}

		Gitlet.saveGit(git);
	}
	
}