package com.xinhai.org.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinhai.org.entity.shuju;

/**
 * Servlet implementation class time
 */
@WebServlet("/time")
public class time extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public time() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String state=null;
		String Starttime=null;
		String Endtime=null;
		int row=10;
		int allpage=0;
		state=request.getParameter("state");
		Starttime=request.getParameter("Starttime");
	    Endtime=request.getParameter("Endtime");
		Starttime=Starttime+" 00:00:00";
		Endtime=Endtime+" 23:59:59";
		int start=0;
		int end=0;
		System.out.println(Starttime+"----"+Endtime);
		if(state.equals("-1")==true){
			state="";
		}
		if(Starttime.length()<19){
			start=0;
		}else{
		start=StringDateint(Starttime);}
		if(Endtime.length()<19){
			end=0;
		}else{
		end=StringDateint(Endtime);
		}
		System.out.println(start+"----"+end);
		
		  List<shuju> lessonList = new ArrayList<shuju>();
		  lessonList=Methods.SearchAll(state, start, end);
//			 for(int i=0;i<lessonList.size();i++){
//			 System.out.println(lessonList.get(i).getId());
//	    }
//			 
		  request.setAttribute("state",state);
		  if(Starttime.length()<11){
			  request.setAttribute("Starttime",null);
		  }
		  else{
		  request.setAttribute("Starttime", Starttime.substring(0, 10));}
		  if(Endtime.length()<11){
			  request.setAttribute("Endtime",null);
		  }
		  else{
		  request.setAttribute("Endtime", Endtime.substring(0, 10));}
			 
			 request.setAttribute("page", 1);
				request.setAttribute("row", row);
				if(lessonList.size()%row != 0){
					allpage=(lessonList.size()/row)+1;
				}
				else
				{
					allpage=lessonList.size()/row;
				}
				
				request.setAttribute("allpage",allpage);
				request.getSession().removeAttribute("relist");
				request.getSession().setAttribute("relist", lessonList);
				request.setAttribute("list", lessonList);	
				request.getRequestDispatcher("shujumsg.jsp").forward(request, response); 
			 
		
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
public static int StringDateint(String str){
	int shijiancuo=0;
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 Date result=null;
	 try {
		result = sdf.parse(str); 
		System.out.println(result);
		shijiancuo=(int)(result.getTime()/1000);
//		System.out.println(shijiancuo);
//		System.out.println("Starttime:"+sdf.parse(Starttime));
//		System.out.println("Endtime:"+sdf.parse(Endtime));
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return shijiancuo;
}

//public static void main(String[] args) {
//	int a=StringDateint("2018-05-22 23:59:59");
//	System.out.println(a);
//}
}
