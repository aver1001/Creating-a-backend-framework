package web.model.service;

import java.util.ArrayList;

import web.model.vo.Mobile;
import web.util.MyException;

public interface MobileService {

	ArrayList<Mobile> mobileList() throws MyException;

	Mobile detailMobile(String mobileCode) throws MyException;

	int deleteMobile(String mobileCode)throws MyException;

	void registMobile(Mobile m)throws MyException;

}
