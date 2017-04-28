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
		String regexAll = "<div class=\"Ptable\">(.+?)<div class=\"package-list\">";
		String regexBrand = "<dt>品牌</dt><dd>(.+?)</dd>";
		String regexVersion = "<dt>型号</dt><dd>(.+?)</dd>";
		String regexPublishYear = "<dt>上市年份</dt><dd>(.+?)年</dd>";
		String regexPublishMonth = "<dt>上市月份</dt><dd>(.+?)月</dd>";
		String regexSystem = "<dt>操作系统</dt><dd>(.+?)</dd>";
		String regexScreenSize = "<dt>主屏幕尺寸（英寸）</dt><dd>(.+?)英寸</dd>";
		String regexBatteryCapacity = "<dt>电池容量（mAh）</dt><dd>(.+?)</dd>";
		String regexWebId = "J-p-(.+?)\">";
		
		
		String result = get.sendGet(realUrl,encoding);
		String resultAll = get.RegexString(result, regexAll);
		
		String brand = get.RegexString(resultAll, regexBrand);
		String version = get.RegexString(resultAll, regexVersion);
		String publishYear = get.RegexString(resultAll, regexPublishYear);
		String publishMonth = get.RegexString(resultAll, regexPublishMonth);
		String system = get.RegexString(resultAll, regexSystem);
		String screenSize = get.RegexString(resultAll, regexScreenSize);
		String batteryCapacity = get.RegexString(resultAll, regexBatteryCapacity);
		String webId = get.RegexString(result, regexWebId);
		
		//获取价格
		String urlPrice = "http://item.m.jd.com/product/" + webId + ".html";
		String regexPrice = "big-price\">(.+?)</span>";
		String rePrice = get.sendGet(urlPrice, encoding);
		String price = get.RegexString(rePrice, regexPrice);
		
		String sql = "UPDATE JDMobilePhones.dbo.MobilePhones SET Price = '"+ price +"',Brand = '" + 
		brand + "',Version = '" +version + "',PublishYear = '" +  publishYear + "',PublishMonth = '"+ publishMonth +"',System = '"+ system +"',ScreenSize = '"+ screenSize +"',BatteryCapacity = '"+ batteryCapacity +"',WebId = '"+ webId + "'" +
		"WHERE Url  = '" + url + "'";
		save.changeInformation(sql);
	}
}
