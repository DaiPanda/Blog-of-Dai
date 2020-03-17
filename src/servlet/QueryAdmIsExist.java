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

/**
 * Servlet implementation class QueryAdmIsExist
 */
@WebServlet("/isExist")
public class QueryAdmIsExist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryAdmIsExist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		//根据姓名查询用户是否存在 改的更完善了一点
		String result = "false";
		PrintWriter writer = null;
		try {
			String username = request.getParameter("username");
			AdmDao a = new AdmDaoImpl();
			boolean flag = a.queryIsExist(username);
			if(flag==true) {
				result = "true";
			}
			writer =  resp.getWriter();
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
