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
import dao.CategoryDao;
import dao.CategoryDaoImpl;
import pojo.Article;
import pojo.Category;
import util.JsonUtil;

/**
 * Servlet implementation class GetSpecificCategory
 */
@WebServlet("/getSpecificCategory")
public class GetSpecificCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSpecificCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = null;
		try {
			int article_id = Integer.parseInt(request.getParameter("article_id"));
			ArticleDao ad = new ArticleDaoImpl();
			CategoryDao cd = new CategoryDaoImpl();
			Article a = ad.getOneArticle(article_id);
			int category_id = a.getCategory_id();
			int parent_id = cd.getParentCategory(category_id);
			Category category = cd.getOneCategory(category_id);
			Category category_parent = cd.getOneCategory(parent_id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("category", category);
			map.put("category_parent", category_parent);
			String jsonStr = JsonUtil.beanToString(map);
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
