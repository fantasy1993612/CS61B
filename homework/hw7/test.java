import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test{
	public static void main(String[] args) {
		Pattern p = Pattern.compile(".*@.*");
		 Matcher m = p.matcher("12322sfasf3@qq.comasdasf");
		 if(m.matches()){
		 	System.out.println("success");
		 }else{
		 	System.out.println("failure");
		 }
	}
} 