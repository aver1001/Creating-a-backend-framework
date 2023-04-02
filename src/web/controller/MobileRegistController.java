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

public class MobileRegistController implements Controller{
	MobileService mobileService;
	
	public MobileRegistController() {
		mobileService = new MobileServiceImpl();
		
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response,JsonObject json,Gson gson, JsonObject retJson) throws ServletException, IOException, MyException {
		String mobileCode = json.get("mobileCode").getAsString();
		String model = json.get("model").getAsString();
		int price = json.get("price").getAsInt();
		String company = json.get("company").getAsString();
		Mobile m = new Mobile(mobileCode, model, price, company);
		mobileService.registMobile(m);
		ArrayList<Mobile> list=mobileService.mobileList();
		retJson.add("data", gson.toJsonTree(list));
	}
	

}
