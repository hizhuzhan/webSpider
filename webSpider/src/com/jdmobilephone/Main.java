package com.jdmobilephone;

import java.sql.Savepoint;
import java.util.List;
import java.util.Set;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetFromUrl get = new GetFromUrl();
		SaveUseJDBC save = new SaveUseJDBC();
		//返回页数
		String urlPage = "https://list.jd.com/list.html?cat=9987,653,655";
		String regexPage = "<em>共<b>(.+?)</b>页";
		String resultPage = get.sendGet(urlPage);
		String pageStr = get.RegexString(resultPage, regexPage);
		int page = Integer.parseInt(pageStr);
		
		//返回手机介绍AND对应网址
		String regexAll = " <div class=\"p-name\">(.+?)<\\/div>";
		String regexUrl = "(//item.jd.com/[a-zA-Z0-9]+.html)";
		String regexName = "<em>(.+?)<\\/em>";
		int count = 0;
		int idName = 0;
		int idUrl = 0;
		for(int i = 1; i <= page; i++){
			count++;
			String url = "https://list.jd.com/list.html?cat=9987,653,655&page=" + i + "&sort=sort_rank_asc&trans=1&JL=6_0_0&ms=5#J_main";
			String result = get.sendGet(url);
			List putAll = get.RegexStringsRepeat(result, regexAll);
			for(int x = 0; x < putAll.size(); x++){
				String resultAll = (String) putAll.get(x);
				List putName = get.RegexStringsRepeat(resultAll, regexName);
				List putUrl = get.RegexStringsRepeat(resultAll, regexUrl);
				for(int y = 0; y < putName.size(); y++){
					idName++;
					String saveSql = "INSERT INTO JDMobilePhones.dbo.MobilePhones(Id,Introduce)VALUES(" + idName + ",'" 
					+ putName.get(y) + "')";
					save.changeInformation(saveSql);
				}
				for(int z = 0; z < putUrl.size(); z++){
					idUrl++;
					String saveSql = "UPDATE JDMobilePhones.dbo.MobilePhones SET Url = '" + putUrl.get(z) + "' where Id = " + idUrl;
					save.changeInformation(saveSql);
				}

			}
			System.out.println("...第" + count + "页爬取完毕...\n");
		}
		
		System.out.println("===匹配了" + count + "次===");
		
	}
}