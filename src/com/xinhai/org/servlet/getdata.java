package com.xinhai.org.servlet;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
 * Servlet implementation class getdata
 */
@WebServlet("/getdata")

public class getdata extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getdata() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		String method = request.getParameter("method");
		String info = null;
		String otherinfo = null;
		int count00xxxxxx = 0;
		int count = 0;
		int incount = 0;
		int outcount = 0;
		int overtimecount = 0;
		int abovertimecount = 0;
		int bcovertimecount = 0;
		int overtimenextcount = 0;
		int errorcount = 0;
		int mistakecount = 0;
		int misiocount = 0;
		String last20info = null;
		
		int row=10; //设置表格的一页的对象数量
		int allpage=0; //声明总页数
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<shuju> lessonList = new ArrayList<shuju>();
		
		try {
			switch (method) {
			case "xuce":
				info = xuze();
				break;
			case "shiyujie":
				map = shiyujie();
				count00xxxxxx = (int) map.get("count00xxxxxx");
				count = (int) map.get("count");
				incount = (int) map.get("incount");
				outcount = (int) map.get("outcount");
				overtimecount = (int) map.get("overtimecount");
				abovertimecount = (int) map.get("abovertimecount");
				bcovertimecount = (int) map.get("bcovertimecount");
				overtimenextcount = (int) map.get("overtimenextcount");
				errorcount = (int) map.get("errorcount");
				mistakecount = (int) map.get("mistakecount");
				misiocount = (int) map.get("misiocount");
				// last20info=(String) map.get("last20info");

				break;
//			case "shujuchengxian":
//				map = shujuchengxian();
//				last20info = (String) map.get("last20info");
//				break;
				
			 case "shujuchengxian":
			 lessonList = chengxian();		 
//			 for(int i=0;i<lessonList.size();i++){
//				 System.out.println(lessonList.get(i).getTime());
//			 }
			 break;
			 default:
				 
			 break;
		// System.out.println("得到了list");
			// for(int i=0;i<lessonList.size();i++){
			// System.out.println("得到了id"+lessonList.get(i).getId());
			// }
			// //last20info=(String) map.get("last20info");
			// break;
			}

			// System.out.println("此次获取的数据:
			// "+state+"----"+explain+"-----"+value);
			// System.out.println("此次获取的值的集合: "+value.get);
			// //JSONObject values = JSONObject.parseObject(value);

			if (incount != 0) {

				otherinfo = " a点进入的人通过的次数为： " + incount + " c点进入出去的人通过的次数为    " + outcount + " ab段超时的次数： "
						+ abovertimecount + "   bc段超时的次数" + bcovertimecount + "  异常的次数(没有从abc cab顺利通过的所有数据)："
						+ errorcount;

				request.setAttribute("msg", info);
				request.setAttribute("othermsg", otherinfo);
				request.setAttribute("count", count);
				request.setAttribute("count00xxxxxx", count00xxxxxx);
				request.setAttribute("overtimecount", overtimecount);
				request.setAttribute("mistakecount", mistakecount);
				request.setAttribute("incount", incount);
				request.setAttribute("outcount", outcount);
				request.setAttribute("abovertimecount", abovertimecount);
				request.setAttribute("bcovertimecount", bcovertimecount);
				request.setAttribute("misiocount", misiocount);
				// request.setAttribute("last20info", last20info);
				request.getRequestDispatcher("msg.jsp").forward(request, response);
			}
	
			//if (last20info != null) {
			else if (lessonList != null) {
				
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
				
				request.getSession().removeAttribute("list");
				
				request.getSession().setAttribute("relist", lessonList);
				
				request.setAttribute("list", lessonList);	
				request.getRequestDispatcher("shujumsg.jsp").forward(request, response);

			}

			// if (lessonList!=null && lessonList.size() != 0) {
			// request.setAttribute("list", lessonList);
			// request.getRequestDispatcher("shujumsg.jsp").forward(request,
			// response);
			//
			// }

		} catch (Exception e) {
			e.printStackTrace();
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

	public String xuze() {
		String info = null;
		// System.out.println("开始获取数据");
		try {
			// String sms= SMS.SetSMS();
			System.out.println("开始获取数据");
			String sms = SMS.SetSMS();

			JSONObject obj = JSONObject.parseObject(sms);

			JSONArray array = obj.getJSONArray("value");

			for (int k = 0; k < array.size(); k++) {
				JSONObject kobject = array.getJSONObject(k);
				// if (kobject.getString("info").equals("DevId=1,Count=3")) {
				// System.err.println("--------"+kobject.getString("id"));

				// }
				String infomation = kobject.getString("info");
				if (infomation.indexOf("Count") != -1) {
					// System.out.println("包含count");
					info = kobject.getString("info");
				}
			}
			System.out.println("最终记录为： " + info);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;

	}

	public Map<String, Object> shiyujie() {
		// String info = null;
		int count00xxxxxx = 0;
		int incount = 0;
		int outcount = 0;
		int misiocount = 0;
		int abovertimecount = 0;
		int errorcount = 0;
		int bcovertimecount = 0;
		int overtimenextcount = 0;
		int overtimecount = 0;
		int mistakecount = 0;
		int count = 0;
		// String last20info="";
		// System.out.println("开始获取数据");
		try {
			// String sms= SMS.SetSMS();
			System.out.println("开始获取数据");
			String sms = SMS.SetSMS();
			JSONObject obj = JSONObject.parseObject(sms);
			JSONArray array = obj.getJSONArray("value");
			for (int k = 0; k < array.size(); k++) {
				JSONObject kobject = array.getJSONObject(k);
				// if (kobject.getString("info").equals("DevId=1,Count=3")) {
				// System.err.println("--------"+kobject.getString("id"));

				// }

				String infomation = kobject.getString("info");
				// long time = kobject.getLongValue("time");
				/// time.toString;
				//
				// SimpleDateFormat formatter = new
				// SimpleDateFormat("yyyy/MM/dd/HH:mm");
				// formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
				// String hms = formatter.format(time);
				// System.out.println("time："+hms);

				// 采集最新的数据
				// if((k+19)> array.size()){
				// //last20info =" id:"+kobject.getString("id")+" time: "+"
				// info: "+kobject.getString("info");
				// last20info=last20info+kobject.toString();
				// }
				// 查询总有效记录
				if (infomation.indexOf("seq") != -1) {
					count++;
				}
				// 查询00开头的报文(正常情况按00发送)
				if (infomation.indexOf("seq=00") != -1) {
					count00xxxxxx++;
				}
				// 查询11开头的报文(正常情况按11发送)
				if (infomation.indexOf("seq=11") != -1) {

					mistakecount++;

				}
				// 查询正常abc 通过的数据条数
				if (infomation.indexOf("seq=00111001") != -1) {

					incount++;

				}
				// 查询正常cba通过的数据条数
				if (infomation.indexOf("seq=00011011") != -1) {

					outcount++;

				}
				// 正常报文中不是 abc cba的报文数量
				if (infomation.indexOf("seq=00") != -1) {
					if (infomation.indexOf("seq=00111001") == -1 || infomation.indexOf("seq=00011011") != -1) {
						misiocount++;
					}

				}
				if (infomation.indexOf("seq=01") != -1) {

					overtimecount++;

				}
				if (infomation.indexOf("seq=01000001") != -1 || infomation.indexOf("seq=01001011") != -1) {

					abovertimecount++;
					// System.out.println("找到一条abc 当前的数量是:" +scount);
					// System.out.println("包含count");
					// info = kobject.getString("info");
				}
				if (infomation.indexOf("seq=01001001") != -1 || infomation.indexOf("seq=01000011") != -1) {

					bcovertimecount++;
				}
				if (infomation.indexOf("DevID=1,seq=") != -1) {
					if (infomation.indexOf("seq=00111001") == -1 || infomation.indexOf("seq=00011011") == -1) {
						errorcount++;
					}
				}
				if (infomation.indexOf("seq=10") != -1) {

					overtimenextcount++;
				}

			}
			System.out.println("最终进入数量为： " + incount + "最终出去的数量为：" + outcount + "在a超时:" + abovertimecount);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("count00xxxxxx", count00xxxxxx);
		map.put("count", count);
		map.put("incount", incount);
		map.put("outcount", outcount);
		map.put("misiocount", misiocount);
		map.put("overtimecount", overtimecount);
		map.put("abovertimecount", abovertimecount);
		map.put("bcovertimecount", bcovertimecount);
		map.put("overtimenextcount", overtimenextcount);
		map.put("errorcount", errorcount);
		map.put("mistakecount", mistakecount);
		// map.put("last20info", last20info);

		return map;

	}

	public Map<String, Object> shujuchengxian() {

		String last20info = "";
		// System.out.println("开始获取数据");
		try {
			// String sms= SMS.SetSMS();
			System.out.println("开始获取数据");
			String sms = SMS.SetSMS();

			JSONObject obj = JSONObject.parseObject(sms);

			JSONArray array = obj.getJSONArray("value");

			for (int k = 0; k < array.size(); k++) {
				JSONObject kobject = array.getJSONObject(k);
				// if (kobject.getString("info").equals("DevId=1,Count=3")) {
				// System.err.println("--------"+kobject.getString("id"));

				// }

				String infomation = kobject.getString("info");
				// long time = kobject.getLongValue("time");
				/// time.toString;
				//
				// SimpleDateFormat formatter = new
				// SimpleDateFormat("yyyy/MM/dd/HH:mm");
				// formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
				// String hms = formatter.format(time);
				// System.out.println("time："+hms);

				// 采集最新的数据
				if ((k + 19) > array.size()) {
					// last20info =" id:"+kobject.getString("id")+" time: "+"
					// info: "+kobject.getString("info");
					last20info = last20info + kobject.toString() + ",";
				}

			}
			last20info = last20info.substring(0, last20info.length() - 1);
			System.out.println("输出完成");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("last20info", last20info);

		return map;

	}

	public  List<shuju> chengxian() {
		List<shuju> lessonList = new ArrayList<shuju>();
		int id = 0;
		String infomation = null;
		String time = null;
		int j=0;
		long timeDate =0;
		String newtime=null;
		String Status=null;
		try {
			System.out.println("开始获取数据");
			String sms = SMS.SetSMS();
			JSONObject obj = JSONObject.parseObject(sms);
			JSONArray array = obj.getJSONArray("value");
			
//			JSONObject kobject1 = array.getJSONObject(3707);
//			int id12 = kobject1.getInteger("id");
//			System.out.println(id12);
			
	//		for (int k = 0; k < array.size(); k++) {
			for (int k = array.size(); k > 0; k--) {
				//System.out.println("k:"+k);
				JSONObject kobject = array.getJSONObject(k-1);
				infomation = kobject.getString("info");
				
				if (infomation.indexOf("seq") != -1 ) {
					// 采集最新的数据
					
					//infomation = kobject.getString("info");
					id=kobject.getInteger("id");
					time =kobject.getString("time");	
					timeDate=Long.parseLong(time);
					newtime=zhuanhuan(timeDate);
					String[] strs=infomation.split(",");
					String secordspit=strs[1];
					String b=secordspit.substring(secordspit.length()-8,secordspit.length());
					
					Status=State(b);
					shuju sj = new shuju();
					sj.setId(id);	
					String pareingABC=shunxu(b);
				
					sj.setInfo(pareingABC);
					sj.setState(Status);
					sj.setTime(newtime);
					lessonList.add(sj);	
//						j++;		
				}
//				if(j==20){
//					break;
//				}
			}
			System.out.println("输出完成");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lessonList;
	}
public  String zhuanhuan(long str){
	String a=null;
	
	//1527064384290
	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	//long now = System.currentTimeMillis();
	Calendar calendar = Calendar.getInstance();
	calendar.setTimeInMillis(str*1000);
	a=formatter.format(calendar.getTime());
	return a;
	
}
public  String State(String str){
	String state=null;
	String str2=null;
	if(str.length()>2){
	str2=str.substring(0,2);
	 int a = Integer.parseInt(str2);
	// System.out.println("a:"+a);
	if(str2!=null && a==0){
		state="正常";
	}
	else if(str2!=null && a==1){
		state="超时";
	}
	else if(str2!=null && a==10){
		state="超时后下一条";
	}
	else{
		state="错误";	
	}}
	else{
		state="错误";	
	}
	//String str6=str.substring(str.length()-6,str.length());	
	return state;
}
public  String shunxu(String str){
	String ABC=null;
	String str6=null;
	if(str!=null&&str.length()==8){
		str6=str.substring(2,str.length());
		ABC=str6;
	}
	else{
		ABC="error";	
	}
	ABC=parsingABC(ABC);
	return ABC;
}

public   String parsingABC(String str){
	String strABC="";
	String newstr=null;
	
	if(str!=null&&str.length()==6){
		for(int i=0;i<6;i=i+2){
			newstr=str.substring(i,i+2);
			strABC=zhuanma(newstr)+strABC;
		//	 System.out.println(newstr);
		}	
		//System.out.println("2:"+str12+" 4:"+str34+" 6:"+str56);
	}
	else{
		strABC="error";	
	}
	return strABC;
}

public  String zhuanma(String str){
	String abc=null;
	if(str.equals("01")==true){
		abc="A";
	}
	else if(str.equals("10")==true){
		abc="B";
	}
	else if(str.equals("11")==true){
		abc="C";
	}
	else if(str.equals("00")==true){
		abc="";
	}
	else{
		
		abc="error";
	}
	return abc;
}

	public static void main(String[] args)  {
	
	}
}
