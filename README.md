# webSpider

2017.4.17更新：
----
SpiderTest</br>
简单的爬下一个网页(www.baidu.com)</br>
目录：src/test/SpiderTest.java</br>

SpiderTest2</br>
爬下Baidu首页的Logo(www.baidu.com/img/bd_logo1.png)</br>
目录：src/test/SpiderTest2.java</br>

----
2017.4.25更新：
----
jdmobilephone:
爬取网址(https://list.jd.com/list.html?cat=9987,653,655&page=1&sort=sort_rank_asc&trans=1&JL=6_0_0&ms=5#J_main)</br>
将此所有页的手机的介绍和网址保存到SQL Server</br>
共包括3个类</br>

jdmobilephone.GetFromUrl提供4个方法</br>
Class1：GetFromUrl</br>
以Get方式获取网页代码</br>
Parameter：要爬取的网页网址(url)</br>
Return：网页代码</br>
Class2：RegexString</br>
正则表达式匹配单一数据</br>
Parameter：目标数据; 正则表达式</br>
Return：返回匹配到的一个数据</br>
Class3：RegexStringsSingle</br>
正则表达式匹配多个不重复的数据</br>
Parameter：目标数据; 正则表达式</br>
Return：返回匹配到的set</br>
Class4：RegexStringsRepeat</br>
正则表达式匹配多个可重复数据</br>
Parameter：目标数据; 正则表达式</br>
Return：返回匹配到的List</br>

jdmobilephone.SaveUseJDBC：提供一个方法</br>
Class1：changeInformation</br>
对数据库进行连接，并对数据库进行增删改</br>
Parameter：SQL语句(sql)</br>
NoReturn</br>

jdmobilephone.Main；调用上列方法对数据进行解析</br>

----
2017.4.27更新：
----
jdmobilephone.Main：更新自动爬取页数

----
2017.4.28更新：
----
jdmobilephone.Method：获取手机的其他信息</br>
更新字段：
价格：Price；
品牌：Brand；
型号：Version；
上市年份：PublishYear；
上市月份：PublishMonth；
操作系统：System；
频幕尺寸：ScreenSize；
电池容量：BatteryCapacity；
手机ID：WebId</br>
价格在手机端网址上抓取(http://item.m.jd.com/product/phoneId.html)</br>
phoneId为WebId

----
2017.5.11更新：
----
修复BUG：部分手机信息爬取不到与获取不到网页</br>
主要修改正则表达式与获取手机价格网址

----
