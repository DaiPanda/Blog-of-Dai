package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdmDao;
import dao.AdmDaoImpl;
import dao.CategoryDao;
import dao.CategoryDaoImpl;
import pojo.Adm;
import pojo.Category;

/**
 * Servlet implementation class SelectAdm
 */
@WebServlet("/selectAdm")
public class SelectAdm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectAdm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("是否进入了selectAdm呢？");
			int adm_id = Integer.parseInt(request.getParameter("adm_id"));
			AdmDao a = new AdmDaoImpl();
			Adm adm = a.getOneAdm(adm_id);
			if(adm!=null) {
				request.setAttribute("adm_id", adm.getAdm_id());
				request.setAttribute("adm_name",adm.getAdm_name());
				request.setAttribute("adm_password", adm.getAdm_passward());
				request.getRequestDispatcher("adm/editAdm.jsp").forward(request, response);//转发到成功页面
			}
		} catch (Exception e) {
			e.printStackTrace();
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
