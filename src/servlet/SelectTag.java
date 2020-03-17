package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdmDao;
import dao.AdmDaoImpl;
import dao.TagDao;
import dao.TagDaoImpl;
import pojo.Adm;
import pojo.Tag;

/**
 * Servlet implementation class SelectTag
 */
@WebServlet("/selectTag")
public class SelectTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectTag() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//System.out.println("是否进入了selectTag呢？");
			int tag_id = Integer.parseInt(request.getParameter("tag_id"));
			TagDao td = new TagDaoImpl();
			Tag t = td.getOneTag(tag_id);
			if(t!=null) {
				request.setAttribute("tag_id", t.getTag_id());
				request.setAttribute("tag_name",t.getTag_name());
				request.getRequestDispatcher("tag/editTag.jsp").forward(request, response);//转发到成功页面
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
