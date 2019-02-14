package com.xinhai.org.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinhai.org.entity.shuju;

/**
 * Servlet implementation class fenye
 */
@WebServlet("/fenye")
public class fenye extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fenye() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String Starttime=request.getParameter("StartTime");
		String Endtime=request.getParameter("EndTime");
		String state=request.getParameter("state");
		
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("row"));
		int allpage = Integer.parseInt(request.getParameter("allpage"));
		String method=request.getParameter("method");
		//List<shuju> lessonList = new ArrayList<shuju>();
		
	
		
	    
		List<shuju> lessonList = (List<shuju>) request.getSession().getAttribute("relist");
		//System.out.println("//////////"+"00000");
		System.out.println("------"+lessonList.size()+"");
		System.out.println("method:"+method);
		System.out.println("page:"+page);
		System.out.println("row:"+row);
		System.out.println("allpage:"+allpage);
		System.out.println("Starttime:"+Starttime);
		System.out.println("Endtime:"+Endtime);
		try {
		switch (method) {
		case "pgup":
		if(page>1){
			page=page-1;
		}
		break;
		case "pgdn":
			if(page>=1 && page<allpage){
				page=page+1;	
			}
			break;
		case "first":
			if(page!=1 && allpage>0){
				page=1;	
			}
			break;
		case "final":
			if(page!=allpage && allpage>0){
				page=allpage;	
			}
			break;
			}
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("state",state);
		request.setAttribute("Starttime",Starttime);
		request.setAttribute("Endtime",Endtime);
		request.setAttribute("list",lessonList);
		request.setAttribute("page",page);
		request.setAttribute("row", row);
		request.setAttribute("allpage", allpage);
		request.getRequestDispatcher("shujumsg.jsp").forward(request, response);
		}
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
