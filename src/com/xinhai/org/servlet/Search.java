package com.xinhai.org.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xinhai.org.entity.shuju;
import com.xinhai.org.until.SMS;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		int row=10; //设置表格的一页的对象数量
		int allpage=0; //声明总页数
		
//		int page = Integer.parseInt(request.getParameter("page"));
//		int row = Integer.parseInt(request.getParameter("row"));
//		int allpage = Integer.parseInt(request.getParameter("allpage"));
	//	String method=request.getParameter("method");
	    String time="0";
	    String state=request.getParameter("state");
	//    System.out.println(page+"---"+row+"---"+allpage+"---"+time+"---"+state+"----"+method);
	    System.out.println("state:"+state+"time:"+time);
	  
	    List<shuju> lessonList = new ArrayList<shuju>();
	    if(time!=null && state!=null){
	    lessonList=Methods.Search(state, time);	
	    }
	  
//	    for(int i=0;i<lessonList.size();i++){
//			 System.out.println("---"+lessonList.get(i).getId());
//		 }
	    
	    
	    
	    
		if(lessonList.size()%row != 0){
		allpage=(lessonList.size()/row)+1;
		}
		else
		{
			allpage=lessonList.size()/row;
		}
		
		
		request.setAttribute("time",time);
		request.setAttribute("state",state);
		request.setAttribute("page", 1);
		request.setAttribute("row", row);
		request.setAttribute("allpage",allpage);	
		request.getSession().removeAttribute("list");		
		request.setAttribute("list", lessonList);
		request.setAttribute("msg", "succeed");
		
		
	//	objec->json
	//	request.getRequestDispatcher("shujumsg.jsp").forward(request, response);
		request.getRequestDispatcher("shujumsg.jsp").forward(request, response);
	//	PrintWriter writer = response.getWriter();
	//	writer.print(json);
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	
	
	
	
	
	
}
