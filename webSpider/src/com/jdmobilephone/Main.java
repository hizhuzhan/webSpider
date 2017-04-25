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
		
		//返回手机介绍AND网址
		String regexAll = " <div class=\"p-name\">(.+?)<\\/div>";
		String regexUrl = "(//item.jd.com/[a-zA-Z0-9]+.html)";
		String regexName = "<em>(.+?)<\\/em>";
		int count = 0;
		int pages = 169;
		int id = 0;
		for(int page = 1; page <= pages; page++){
			count++;
			String url = "https://list.jd.com/list.html?cat=9987,653,655&page=" + page + "&sort=sort_rank_asc&trans=1&JL=6_0_0&ms=5#J_main";
			String result = get.sendGet(url);
			List putAll = get.RegexStringsRepeat(result, regexAll);
			for(int i = 0; i < putAll.size(); i++){
				String resultAll = (String) putAll.get(i);
				List putName = get.RegexStringsRepeat(resultAll, regexName);
				List putUrl = get.RegexStringsRepeat(resultAll, regexUrl);
				for(int x = 0; x < putName.size(); x++){
					String saveSql = "INSERT INTO JDMobilePhones.dbo.MobilePhones(Introduce)VALUES('" 
					+ putName.get(x) + "')";
					save.changeInformation(saveSql);
				}
				for(int y = 0; y < putUrl.size(); y++){
					id++;
					String saveSql = "UPDATE JDMobilePhones.dbo.MobilePhones SET Url = '" + putUrl.get(y) + "' where Id = " + id;
					save.changeInformation(saveSql);
				}

			}
			System.out.println("...第" + page + "页爬取完毕...\n");
		}
		
		System.out.println("===匹配了" + count + "次===");
		
	}
}