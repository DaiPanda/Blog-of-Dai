package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FriendDao;
import dao.FriendDaoImpl;
import pojo.Friend;

/**
 * Servlet implementation class EditFriend
 */
@WebServlet("/editFriend")
public class EditFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditFriend() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("测试是否进入了editFriend方法");
		String result = "false";
		PrintWriter writer = null;
		try {
			int friend_id =  Integer.parseInt(request.getParameter("friend_id"));
			String friend_url = request.getParameter("friend_url");
			String friend_name = request.getParameter("friend_name");
			Friend f = new Friend();
			f.setFriend_id(friend_id);;
			f.setFriend_name(friend_name);
			f.setFriend_url(friend_url);
			FriendDao fd = new FriendDaoImpl();
			boolean flag = fd.updateFriend(f);
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
