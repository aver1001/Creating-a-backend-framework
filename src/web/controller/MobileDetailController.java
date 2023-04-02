package web.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import web.model.service.MobileService;
import web.model.service.MobileServiceImpl;
import web.model.vo.Mobile;
import web.util.MyException;

public class MobileDetailController implements Controller{
	MobileService mobileService;
	
	public MobileDetailController() {
		mobileService = new MobileServiceImpl();
		
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response,JsonObject json,Gson gson, JsonObject retJson) throws ServletException, IOException, MyException {
		String mobileCode = json.get("mobileCode").getAsString();
		Mobile m = mobileService.detailMobile(mobileCode); //모바일 코드가 json text 형태 
		// key에는 String, value에는 ""가 있으면 이것은 스트링임
		// js에서는 텍스트형태로 받음, 즉, 이 안에 있는 것을 JavaScript Object로 받을 수 없음
		// 즉, " "이 아닌 [] 형태로 넘겨야 함
		retJson.add("data",  gson.toJsonTree(m));
		
	}
	

}
