package web.model.service;

import java.util.ArrayList;

import web.model.dao.MobileDAO;
import web.model.dao.MobileDAOImp;
import web.model.vo.Mobile;
import web.util.MyException;

public class MobileServiceImpl implements MobileService {

	MobileDAO mobileDAO;

	
	public MobileServiceImpl() {
		mobileDAO = new MobileDAOImp();
	}


	public ArrayList<Mobile> mobileList() throws MyException{
		return mobileDAO.mobileList();
	}


	@Override
	public Mobile detailMobile(String mobileCode) throws MyException {
		return mobileDAO.detailMobile(mobileCode);
	}


	@Override
	public int deleteMobile(String mobileCode) throws MyException {
		return mobileDAO.deleteMobile(mobileCode);
	}


	@Override
	public void registMobile(Mobile m) throws MyException {
		mobileDAO.registMobile(m);
		
	}
}
