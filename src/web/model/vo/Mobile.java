package web.model.vo;

import web.util.MyException;

public class Mobile {
	private String mobileCode, model;
	private int price;
	private String company;
	public Mobile(String mobileCode, String model, int price, String company) throws MyException {
		setMobileCode(mobileCode);
		setModel(model);
		setPrice(price);
		setCompany(company);
	}
	
	public Mobile(String mobileCode, String model, int price, String company, boolean flag) throws MyException {
		if(flag) { //DB 에서 올 때는 flag가 true, 즉, DB에서 올 때는 유효성 검사 없이 바로 넣어주겠습니다. setMethod는 null값 체크를 하므로.
			this.mobileCode = mobileCode;
			this.model = model;
			this.price = price;
			this.company = company;
		}
	}
	
	public String getMobileCode() {
		return mobileCode;
	}
	public void setMobileCode(String mobileCode) throws MyException {
		if(mobileCode != null) {
			this.mobileCode = mobileCode;
		}else {
			throw new MyException("mobileCode 제대로 넣어주세요");
		}
		
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
}
