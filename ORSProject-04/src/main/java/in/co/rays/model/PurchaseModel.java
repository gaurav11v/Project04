package in.co.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.PurchaseBean;
import in.co.rays.util.JDBCDataSource;

public class PurchaseModel {

	public Integer nextPk() throws Exception {

		int pk = 0;
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_purchase");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			pk = rs.getInt(1);
		}
		JDBCDataSource.closeConnection(conn);
		return pk + 1;

	}

	public void add(PurchaseBean bean) throws Exception {

		int pk = nextPk();

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("insert into st_purchase values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

		// pstmt.setLong(1, bean.getId()); removed, bcoz nextpk is set
		pstmt.setInt(1, pk);
		pstmt.setInt(2, bean.getQuantity());
		pstmt.setDouble(3, bean.getPrice());
		pstmt.setDate(4, new Date(bean.getPurchaseDate().getTime()));
		pstmt.setString(5, bean.getOrderType());
		pstmt.setString(6, bean.getCreatedBy());
		pstmt.setString(7, bean.getModifiedBy());
		pstmt.setTimestamp(8, bean.getCreatedDateTime());
		pstmt.setTimestamp(9, bean.getModifiedDateTime());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);
		System.out.println("Data added successfully = " + i);

	}

	/**
	 * @param bean
	 * @throws Exception
	 */
	public void update(PurchaseBean bean) throws Exception {

		int pk = nextPk();
		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_purchase set quantity = ?, price = ?, purchase_date = ?, order_type = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

		pstmt.setInt(1, bean.getQuantity());
		pstmt.setDouble(2, bean.getPrice());
		pstmt.setDate(3, new Date(bean.getPurchaseDate().getTime()));
		pstmt.setString(4, bean.getOrderType());
		pstmt.setString(5, bean.getCreatedBy());
		pstmt.setString(6, bean.getModifiedBy());
		pstmt.setTimestamp(7, bean.getCreatedDateTime());
		pstmt.setTimestamp(8, bean.getModifiedDateTime());
		pstmt.setLong(9, bean.getId());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("Data updated Successfully = " + i);
	}

	/**
	 * @param id
	 * @throws Exception method used to delete data
	 */
	public void delete(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from st_purchase where id = ?");

		pstmt.setLong(1, id);

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("Data Deleted = " + i);

	}

	public List list() throws Exception {
		System.out.println("in list method>>>>>>>>>>>>>>>");
		return search(null, 0, 0);
	}

	public List search(PurchaseBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		StringBuffer sql = new StringBuffer("select * from st_purchase where 1=1");

		if (bean != null) {
			if (bean.getOrderType() != null && bean.getOrderType().length() > 0) {
				sql.append(" and order_type like '" + bean.getOrderType() + "'");

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

			bean = new PurchaseBean();

			bean.setId(rs.getLong(1));
			bean.setQuantity(rs.getInt(2));
			bean.setPrice(rs.getDouble(3));
			bean.setPurchaseDate(rs.getDate(4));
			bean.setOrderType(rs.getString(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDateTime(rs.getTimestamp(8));
			bean.setModifiedDateTime(rs.getTimestamp(9));

			list.add(bean);
		}
		JDBCDataSource.closeConnection(conn);
		return list;

	}

	public PurchaseBean findByPk(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_purchase where id = ?");

		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		PurchaseBean bean = null;

		while (rs.next()) {
			bean = new PurchaseBean();

			bean.setId(rs.getLong(1));
			bean.setQuantity(rs.getInt(2));
			bean.setPrice(rs.getDouble(3));
			bean.setPurchaseDate(rs.getDate(4));
			bean.setOrderType(rs.getString(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDateTime(rs.getTimestamp(8));
			bean.setModifiedDateTime(rs.getTimestamp(9));

		}
		JDBCDataSource.closeConnection(conn);
		return bean;
	}

}
