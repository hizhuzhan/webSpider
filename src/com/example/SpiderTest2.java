package com.example;

import java.io.*;
import java.net.*;
import java.util.regex.*;


public class SpiderTest2 {
	static String sendGet(String url){
		//定义一个字符串来存储网页内容
		String result = "";
		//定义一个缓冲字符输入流
		BufferedReader in = null;
		try {
			//将String转化成url对象
			URL realUrl = new URL(url);
			//初始化一个链接到那个url的连接
			URLConnection connection = realUrl.openConnection();
			//开始实际的连接
			connection.connect();
			//初始化BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			//用来临时存储抓到的每一行数据
			String line;
			while((line = in.readLine()) != null){
				//遍历抓取到每一行并将其存储到result里面
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送Get请求出现异常！"+e);
			e.printStackTrace();
		}
		//使用finally来关闭输入流
		finally{
			try{
				if(in != null){
					in.close();
				}
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	//正则表达式
	static String RegexString(String targetStr, String patternStr){
		//定义一个样式模板，此中使用正则表达式，括号中是要抓的内容
		//相当于埋好的陷阱匹配的地方就会掉下去
		Pattern pattern = Pattern.compile(patternStr);
		//定义一个matcher用来做匹配
		Matcher matcher = pattern.matcher(targetStr);
		//如果找到了
		if(matcher.find()){
			return matcher.group(1);
		}
		return "未匹配";
	}
	
	public static void main(String[] args){
		//定义即将要访问的链接
		String url = "https://list.jd.com/list.html?cat=9987,653,655&page=1&sort=sort_rank_asc&trans=1&JL=6_0_0&ms=6#J_main";
		//访问连接并获取页面
		String result = sendGet(url);
		System.out.println("网页内容："+result);
		//使用正则表达式匹配图片的src内容
//		String imgSrc = RegexString(result, "src=//(.+)\\swidth=");
//		//打印结果
//		System.out.println("百度Logo："+imgSrc);
	}
}
