package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdmDao;
import dao.AdmDaoImpl;
import dao.TagDao;
import dao.TagDaoImpl;

/**
 * Servlet implementation class QueryTagIsExist
 */
@WebServlet("/tagIsExist")
public class QueryTagIsExist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryTagIsExist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = "false";
		PrintWriter writer = null;
		try {
			String tag_name = request.getParameter("tag_name");
			TagDao td = new TagDaoImpl();
			boolean flag = td.queryIsExist(tag_name);
			if(flag==true) {
				result = "true";
			}
			writer =  response.getWriter();
			writer.write(result);
		} catch (Exception e) {
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
