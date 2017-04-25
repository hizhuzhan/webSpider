package come.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
	static String RegexString(String targetStr, String patternStr){
		//定义一个样式模板，此中使用正则表达式，括号中是要抓的内容
		//相当于埋好的陷阱匹配的地方就会掉下去
		Pattern pattern = Pattern.compile(patternStr);
		//定义一个matcher用来做匹配
		Matcher matcher = pattern.matcher(targetStr);
		//如果找到了
		if(matcher.find()){
			return matcher.group();
			
		}
		return "未匹配";
	}
	
	static List RegexString2(String targetStr, String patternStr){
		//定义一个样式模板，此中使用正则表达式，括号中是要抓的内容
		//相当于埋好的陷阱匹配的地方就会掉下去
		List resList = new ArrayList();
		Pattern pattern = Pattern.compile(patternStr);
		//定义一个matcher用来做匹配
		Matcher matcher = pattern.matcher(targetStr);
		//如果找到了
		int count = 0;
		while(matcher.find()){
			count++; 
			//System.out.println(matcher.group());
			resList.add(matcher.group());
		}
		System.out.println("共匹配："+ count +"项");
		return resList;
		
	}
	
	
	
	public static void main(String[] args){
		String A = "t; &lt;img hidefocus=true src=https://www.baidu1.com/img/bd_logo1.png width=270 height=129&gt; &lt;/div&gt;t; &lt;img hidefocus=true src=https://www.baidu2.com/img/bd_logo1.png width=270 height=129&gt; &谁lt;/div&gt;t; &lt;img hidefocus=tr请求ue src=https://www.baidu3.com/img/bd_logo1.png width=270 height=129&gt; &lt;/div&gt;t; &lt;img hidefocus=true src=https://www.baidu4.com/img/bd_logo1.png width=270 height=129&gt; &lt;/div&gt;t; &lt;img hidefocus=true src=https://www.baidu5.com/img/bd_logo1.png width=270 height=129&gt; &lt;/div&gt;t; &lt;img hidefocus=true src=https://www.baidu6.com/img/bd_logo1.png width=270 height=129&gt; &lt;/div&gt;t; &lt;img hidefocus=true src=https://www.baidu7.com/img/bd_logo1.png width=270 height=129&gt; &lt;/div&gt; ";
//		String B = "src=//(.+)\\swidth=";  
//		String B = "[\\u4e00-\\u9fa5]";
		String B = "[a-zA-z]+://[^\\s]*";
//		String A = "";
//		String B = "";
//		String result = RegexString(A,B);
		
//		System.out.println(result);
		for(Object list:RegexString2(A,B)){
			System.out.println(list);
		}
	}
}
