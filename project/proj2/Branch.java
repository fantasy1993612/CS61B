import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class Branch implements Serializable{

	private int curCommitedId;
	private int lastCommited;
	private String commitMessage;
	private String commitDate;
	private Map<String,String> commitedFiles = new HashMap<String,String>();

	public Branch(int curCommitedId,int lastCommited,String commitMessage,String commitDate){
		this.curCommitedId = curCommitedId;
		this.lastCommited = lastCommited;
		this.commitMessage = commitMessage;
		this.commitDate = commitDate;
	}

	public String getCommitDate(){
		return this.commitDate;
	}

	public int getLastCommitedId(){
		return this.lastCommited;
	} 

	public int getCurCommitedId(){
		return this.curCommitedId;
	}

	public String getCommitMessage(){
		return this.commitMessage;
	}
	
	public String getFilePath(String fileName){
		return commitedFiles.get(fileName);
	}

	public void addFile(String fileName,String filePath){
		commitedFiles.put(fileName,filePath);
	}

	public boolean containsFile(String fileName) {
		return commitedFiles.containsKey(fileName);
	}

	public Map<String,String> getAllCommitedFiles(){
		return this.commitedFiles;
	}

}