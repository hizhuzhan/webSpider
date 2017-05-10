package com.jdmobilephone;
/*
 * 品牌：Brand     
 * 型号：Version     
 * 上市年份：PublishYear     
 * 上市月份：PublishMonth     
 * 操作系统：System     
 * 屏幕尺寸：ScreenSize     
 * 电池容量 ：BatteryCapacity     
 */
public class Method {
	public void savePhoneInfo(String url){
		String realUrl = "https:"+url;
		GetFromUrl get = new GetFromUrl();
		SaveUseJDBC save = new SaveUseJDBC();
		//网页编码
		String encoding = "GBK";
		//抓取字段
		//All
		//group2
		String regexAll = "(>规格与包装<|>参数规格<)(.+?)</html>";
		//品牌
		//group1
		String regexBrand = "<li title=\'(.+?)\'>(品牌：|商品名称：)";
		//型号
		//group2
		String regexVersion = "型号(</td><td>|</dt><dd>)(.+?)</";
		//上市年份
		//group2
		String regexPublishYear = "上市年份(</dt><dd>|</td><td>)(.+?)(年</|</)";
		//String regexPublishMonth = "<dt>上市月份</dt><dd>(.+?)月</dd>";
		//操作系统
		//group2
		String regexSystem = "操作系统(</dt><dd>|</td><td>)(.+?)</";
		//屏幕尺寸
		//group2
		String regexScreenSize = "主屏幕尺寸（英寸）(</dt><dd>|</td><td>)(.+?)(英寸</|</)";
		//电池容量
		//2
		String regexBatteryCapacity = "电池容量（mAh）(</dt><dd>|</td><td>)(.+?)</";
		String regexWebId = "com/(.+?).html";
		
		
		String result = get.sendGet(realUrl,encoding);
		String resultAll = get.RegexString(result, regexAll, 2);
		
		String brand = get.RegexString(resultAll, regexBrand, 1);
		String version = get.RegexString(resultAll, regexVersion, 2);
		String publishYear = get.RegexString(resultAll, regexPublishYear, 2);
//		String publishMonth = get.RegexString(resultAll, regexPublishMonth, 1);
		String system = get.RegexString(resultAll, regexSystem, 2);
		String screenSize = get.RegexString(resultAll, regexScreenSize, 2);
		String batteryCapacity = get.RegexString(resultAll, regexBatteryCapacity, 2);
		String webId = get.RegexString(url, regexWebId, 1);
		
		//获取价格
		String urlPrice = "http://p.3.cn/prices/mgets?skuIds=J_" + webId ;
		String regexPrice = "\"p\":\"(.+?)\",\"m\"";
		String rePrice = get.sendGet(urlPrice, encoding);
		String price = get.RegexString(rePrice, regexPrice, 1);
		
		String sql = "UPDATE JDMobilePhones.dbo.MobilePhones SET Price = '"+ price +"',Brand = '" + 
		brand + "',Version = '" +version + "',PublishYear = '" +  publishYear + "',System = '"+ system +"',ScreenSize = '"+ screenSize +"',BatteryCapacity = '"+ batteryCapacity +"',WebId = '"+ webId + "'" +
		"WHERE Url  = '" + url + "'";
		save.changeInformation(sql);
	}
}
