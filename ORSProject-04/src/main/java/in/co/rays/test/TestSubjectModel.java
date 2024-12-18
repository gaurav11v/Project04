package in.co.rays.test;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.SubjectBean;
import in.co.rays.model.SubjectModel;

public class TestSubjectModel {

	public static void main(String[] args) throws Exception {
		
		
		//testAdd();
        //testUpdate();
		//testDelete();
		//testSearch();
		//testFindByPk();
		testFindByName();
		
		
	}

	private static void testAdd() throws Exception {
		
		SubjectBean bean = new SubjectBean();
		SubjectModel model = new SubjectModel();
	
		bean.setName("Mechanical");
		bean.setCourseId(1);
		bean.setDescription("Mechanical engineering");
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		
		model.add(bean);
	
	}

	private static void testUpdate() throws Exception {
		
		SubjectBean bean = new SubjectBean();
		SubjectModel model = new SubjectModel();
		
		bean.setId(1);
		bean.setName("Electrical");
		bean.setCourseId(1);
		bean.setDescription("Electrical engineering");
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		
		model.update(bean);
	
	}

	private static void testDelete() throws Exception {
		

		SubjectModel model = new SubjectModel();
		model.delete(1);

	}

	private static void testSearch() throws Exception {
		SubjectBean bean = new SubjectBean();
		

		SubjectModel model = new SubjectModel();

		List list = model.search(bean);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (SubjectBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDateTime());
			System.out.println("\t" + bean.getModifiedDateTime());
		}
		
	}

	private static void testFindByPk() throws Exception {
		
		SubjectModel model = new SubjectModel();

		SubjectBean bean = model.findByPk(1);

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDateTime());
			System.out.println("\t" + bean.getModifiedDateTime());
		} else {
			System.out.println("id not found");
		}
		
	}

	private static void testFindByName() throws Exception {
		SubjectModel model = new SubjectModel();

		SubjectBean bean = model.findByName("mechanical");

		if (bean != null) {
			
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDateTime());
			System.out.println("\t" + bean.getModifiedDateTime());
		} else {
			System.out.println("subject name not found");
		}
		
	}

}
