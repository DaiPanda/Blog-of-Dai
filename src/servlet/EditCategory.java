package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDao;
import dao.CategoryDaoImpl;
import pojo.Category;

/**
 * Servlet implementation class EditCategory
 */
@WebServlet("/editCategory")
public class EditCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCategory() {
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
			System.out.println("博客啊博客！你进了我的方法没有呢！！！我是edit啊");
			int category_id = Integer.parseInt(request.getParameter("category_id"));
			int parent_id = Integer.parseInt(request.getParameter("parent_id"));
			String category_name = request.getParameter("category_name");
			Category c1 = new Category();
			c1.setCategory_id(category_id);
			c1.setCategory_name(category_name);
			c1.setParent_id(parent_id);
			CategoryDao c = new CategoryDaoImpl();
			boolean flag = c.update(c1);
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
