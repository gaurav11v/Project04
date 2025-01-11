package in.co.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.DoctorBean;
import in.co.rays.bean.PurchaseBean;
import in.co.rays.util.JDBCDataSource;

public class DoctorModel {

	public Integer nextPk() throws Exception {

		int pk = 0;
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_doctor");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			pk = rs.getInt(1);
		}
		JDBCDataSource.closeConnection(conn);
		return pk + 1;

	}

	public void add(DoctorBean bean) throws Exception {

		int pk = nextPk();

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("insert into st_doctor values(?, ?, ?, ?, ?)");

		// pstmt.setLong(1, bean.getId()); removed, bcoz nextpk is set
		pstmt.setInt(1, pk);
		pstmt.setString(2, bean.getName());
		pstmt.setDate(3, new Date(bean.getDob().getTime()));
		pstmt.setString(4, bean.getMobile());
		pstmt.setString(5, bean.getExpertise());
		

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);
		System.out.println("Data added successfully = " + i);

	}

	
	public void update(DoctorBean bean) throws Exception {

		int pk = nextPk();
		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_doctor set name = ?, dob = ?, mobile = ?, expertise = ? where id = ?");

		pstmt.setString(1, bean.getName());
		pstmt.setDate(2, new Date(bean.getDob().getTime()));
		pstmt.setString(3, bean.getMobile());
		pstmt.setString(4, bean.getExpertise());
		pstmt.setLong(5, bean.getId());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("Data updated Successfully = " + i);
	}


	public void delete(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from st_doctor where id = ?");

		pstmt.setLong(1, id);

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("Data Deleted = " + i);

	}

	public List list() throws Exception {
		System.out.println("in list method>>>>>>>>>>>>>>>");
		return search(null, 0, 0);
	}

	public List search(DoctorBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		StringBuffer sql = new StringBuffer("select * from st_doctor where 1=1");

		if (bean != null) {
			
			if (bean.getExpertise() != null && bean.getExpertise().length() > 0) {
				sql.append(" and expertise like '" + bean.getExpertise() + "'");
			}
			
		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println("sql = " + sql.toString());

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		List list = new ArrayList();

		while (rs.next()) {

			bean = new DoctorBean();

			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDob(rs.getDate(3));
			bean.setMobile(rs.getString(4));
			bean.setExpertise(rs.getString(5));
			

			list.add(bean);
		}
		JDBCDataSource.closeConnection(conn);
		return list;

	}

	public DoctorBean findByPk(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_doctor where id = ?");

		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		DoctorBean bean = null;

		while (rs.next()) {
			bean = new DoctorBean();

			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDob(rs.getDate(3));
			bean.setMobile(rs.getString(4));
			bean.setExpertise(rs.getString(5));
			
		}
		JDBCDataSource.closeConnection(conn);
		return bean;
	}

}
