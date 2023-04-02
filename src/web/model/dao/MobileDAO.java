package web.model.dao;

import java.util.ArrayList;

import web.model.vo.Mobile;
import web.util.MyException;

public interface MobileDAO {

	public ArrayList<Mobile> mobileList() throws MyException;

	public Mobile detailMobile(String mobileCode) throws MyException;

	public int deleteMobile(String mobileCode) throws MyException;

	public void registMobile(Mobile m) throws MyException;

}
