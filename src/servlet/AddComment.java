package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDao;
import dao.CommentDaoImpl;
import pojo.Comment;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/addComment")
public class AddComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 是dai_comment表相关联的方法 注意 该变量为comment_type
		String result = "false";
		PrintWriter writer = null;
		try {
			CommentDao cd = new CommentDaoImpl();
			Long comment_createtime = System.currentTimeMillis();
			int comment_likenum = 0;
			String comment_name = request.getParameter("comment_name");
			int parent_id = Integer.parseInt(request.getParameter("parent_id"));
			String comment_content = request.getParameter("comment_content");
			int comment_type = Integer.parseInt(request.getParameter("comment_type"));
			String comment_email = request.getParameter("comment_email");
			
			Comment c = new Comment();
			c.setComment_content(comment_content);
			c.setComment_createtime(comment_createtime);
			c.setComment_likenum(comment_likenum);
			c.setComment_name(comment_name);
			c.setComment_type(comment_type);
			c.setParent_id(parent_id);
			c.setComment_email(comment_email);
			
			boolean flag = cd.insertComment(c);
			if(flag) {
				result = "true";
			}
			writer = response.getWriter();
			writer.write(result);	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
