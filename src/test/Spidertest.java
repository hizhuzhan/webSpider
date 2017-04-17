package test;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
/*
 * Java模拟Get访问百度的Main方法
 */
public class Spidertest {
	public static void main(String[] args){
		//定义访问的链接
		String url = "http://www.baidu.com";
		//定义一个字符串来储存网页内容
		String result= "";
		//定义一个缓冲字符输入流
		BufferedReader in = null;
		try{
			//将String转换为URL对象
			URL realUrl = new URL(url);
			//初始化一个连接到那个Url连接
			URLConnection connection = realUrl.openConnection();
			//开始实际连接
			connection.connect();
			//初始化 Bufferedreader 输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			//用来临时储存抓取的每一行数据
			String line;
			while((line = in.readLine()) != null){
				//遍历抓取到的每一行并将其储存到result里面
				result +=line;
			}
		}catch(Exception e){
			System.out.println("发送GET请求出现异常！" + e);
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
		System.out.println(result);
	}
		
}

