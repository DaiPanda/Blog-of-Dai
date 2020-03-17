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
 * Servlet implementation class EditAdm
 */
@WebServlet("/editAdm")
public class EditAdm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAdm() {
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
			System.out.println("博客啊博客！你进了我的方法没有呢！！！我是editAdm啊");
			int adm_id = Integer.parseInt(request.getParameter("adm_id"));
			String adm_name = request.getParameter("adm_name");
			String adm_password = request.getParameter("adm_password");
			Adm adm = new Adm();
			adm.setAdm_id(adm_id);
			adm.setAdm_name(adm_name);
			adm.setAdm_passward(adm_password);
			AdmDao a = new AdmDaoImpl();
			boolean flag = a.updateAdm(adm);
			System.out.println(flag);
			if(flag) {
				result ="true";	
			}
		    writer =  response.getWriter();
		    writer.write(result);
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
