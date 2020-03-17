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
import pojo.Adm;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * void getSession()方法相当于得到一个session对象，而void setAttribute()和String Attribute分别是对属性赋值和得到属性值的方法。
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//System.out.println("kkkk");
		
		PrintWriter out = response.getWriter();
		String name = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		
		AdmDao a = new AdmDaoImpl();
		Adm adm = a.getAdm(name	, pwd);
		System.out.println(a.toString());
		if(adm!=null) {
			request.getSession().setAttribute("admin",adm);			
			//重定向到成功页面
			response.sendRedirect("index.jsp"); 
		}else {
			//out.println("啊啊啊，我应该转发到失败的界面了");
			request.setAttribute("errMsg", "账号或密码错误，请重新输入信息！");
			//转发到login界面
			request.getRequestDispatcher("login.jsp").forward(request, response); 
			return;
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
