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
import dao.Article_Tag_Dao;
import dao.Article_Tag_DaoImpl;
import dao.TagDao;
import dao.TagDaoImpl;
import pojo.Article;
import pojo.Article_Tag;
import pojo.Tag;
import util.JsonUtil;

/**
 * Servlet implementation class FindArticle
 */
@WebServlet("/findArticle")
public class FindArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FindArticle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = null;
		try {
			int article_id = Integer.parseInt(request.getParameter("article_id"));
			//1.调用articleDao查找出Article
			ArticleDao ad = new ArticleDaoImpl();
			Article a = ad.getOneArticle(article_id);
			//2.调用article_tagdao查找出有多少个tag 以及相对应的tag_id
			Article_Tag_Dao atd = new Article_Tag_DaoImpl();
			ArrayList<Article_Tag> at = atd.searchArticle_Tag(article_id);
			Map<String, Object> map = new HashMap<String, Object>();
			ArrayList<Tag> tList = new ArrayList<Tag>();
			TagDao td = new TagDaoImpl();
			int tag_id1,tag_id2,tag_id3;
			if(at.size()==1){
				tag_id1 = at.get(0).getTag_id();
				Tag t1 = td.getOneTag(tag_id1);
				tList.add(t1);
			}else if(at.size()==2) {
				tag_id1 = at.get(0).getTag_id();
				tag_id2 = at.get(1).getTag_id();
				Tag t1 = td.getOneTag(tag_id1);
				Tag t2 = td.getOneTag(tag_id2);
				tList.add(t1);
				tList.add(t2);
			}else if(at.size()==3) {
				tag_id1 = at.get(0).getTag_id();
				tag_id2 = at.get(1).getTag_id();
				tag_id3 = at.get(2).getTag_id();
				Tag t1 = td.getOneTag(tag_id1);
				Tag t2 = td.getOneTag(tag_id2);
				Tag t3 = td.getOneTag(tag_id3);
				tList.add(t1);
				tList.add(t2);
				tList.add(t3);
			}
			map.put("dataArticle",a);
			map.put("dataTag", tList);
			map.put("tagSize", at.size());
			String jsonStr = JsonUtil.beanToString(map);
			System.out.println(jsonStr);
			writer= response.getWriter();
			writer.write(jsonStr);	
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
