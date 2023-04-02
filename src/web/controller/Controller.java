package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import web.util.MyException;

public interface Controller {
	public void process(HttpServletRequest request, HttpServletResponse response,JsonObject json,Gson gson, JsonObject retJson) throws ServletException, IOException, MyException;
}
