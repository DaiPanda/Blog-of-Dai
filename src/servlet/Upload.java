package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import pojo.Data;
import util.JsonUtil;
 

/**
 * Servlet implementation class Upload
 */
@WebServlet("/upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 后台最后进行一个判断
		 * 1.判断文件大小
		 * 2.判断文件宽和高的比值
		 * 3.上传失败进行提示
		 * 4.通过map.put("msg","xxxxx")将值传过来
		 * 
		 * 5.进行文件扩展名判断
		 * **/
		PrintWriter writer = null;
		System.out.println("测试是否进入了upload！！！！！！");
		//1.得到上传文件的保存目录
		String savePath = this.getServletContext().getRealPath("/upload");
		//System.out.println(savePath2);
		//String savePath2 = "D:\\workplace\\MyBlog\\WebContent\\upload";
		String returnPath;
		//System.out.println("savePath在"+savePath);
		File file = new File(savePath);
		//2.判断上传文件的保存目录是否存在
		// 如果目录不存在或者目录是文件
		if(!file.exists()&&!file.isDirectory()) {
			System.out.println(savePath+"目录不存在，需要创建");
			//3.创建目录
			file.mkdir();
		}
		try {
			//4.使用apache文件上传组件处理文件步骤
			//4.1 创建一个DiskFileItemFactory
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//4.2 创建一个文件上传解码器
			ServletFileUpload upload = new ServletFileUpload(factory);
			//4.3解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			//4.4 判断提交上来的数据是否是上传表单的数据
			if(!ServletFileUpload.isMultipartContent(request)) {
				return;
			}
			//5.使用ServletFileUpload解析器上传数据
				List<FileItem> list = upload.parseRequest(request);
				//6.遍历 处理文件
				for(FileItem item:list) {
					String filename = item.getName();
					//System.out.println(filename+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					if(filename==null||filename.trim().equals("")) {
						continue;
					}
					//注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);
                    //System.out.println(filename+"222222222222222222222222222222222222222");
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //创建一个文件输出流
                    returnPath = filename;
                    FileOutputStream out = new FileOutputStream(savePath+"\\"+filename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while ((len = in.read(buffer)) > 0) {
                    	//System.out.println("是否写入进了服务器！！！");
                        out.write(buffer, 0, len);
				}
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    Map<String, Object> map = new HashMap<String, Object>();
                	map.put("code", 0);
                	map.put("msg", "我从upload的servlet中返回了");
                	Data data = new Data();
                	data.setReturnPath(returnPath);
                	map.put("data", data);
                	String jsonStr = JsonUtil.beanToString(map);
        			//System.out.println(jsonStr);
        			writer= response.getWriter();
        			writer.write(jsonStr);	
				}	
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
