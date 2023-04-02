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

public class MobileListController implements Controller{
	MobileService mobileService;
	
	public MobileListController() {
		mobileService = new MobileServiceImpl();
		
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response,JsonObject json,Gson gson, JsonObject retJson) throws ServletException, IOException, MyException {
		ArrayList<Mobile> list = mobileService.mobileList();
		retJson.add("data", gson.toJsonTree(list));
		
	}
	

}
