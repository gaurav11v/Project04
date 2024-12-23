package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CourseBean;
import in.co.rays.model.CourseModel;

public class TestCourseModel {

	public static void main(String[] args) throws Exception {

		//testAdd();
		 //testUpdate();
		 //testDelete();
		 //testFindByPk();
		//testFindByName();
		testSearch();

	}

	private static void testFindByName() throws Exception {

		String name = "Biotech";

		CourseModel model = new CourseModel();

		CourseBean bean = model.findByName(name);

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println("\t" + bean.getName());
			System.out.println("\t" + bean.getDuration());
			System.out.println("\t" + bean.getDescription());
			System.out.println("\t" + bean.getCreatedBy());
			System.out.println("\t" + bean.getModifiedBy());
			System.out.println("\t" + bean.getCreatedDateTime());
			System.out.println("\t" + bean.getModifiedDateTime());

		} else {

			System.out.println("user not found");

		}

	}

	private static void testFindByPk() throws Exception {

		long id = 1;

		CourseModel model = new CourseModel();

		CourseBean bean = model.findByPk(id);

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println("\t" + bean.getName());
			System.out.println("\t" + bean.getDuration());
			System.out.println("\t" + bean.getDescription());
			System.out.println("\t" + bean.getCreatedBy());
			System.out.println("\t" + bean.getModifiedBy());
			System.out.println("\t" + bean.getCreatedDateTime());
			System.out.println("\t" + bean.getModifiedDateTime());

		} else {

			System.out.println("user not found");

		}

	}

	private static void testSearch() throws Exception {

		CourseBean bean = new CourseBean();

		CourseModel model = new CourseModel();

		bean.setName("");
		
		List list = model.search(bean, 1, 5);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			bean = (CourseBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDateTime());
			System.out.println("\t" + bean.getModifiedDateTime());

		}

	}

	private static void testDelete() throws Exception {

		CourseModel model = new CourseModel();
		model.delete(1);

	}

	private static void testUpdate() throws Exception {

		CourseBean bean = new CourseBean();
		CourseModel model = new CourseModel();

		bean.setId(1);
		bean.setName("Biotech");
		bean.setDuration("4yr");
		bean.setDescription("JNITcollege");
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));

		model.update(bean);

	}

	private static void testAdd() throws Exception {

		CourseBean bean = new CourseBean();
		CourseModel model = new CourseModel();

		//bean.setId(1);
		bean.setName("Biotech");
		bean.setDuration("5yr");
		bean.setDescription("MNITcollege");
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

}
