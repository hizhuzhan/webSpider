package com.jdmobilephone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetFromUrl {
	/*
	 * 以GET方式获取网页代码
	 */
	public String sendGet(String url,String encoding){
		String result = "";
		BufferedReader in = null;
		try {
			System.out.println("...开始获取目标网页HTML...");
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.connect();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(),encoding));
			String line;
			while((line = in.readLine()) != null){
				result += line;
			}
			System.out.println("...获取完毕...");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("网址格式?");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("网址连接未成功...");
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("释放内存失败...");
				}
			}
		}
		return result;
	}
	
	/*
	 * 正则表达式匹配
	 * 匹配单一数据
	 */
	public String RegexString(String targetStr, String patternStr){
		System.out.println("...开始匹配数据...");
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(targetStr);
		if(matcher.find()){
			System.out.println("获取到：" + matcher.group(1));
			return matcher.group(1);
		}else{
			return "未匹配";
		}
	}
	
	/*
	 * 正则表达式匹配
	 * 匹配多数据(不重复数据)
	 */
	public Set RegexStringsSingle(String targetStr, String patternStr){
		System.out.println("...开始匹配数据...");
		Set resSet = new HashSet();
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(targetStr);
		int count = 0;
		while(matcher.find()){
			resSet.add(matcher.group());
			count = resSet.size();
		}
		for(Object set: resSet){
			System.out.println(set);
		}
		System.out.println("共匹配：" + count + "条数据");
		System.out.println("...匹配完毕...");
		return resSet;
	}
	
	/*
	 * 正则表达式匹配
	 * 匹配多数据(可重复数据)
	 */
	public List RegexStringsRepeat(String targetStr, String patternStr){
		System.out.println("...开始匹配数据...");
		List resList = new ArrayList();
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(targetStr);
		int count = 0;
		while(matcher.find()){
			resList.add(matcher.group(1));
			count = resList.size();
		}
		for(Object set: resList){
			System.out.println(set);
		}
		System.out.println("共匹配：" + count + "条数据");
		System.out.println("...匹配完毕...");
		return resList;
	}
}
