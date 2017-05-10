package com.jdmobilephone;

public class Test {
	public static void main(String[] args){
		GetFromUrl test = new GetFromUrl();
		String url = "https://item.m.jd.com/product/3652063.html";
		String result = test.sendGet(url, "UTF-8");
		System.out.println(result);
		String regex = "name=\"jdPrice\" value=\"(.+?)\"/>";
		String relResult = test.RegexString(result, regex, 1);
	}
	
}
