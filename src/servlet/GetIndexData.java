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
 * Servlet implementation class GetIndexData
 */
@WebServlet("/getIndexData")
public class GetIndexData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetIndexData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = null;
		try {	
			ArticleDao ad = new ArticleDaoImpl();
			//可前面传过来
			ArrayList<Article> a1 = ad.getHotArticle(5);
			ArrayList<Article> a2 = ad.getNearArticle(2);
			Article a3 = ad.getTopArticle(1);
			ArrayList<Article> a4 = ad.getRandomArticle(5);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dataHot", a1);
			map.put("dataNear", a2);
			map.put("dataTop", a3);
			map.put("dataRandom", a4);
			
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
