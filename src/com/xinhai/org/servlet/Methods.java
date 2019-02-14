package com.xinhai.org.servlet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xinhai.org.entity.shuju;
import com.xinhai.org.until.SMS;

/**
* @author Tony
* @version 创建时间：2018年5月24日 下午2:30:16
* @ClassName 类名称
* @Description 类描述
*/
public class Methods {


	public static  List<shuju> Search(String stateConditions,String timeConditions) {
		
		List<shuju> lessonList = new ArrayList<shuju>();
		int id = 0;
		String infomation = null;
		String time = null;
		long timeDate =0;
		String newtime=null;
		String Status=null;
		try {
			System.out.println("开始获取数据");
			String sms = SMS.SetSMS();
			JSONObject obj = JSONObject.parseObject(sms);
			JSONArray array = obj.getJSONArray("value");
			System.out.println("时间条件："+timeConditions);
			for (int k = array.size(); k > 0; k--) {
				JSONObject kobject = array.getJSONObject(k-1);
				infomation = kobject.getString("info");
				
				if(stateConditions!=null){
					
				 if (infomation.indexOf("seq") != -1 && infomation.indexOf("seq="+stateConditions) != -1) {
					// 采集最新的数据
					
					id=kobject.getInteger("id");
				//	System.out.println("这次00报文开头的id: "+id);
					time =kobject.getString("time");	
					timeDate=Long.parseLong(time);
					
					String b=infomation.substring(infomation.length()-8,infomation.length());
					Status=State(b);
					shuju sj = new shuju();
					sj.setId(id);	
					String pareingABC=shunxu(b);
					
					sj.setInfo(pareingABC);
					sj.setState(Status);
					newtime=zhuanhuan(timeDate);				
					sj.setTime(newtime);
					lessonList.add(sj);	
				}}
			}
			System.out.println("输出完成");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lessonList;
	}

	
	
	public static  List<shuju> Searchtimenowday() {
		List<shuju> lessonList = new ArrayList<shuju>();
		int id = 0;
		String infomation = null;
		String time = null;
		long timeDate =0;
		String newtime=null;
		String Status=null;
		try {
			System.out.println("开始获取数据");
			String sms = SMS.SetSMS();
			JSONObject obj = JSONObject.parseObject(sms);
			JSONArray array = obj.getJSONArray("value");
			for (int k = array.size(); k > 0; k--) {
				JSONObject kobject = array.getJSONObject(k-1);
				infomation = kobject.getString("info");
				 if (infomation.indexOf("seq") != -1) {
					// 采集最新的数据
					id=kobject.getInteger("id");
					time =kobject.getString("time");	
					timeDate=Long.parseLong(time);
					newtime=zhuanhuan(timeDate);
					String b=infomation.substring(infomation.length()-8,infomation.length());
					Status=State(b);
					shuju sj = new shuju();
					sj.setId(id);	
					String pareingABC=shunxu(b);		
					sj.setInfo(pareingABC);
					sj.setState(Status);
					sj.setTime(newtime);
					String nowday=nowdaytime();
					if(nowday.equals(newtime.substring(0,10))==true){
					System.out.println("这次时间: "+newtime);
					lessonList.add(sj);	
					}
				}}			
			System.out.println("输出完成");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lessonList;
	}
	
	
	
	
	public static  String zhuanhuan(long str){
		String a=null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(str*1000);
		a=formatter.format(calendar.getTime());
		return a;
	}
	public static  String State(String str){
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
	public static  String shunxu(String str){
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

	public static  String parsingABC(String str){
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

	public static  String zhuanma(String str){
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

	public static String nowdaytime(){
		String nowday=null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
	     String now=df.format(System.currentTimeMillis());
	//	 System.out.println(now);		 
		  nowday=now.substring(0,10);
//		  System.out.println(nowyear);
//		  System.out.println(nowmonth);
//		  System.out.println(nowday);
		return nowday;
	}
	
	
	
public static  List<shuju> SearchAll(String stateConditions,int start,int end) {
		
		List<shuju> lessonList = new ArrayList<shuju>();
		int id = 0;
		String infomation = null;
		String time = null;
		long timeDate =0;
		String newtime=null;
		String Status=null;
		int infotime=0;
		try {
			System.out.println("开始获取数据");
			String sms = SMS.SetSMS();
			JSONObject obj = JSONObject.parseObject(sms);
			JSONArray array = obj.getJSONArray("value");
	
			for (int k = array.size(); k > 0; k--) {
				JSONObject kobject = array.getJSONObject(k-1);
				infomation = kobject.getString("info");
				
				if(stateConditions!=null){
					
				 if (infomation.indexOf("seq") != -1 && infomation.indexOf("seq="+stateConditions) != -1) {
					// 采集最新的数据
					
					id=kobject.getInteger("id");
					//System.out.println("这次00报文开头的id: "+id);
					time =kobject.getString("time");	
					timeDate=Long.parseLong(time);
				
					String b=infomation.substring(infomation.length()-8,infomation.length());
					Status=State(b);
					shuju sj = new shuju();
					sj.setId(id);	
					String pareingABC=shunxu(b);
					
					sj.setInfo(pareingABC);
					sj.setState(Status);
					newtime=zhuanhuan(timeDate);				
					sj.setTime(newtime);
					if(time.length()<=10){
					infotime=Integer.parseInt(time);
					
					
					if(start!=0&&end==0){
						if(infotime>=start){	
						      lessonList.add(sj);	
					   }
					}
					else if(start==0&&end!=0){
						if(infotime<=end){	
						 lessonList.add(sj);	
						}
					}
					else if(start!=0&&end!=0){
					  if(infotime>=start&&infotime<=end){	
					      lessonList.add(sj);	
					   }
					}
					 else if(start==0&&end==0){
					      lessonList.add(sj);	
					   }
					
					}
				}}
			}
			System.out.println("输出完成");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lessonList;
	}

	
	
	
	
	public static void main(String[] args) {
		  List<shuju> lessonList = new ArrayList<shuju>();
		  lessonList=SearchAll("01",0, 0);
		for(int i=0;i<lessonList.size();i++){
			System.out.println(lessonList.get(i).getId());
		}
//		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
//		  String now=df.format(System.currentTimeMillis());
//		  System.out.println(now);
//		  String nowyear=now.substring(0,4);
//		  String nowmonth=now.substring(5,7);
//		  String nowday=now.substring(0,10);
//		  System.out.println(nowyear);
//		  System.out.println(nowmonth);
//		  System.out.println(nowday);
	}
	
}
