package web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import web.util.MyException;

@WebServlet(urlPatterns= {"/main", "/a", "/b"})
public class MainServlet extends HttpServlet {
	
	HashMap<String, Controller> beans;
	
	public void init() throws ServletException {
		try {
			beans = new XmlBeanFactory(getServletContext().getRealPath("/WEB-INF/Beans.xml")).beans;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		JsonObject json = (JsonObject) JsonParser.parseReader(request.getReader()); // 상속관계에 있기 때문에 캐스팅해서 사용, chain을 거는 것 
		String sign = json.get("sign").getAsString();
		Gson gson = new Gson();
		JsonObject retJson = new JsonObject();
		
		System.out.print(sign);
		try {
			if(sign!=null) {				
					beans.get(sign).process(request, response, json, gson, retJson);
				}
			else {
				
			}
		}catch(MyException e) {
			retJson.addProperty("msg", e.getMessage()); //exception message 
		}
		out.append(retJson.toString());
		out.close(); //out버퍼에 들어간 내용이 딱 닫아지면서 웹서버에서 텍스트로 전달됨 
		
	}// end process

}
