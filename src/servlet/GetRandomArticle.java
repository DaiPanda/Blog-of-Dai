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
 * Servlet implementation class GetRandomArticle
 */
@WebServlet("/getRandomArticle")
public class GetRandomArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRandomArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//随机获得数据（<猜你喜欢>）
		int limit = 0;
		PrintWriter writer = null;
		try {	
			limit = Integer.parseInt(request.getParameter("limit"));
			ArticleDao ad = new ArticleDaoImpl();
			ArrayList<Article> aList = ad.getRandomArticle(limit);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("count", aList.size());
			map.put("data", aList);
			String jsonStr = JsonUtil.beanToString(map);
			System.out.println(jsonStr);
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
