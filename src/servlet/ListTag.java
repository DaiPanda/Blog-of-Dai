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

import dao.TagDao;
import dao.TagDaoImpl;
import pojo.Tag;
import util.JsonUtil;

/**
 * Servlet implementation class ListTag
 */
@WebServlet("/listTag")
public class ListTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListTag() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int limit = 0;
		int page = 0;		
		PrintWriter writer = null;
		try {
			limit = Integer.parseInt(request.getParameter("limit"));
			page = Integer.parseInt(request.getParameter("page"));		
			TagDao td = new TagDaoImpl();
			ArrayList<Tag> t = td.getPageTag(page, limit);
			ArrayList<Tag> tTotal = td.getAllTag();	
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 0);
			map.put("msg", "");
			map.put("count", tTotal.size());
			map.put("data", t);
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
