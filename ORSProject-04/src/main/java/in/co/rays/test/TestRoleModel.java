package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.RoleBean;
import in.co.rays.model.RoleModel;

public class TestRoleModel {

	public static void main(String[] args) throws Exception {

//		 testAdd();
		// testUpdate();
		// testDelete();
		 testSearch();
		// testFindByPk();
//		testFindByName();

	}

	private static void testFindByName() throws Exception {

		RoleModel model = new RoleModel();
		RoleBean bean = model.findByName("admin");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDateTime());
			System.out.println("\t" + bean.getModifiedDateTime());
		}

	}

	private static void testFindByPk() throws Exception {

		RoleModel model = new RoleModel();
		RoleBean bean = model.findByPk(1);

		if (bean != null) {

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDateTime());
			System.out.println("\t" + bean.getModifiedDateTime());
		}

	}

	private static void testSearch() throws Exception {

		RoleModel model = new RoleModel();

		RoleBean bean = new RoleBean();
		
		bean.setName("student");

		List list = model.search(bean,0,0);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (RoleBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDateTime());
			System.out.println("\t" + bean.getModifiedDateTime());
		}
	}

	private static void testDelete() throws Exception {

		RoleModel model = new RoleModel();

		model.delete(1);

	}

	private static void testUpdate() throws Exception {

		RoleModel model = new RoleModel();
		RoleBean bean = model.findByPk(1);

		bean.setId(3);
		bean.setName("kioska");
		bean.setDescription("kiosk");
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));

		model.update(bean);

	}

	private static void testAdd() throws Exception {

		RoleModel model = new RoleModel();
		RoleBean bean = new RoleBean();

		// bean.setId(1); after setting nextpk in model, no need this line to generate
		// id. it will give automatically
		bean.setName("student");
		bean.setDescription("students");
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));

		model.add(bean);
	}

}
