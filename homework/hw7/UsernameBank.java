import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameBank {

    // Instance variables (remember, they should be private!)
    // YOUR CODE HERE
    private Map<String,String>  userName;
    private Map<String,String>  userEmail;
    private Map<String,Integer> invalidUsername;//record the non-null invalid username and the attempt put times
    private Map<String,Integer> invalidUserEmail;
    private int invalidUsernameCount = 0;
    private int invalidEmailCount = 0;

    public UsernameBank() {
        // YOUR CODE HERE
        //userName = new HashMap();
        //userEmail
    }

    private boolean checkUser(String userMessage,String userPattern){
        Matcher userMatch;
        Pattern usernamePattern = Pattern.compile("[0-9A-Za-z]{2,3}");
        Pattern userEmailPattern = Pattern.compile(".*@.*");

        if(userPattern.equals("userName")){
            userMatch = usernamePattern.matcher(userMessage);
            return userMatch.matches() == true; 
        }

        if(userPattern.equals("userEmail")){
            userMatch = userEmailPattern.matcher(userMessage);
            return userMatch.matches() == true; 
        }
    }

    public void generateUsername(String username, String email) {
        // YOUR CODE HERE
    }

    public String getEmail(String username) {
        // YOUR CODE HERE
       
        if(username.equals("null")){
            throw new NullPointerException("No username!!");
        }else if(checkUser(username,"userName")){  
                if(userName.containsKey(username)){
                    if(userName.get(username)!= null){
                        return userName.get(username);
                    }
                }else{
                    invalidUsername.put(username,invalidUsernameCount+1);
                    return null;
                }
        }else{
                invalidUsername.put(username,invalidUsernameCount+1);
                return null;
        }
      
    }

    public String getUsername(String userEmail)  {
        // YOUR CODE HERE
        if(userEmail.equals("null")){
            throw new NullPointerException("No userEmail");
        }else if(checkUser(userEmail,"userEmail")){
            if(userEmail.containsKey(userEmail)){
                    if(userEmail.get(userEmail)!= null){
                        return userEmail.get(userEmail);
                    }
                }else{
                    invalidUserEmail.put(userEmail,invalidUserEmail+1);
                    return null;
                }
        }
        return null;
    }

    public Map<String, Integer> getBadEmails() {
        // YOUR CODE HERE
        return invalidUserEmail;
    }

    public Map<String, Integer> getBadUsernames() {
        // YOUR CODE HERE
        return invalidUsername;
    }

    public String suggestUsername() {
        // YOUR CODE HERE
        return null;
    }

    // The answer is somewhere in between 3 and 1000.
    public static final int followUp() {
        // YOUR CODE HERE
        return 0;
    }

    // Optional, suggested method. Use or delete as you prefer.
    private void recordBadUsername(String username) {
        // YOUR CODE HERE
    }

    // Optional, suggested method. Use or delete as you prefer.
    private void recordBadEmail(String email) {
        // YOUR CODE HERE
    }
}