package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.UserBean;
import in.co.rays.model.UserModel;

public class TestUserModel {

	public static void main(String[] args) throws Exception {

		// testAdd();
		// testUpdate();
		// testDelete();
//		testSearch();
		// testFindByPk();
		// testFindByLogin();
		testAuthenticate();

	}

	private static void testAuthenticate() throws Exception {

		String login = "meenabhardwaj@gmail.com";
		String password = "password123";

		UserModel model = new UserModel();

		UserBean bean = model.Authenticate(login, password);

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println("\t" + bean.getFirstName());
			System.out.println("\t" + bean.getLastName());
			System.out.println("\t" + bean.getLogin());
			System.out.println("\t" + bean.getPassword());
			System.out.println("\t" + bean.getDob());
			System.out.println("\t" + bean.getMobileNo());
			System.out.println("\t" + bean.getRoleId());
			System.out.println("\t" + bean.getGender());
			System.out.println("\t" + bean.getCreatedBy());
			System.out.println("\t" + bean.getModifiedBy());
			System.out.println("\t" + bean.getCreatedDateTime());
			System.out.println("\t" + bean.getModifiedDateTime());

		} else {

			System.out.println("user not available");

		}
	}
	private static void testFindByLogin() throws Exception {

		UserModel model = new UserModel();

		UserBean bean = model.findByLogin("as@gmail.com");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDateTime());
			System.out.println("\t" + bean.getModifiedDateTime());
		}
	}

	private static void testFindByPk() throws Exception {

		UserModel model = new UserModel();
		UserBean bean = model.findByPk(0);

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDateTime());
			System.out.println("\t" + bean.getModifiedDateTime());
		}

	}

	private static void testSearch() throws Exception {

		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		bean.setFirstName("");
		
		List list = model.search(bean,1,5);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (UserBean) it.next();

			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDateTime());
			System.out.println("\t" + bean.getModifiedDateTime());
		}
	}

	private static void testDelete() throws Exception {
		UserModel model = new UserModel();

		model.delete(1);
	}

	private static void testUpdate() throws Exception {

		UserModel model = new UserModel();
		UserBean bean = new UserBean();

		bean.setId(0);
		bean.setFirstName("Aman");
		bean.setLastName("Sharma");
		bean.setLogin("as@gmail.com");
		bean.setPassword("4321");
		bean.setDob(new Date());
		bean.setMobileNo("7458963544");
		bean.setRoleId(2);
		bean.setGender("Male");
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));

		model.update(bean);
	}

	private static void testAdd() throws Exception {

		UserModel model = new UserModel();
		UserBean bean = new UserBean();

		// bean.setId(0); Not required bcoz nextpk method is applied
		bean.setFirstName("Amit");
		bean.setLastName("Gupta");
		bean.setLogin("ag@gmail.com");
		bean.setPassword("5678");
		bean.setDob(new Date());
		bean.setMobileNo("9874526375");
		bean.setRoleId(1);
		bean.setGender("Male");
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

}
