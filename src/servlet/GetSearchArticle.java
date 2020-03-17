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

import dao.ArticleDao;
import dao.ArticleDaoImpl;
import pojo.Article;
import util.JsonUtil;

/**
 * Servlet implementation class GetSearchArticle
 */
@WebServlet("/getSearchArticle")
public class GetSearchArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSearchArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("测试是否进入了getSearchArticle");
		PrintWriter writer = null;
		int limit = 0;
		int page = 0;		
		try {
			limit = Integer.parseInt(request.getParameter("limit"));
			page = Integer.parseInt(request.getParameter("page"));		
			String article_title = request.getParameter("article_title");
			ArticleDao ad = new ArticleDaoImpl();
			ArrayList<Article> aListTotal = ad.getSearchArticle(article_title);
			ArrayList<Article> aListPageTotal = ad.getSearchPageArticle(article_title, page, limit);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 0);
			map.put("msg", "");
			map.put("count", aListTotal.size());
			map.put("data", aListPageTotal);
			String jsonStr = JsonUtil.beanToString(map);
			//System.out.println(jsonStr);
			writer= response.getWriter();
			writer.write(jsonStr);	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			writer.flush();
			writer.close();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
