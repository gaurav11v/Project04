package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.CourseBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.util.JDBCDataSource;

public class SubjectModel {

	public Integer nextPk() throws Exception {
		int pk = 0;
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_subject");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			pk = rs.getInt(1);
		}
		JDBCDataSource.closeConnection(conn);
		return pk + 1;
	}

	public void add(SubjectBean bean) throws Exception {
		
		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());
		bean.setCourseName(courseBean.getName());

		SubjectBean existBean = findByName(bean.getName());

		if (existBean != null) {
			throw new Exception("subject name already exist ");
		}
		int pk = nextPk();
		
	Connection conn = JDBCDataSource.getConnection();
	
	PreparedStatement pstmt = conn.prepareStatement("insert into st_subject values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
	
	pstmt.setLong(1, pk);
	pstmt.setString(2, bean.getName());
	pstmt.setLong(3, bean.getCourseId());
	pstmt.setString(4, bean.getCourseName());
	pstmt.setString(5, bean.getDescription());
	pstmt.setString(6, bean.getCreatedBy());
	pstmt.setString(7, bean.getModifiedBy());
	pstmt.setTimestamp(8, bean.getCreatedDateTime());
	pstmt.setTimestamp(9, bean.getModifiedDateTime());

	int i = pstmt.executeUpdate();

	JDBCDataSource.closeConnection(conn);

	System.out.println("data added = " + i);
		}
	
	
	public void update(SubjectBean bean) throws Exception {
		
		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());
		bean.setCourseName(courseBean.getName());

		SubjectBean existBean = findByName(bean.getName());

		if (existBean != null && bean.getId() != existBean.getId()) {
			throw new Exception("subject name already exist ");
		}
		
		Connection conn = JDBCDataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("update st_subject set name = ?, course_id = ?, course_name = ?, description = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

		pstmt.setString(1, bean.getName());
		pstmt.setLong(2, bean.getCourseId());
		pstmt.setString(3, bean.getCourseName());
		pstmt.setString(4, bean.getDescription());
		pstmt.setString(5, bean.getCreatedBy());
		pstmt.setString(6, bean.getModifiedBy());
		pstmt.setTimestamp(7, bean.getCreatedDateTime());
		pstmt.setTimestamp(8, bean.getModifiedDateTime());
		pstmt.setLong(9, bean.getId());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data updated Successfully " + i);

	}
	
	public void delete(long id) throws Exception {
		
		Connection conn = JDBCDataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("delete from st_subject where id = ?");
	
		pstmt.setLong(1, id);

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data deleted = " + i);
	}
	
	public List search(SubjectBean bean) throws SQLException {
		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_subject");

		ResultSet rs = pstmt.executeQuery();

		List list = new ArrayList();

		while (rs.next()) {
			bean = new SubjectBean();
			
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setCourseId(rs.getLong(3));
			bean.setCourseName(rs.getString(4));
			bean.setDescription(rs.getString(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDateTime(rs.getTimestamp(8));
			bean.setModifiedDateTime(rs.getTimestamp(9));
			list.add(bean);
		}
		JDBCDataSource.closeConnection(conn);
		return list;
	}
	
	public SubjectBean findByPk(long id) throws Exception {
		
		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_subject where id = ?");

		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		SubjectBean bean = null;

		while (rs.next()) {
			bean = new SubjectBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setCourseId(rs.getLong(3));
			bean.setCourseName(rs.getString(4));
			bean.setDescription(rs.getString(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDateTime(rs.getTimestamp(8));
			bean.setModifiedDateTime(rs.getTimestamp(9));
		}
		JDBCDataSource.closeConnection(conn);
		return bean;
		}
	
	public SubjectBean findByName(String name) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_subject where name = ?");

		pstmt.setString(1, name);

		ResultSet rs = pstmt.executeQuery();

		SubjectBean bean = null;

		while (rs.next()) {
			bean = new SubjectBean();
			
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setCourseId(rs.getLong(3));
			bean.setCourseName(rs.getString(4));
			bean.setDescription(rs.getString(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDateTime(rs.getTimestamp(8));
			bean.setModifiedDateTime(rs.getTimestamp(9));
		}
		JDBCDataSource.closeConnection(conn);
		return bean;
}
	
}

