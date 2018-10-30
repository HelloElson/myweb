package com.mingrisoft;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class News {
	DBConnection DBConn = new DBConnection();
	Function Fun = new Function();
	//新闻列表查询方法
	public String ListNewsFront(String sPage, String strPage) {
		try {
			Connection Conn = DBConn.getConn();
			Statement stmt = Conn.createStatement();
			ResultSet rs = null;
			// 定义本方法返回字符串数据
			StringBuffer sb = new StringBuffer();
			int i;
			int intPage = 1;
			int intPageSize = 5;
			// 创建sql语句查询News表全部信息
			String sSql = "select * from News order by NewsID desc";
			//通过执行sql语句得到查询结果
			rs = stmt.executeQuery(sSql);
			if (!rs.next()) {
				// 返回属性添加字符串数据用于页面显示
				sb.append("<tr height=\"25\" bgcolor=\"#d6dff7\" class=\"info1\">");
				sb.append("<td colspan=\"5\">\r\n");
				// 返回属性添加字符串数据用于岩棉展示
				sb.append("<div alogin=\"centter\"><b>没有记录!</b</div></td></tr>\r\n");							
			}else {
				// 将传入参数strpage进行数据格式转换
				intPage = Fun.StrToInt(strPage);
				// 将传入的参数sPage 进行数据格式转换
				sPage = Fun.CheckReplace(sPage);
				if(intPage == 0) {
					intPage = 1;
				}
				// 计算当前页面显示新闻条数
				rs.absolute((intPage - 1) * intPageSize + 1);
				i = 0;
				// i属性小于页面显示条数并且查询结果集不为空,进行循环方法
				while(i<intPageSize && !rs.isAfterLast()) {
					// 定义数字型变量并赋值News表里的NewsID属性
					int NewsID = rs.getInt("NewsID");
					//定义数字类型变量并赋值News表里NewsTitle属性
					String NewsTitle = rs.getString("NewsTitle");
					// 定义数字型变量并赋值News表里的NewsTime属性
					String NewsTime = rs.getString("NewsTime");
					// 定义数字型变量并赋值News表里的AdminName属性
					String AdminName = rs.getString("AdminName");
					// 返回属性添加字符串数据用于页面显示,<tr>表示换行
					sb.append("<tr>");
					// 返回属性添加字符串用于页面显示新闻标题
					sb.append("<td>"+NewsTitle+"</td>");
					// 返回属性添加字符串数据用于页面显示用户名
					sb.append("<td>"+AdminName+"</td>");
					// 返回属性添加字符串数据用于页面显示新闻时间
					sb.append("<td>"+NewsTime+"</td>");
					// 返回属性添加字符串数据用于页面显示详情按钮
					sb.append("<td><a style=\"color:3F862E\" target=\"_blank\" href=\"newsFrontDetail.jsp?newsID=" + NewsID +"\">详情</a></td><tr>");
					rs.next();
					i++;												
				}
				// 拼写字符串数据用于列表最下方的分页方法
				sb.append(Fun.PageFront(sPage, rs, intPage, intPageSize));			
			}
			rs.close();
			stmt.close();
			return sb.toString();
		} catch (Exception e) {
			return "No";
		}
	}
			

}
