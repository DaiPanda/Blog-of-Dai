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

import dao.AdmDao;
import dao.AdmDaoImpl;
import pojo.Adm;
import util.JsonUtil;

/**
 * Servlet implementation class ListAdm
 */
@WebServlet("/listAdm")
public class ListAdm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAdm() {
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
			
			AdmDao a = new AdmDaoImpl();
			ArrayList<Adm> aList = a.getPageAdm(page, limit);
			ArrayList<Adm> aListTotal = a.getAllAdm();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 0);
			map.put("msg", "");
			map.put("count", aListTotal.size());
			map.put("data", aList);
			String jsonStr = JsonUtil.beanToString(map);
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
