package web.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import web.model.vo.Mobile;
import web.util.DBUtil;
import web.util.MyException;

public class MobileDAOImp implements MobileDAO{

	DBUtil util;
	public MobileDAOImp() {
		util = DBUtil.getInstance();
	}
	@Override
	public ArrayList<Mobile> mobileList() throws MyException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con=util.getConnection();
			String sql = "select * from mobile";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			ArrayList<Mobile> list = new ArrayList<>();
			while(rs.next()) {
				String mobileCode = rs.getString("mobileCode");
				String model = rs.getString("model");
				int price = rs.getInt("price");
				String company = rs.getString("company");
				list.add(new Mobile(mobileCode, model, price, company));
			}
			System.out.println(list);
			return list; // 비어있거나 채워져있거나 
			
		}catch(SQLException e) {
			throw new MyException("error");
		}finally{
			util.close(rs, stmt, con);
		}
	}
	@Override
	public Mobile detailMobile(String mobileCode) throws MyException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con=util.getConnection();
			String sql = "select * from mobile where mobileCode=?"; // column 이 굉장이 많은 경우 *는 비효율 
			stmt = con.prepareStatement(sql);
			stmt.setString(1, mobileCode);
			rs = stmt.executeQuery();
			ArrayList<Mobile> list = new ArrayList<>();
			while(rs.next()) {
				String model = rs.getString("model");
				String company = rs.getString("company");
				int price = rs.getInt("price");
				return new Mobile(mobileCode, model, price, company, true);
			}
			return null; // 비어있거나 채워져있거나 
			
		}catch(SQLException e) {
			throw new MyException("모바일 상세 조회 실패 ");
		}finally{
			util.close(rs, stmt, con);
		}
	}
	@Override
	public int deleteMobile(String mobileCode) throws MyException {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con=util.getConnection();
			String sql = "delete from mobile where mobileCode=?"; // column 이 굉장이 많은 경우 *는 비효율 
			stmt = con.prepareStatement(sql);
			stmt.setString(1, mobileCode);
		return stmt.executeUpdate();
			
		}catch(SQLException e) {
			throw new MyException("모바일 삭제 실패");
		}finally{
			util.close(rs, stmt, con);
		}
	}
	
	@Override
	public void registMobile(Mobile m) throws MyException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con=util.getConnection();
			String sql = "insert into mobile(mobileCode, model, price, company) values(?, ?, ?, ?)"; // column 이 굉장이 많은 경우 *는 비효율 
			stmt = con.prepareStatement(sql);
			stmt.setString(1, m.getMobileCode());
			stmt.setString(2,  m.getModel());
			stmt.setInt(3, m.getPrice());
			stmt.setString(4, m.getCompany());
			
			stmt.executeUpdate();
			
			
		}catch(SQLException e) {
			throw new MyException("모바일 등 실패");
		}finally{
			util.close(stmt, con);
		}
	}

}
