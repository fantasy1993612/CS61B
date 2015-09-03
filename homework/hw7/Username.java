import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Username {

    // Potentially useless note: (int) '0' == 48, (int) 'a' == 97
    private String username;

    //random 0-9 a-z A-Z
    private char randomChar(){
        int  index = (int)(Math.random()*62);

        if(index < 10){
            return (char)(index + 48);
        } else if (index < 36) {
            return (char)((index - 10) + 65);
        } else {
            return (char)((index - 36) + 97);
        }
    }
    // Instance Variables (remember, they should be private!)
    // YOUR CODE HERE
    /*A no-parameter constructor that generates a random, valid username. 
    The generated username should sometimes be 2 characters long, and sometimes 
    be 3 characters long. Hint: A great way to generate a random char is to generate 
    a random int between 0-25 and type-cast it into a char. You should play around with 
    small examples and get familiar with this before you proceed to write your super awesome 
    non-trivial solution.*/
    public Username() {
        // YOUR CODE HERE
        if(Math.random() > .6){
            username = new StringBuilder().append(randomChar()).append(randomChar()).append(randomChar()).toString();
        }else{
            username = new StringBuilder().append(randomChar()).append(randomChar()).toString();
        }
    }

    public Username(String reqName) {
        // YOUR CODE HERE
        if(reqName == null){
            throw new NullPointerException("Requested username is null!");
        }

        if(reqName.length() > 3 || reqName.length() < 2){
            throw new IllegalArgumentException("length error");
        }

        Pattern p = Pattern.compile("[0-9A-Za-z]{2,3}");
        Matcher m = p.matcher(reqName);
        if(m.matches()){
            username = reqName;
        } else {
            throw new IllegalArgumentException("Username must contain only alphanumeric characters!");
        }
    }

    @Override
    public boolean equals(Object o) {
       if (o != null && o instanceof Username) {
            Username other = (Username) o;
            return this.hashCode() == other.hashCode();
        }
        return false;
    }

    @Override
    public int hashCode() { 
        String hashString;
        if(username.length() == 2)
            hashString = " " + username.toUpperCase();
        else
            hashString = username.toUpperCase();
        
        int hashInt = 0;
        
        for(int i = 0; i <= 2; i += 1){
            hashInt += (hashString.charAt(i) - 40) * (Math.pow(100, i));
        }
         
        return hashInt;
    }

    public static void main(String[] args) {
        // You can put some simple testing here.
        Username name = new Username();
        System.out.println(name.username);
    }
}