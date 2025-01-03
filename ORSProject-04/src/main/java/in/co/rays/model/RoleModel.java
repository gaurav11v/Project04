package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.DropdownListBean;
import in.co.rays.bean.RoleBean;
import in.co.rays.util.JDBCDataSource;

public class RoleModel {

	public Integer nextPk() throws Exception {

		int pk = 0;
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_role");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			pk = rs.getInt(1);
		}
		JDBCDataSource.closeConnection(conn);
		return pk + 1;

	}

	public void add(RoleBean bean) throws Exception {

		RoleBean existbean = findByName(bean.getName());

		if (existbean != null) {

			throw new Exception("name already exist");

		}

		int pk = nextPk();

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("insert into st_role values(?, ?, ?, ?, ?, ?, ?)");

		// pstmt.setLong(1, bean.getId()); removed, bcoz nextpk is set
		pstmt.setInt(1, pk);
		pstmt.setString(2, bean.getName());
		pstmt.setString(3, bean.getDescription());
		pstmt.setString(4, bean.getCreatedBy());
		pstmt.setString(5, bean.getModifiedBy());
		pstmt.setTimestamp(6, bean.getCreatedDateTime());
		pstmt.setTimestamp(7, bean.getModifiedDateTime());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);
		System.out.println("Data added successfully = " + i);

	}

	public void update(RoleBean bean) throws Exception {

		RoleBean existsbean = findByName(bean.getName());

		if (existsbean != null && bean.getId() != existsbean.getId()) {

			throw new Exception("role id already exists ");
		}

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_role set name = ?, description = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

		pstmt.setString(1, bean.getName());
		pstmt.setString(2, bean.getDescription());
		pstmt.setString(3, bean.getCreatedBy());
		pstmt.setString(4, bean.getModifiedBy());
		pstmt.setTimestamp(5, bean.getCreatedDateTime());
		pstmt.setTimestamp(6, bean.getModifiedDateTime());
		pstmt.setLong(7, bean.getId());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("Data updated Successfully = " + i);
	}

	public void delete(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from st_role where id = ?");

		pstmt.setLong(1, id);

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("Data Deleted = " + i);

	}

	public List list() throws Exception {
		System.out.println("in list method>>>>>>>>>>>>>>>");
		return search(null, 0, 0);
	}

	public List search(RoleBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		StringBuffer sql = new StringBuffer("select * from st_role where 1=1");

		if (bean != null) {

			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "'");

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

			bean = new RoleBean();

			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDescription(rs.getString(3));
			bean.setCreatedBy(rs.getString(4));
			bean.setModifiedBy(rs.getString(5));
			bean.setCreatedDateTime(rs.getTimestamp(6));
			bean.setModifiedDateTime(rs.getTimestamp(7));
			list.add(bean);
		}
		JDBCDataSource.closeConnection(conn);
		return list;

	}

	public RoleBean findByPk(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_role where id = ?");

		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		RoleBean bean = null;

		while (rs.next()) {
			bean = new RoleBean();

			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDescription(rs.getString(3));
			bean.setCreatedBy(rs.getString(4));
			bean.setModifiedBy(rs.getString(5));
			bean.setCreatedDateTime(rs.getTimestamp(6));
			bean.setModifiedDateTime(rs.getTimestamp(7));

		}
		JDBCDataSource.closeConnection(conn);
		return bean;
	}

	public RoleBean findByName(String name) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_role where name = ?");

		pstmt.setString(1, name);

		ResultSet rs = pstmt.executeQuery();

		RoleBean bean = null;

		while (rs.next()) {

			bean = new RoleBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDescription(rs.getString(3));
			bean.setCreatedBy(rs.getString(4));
			bean.setModifiedBy(rs.getString(5));
			bean.setCreatedDateTime(rs.getTimestamp(6));
			bean.setModifiedDateTime(rs.getTimestamp(7));

		}
		JDBCDataSource.closeConnection(conn);
		return bean;

	}

}
