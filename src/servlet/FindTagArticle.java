package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Article_Tag_Dao;
import dao.Article_Tag_DaoImpl;
import pojo.Article;
import pojo.Article_Tag;
import util.JsonUtil;

/**
 * Servlet implementation class FindTagArticle
 */
@WebServlet("/findTagArticle")
public class FindTagArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FindTagArticle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int limit = 0;
		int page = 0;
		int count = 0;
		PrintWriter writer = null;
		try {
			limit = Integer.parseInt(request.getParameter("limit"));
			page = Integer.parseInt(request.getParameter("page"));
			int tag_id = Integer.parseInt(request.getParameter("tag_id"));
			Article_Tag_Dao atd = new Article_Tag_DaoImpl();
			ArrayList<Article> atList = atd.searchTag_Article(tag_id, page, limit);
			count = atd.searchAllTag_Article(tag_id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 0);
			map.put("msg", "");
			map.put("count", count);
			map.put("data", atList);
			String jsonStr = JsonUtil.beanToString(map);
			writer = response.getWriter();
			writer.write(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writer.flush();
			writer.close();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
