package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.RoleBean;
import in.co.rays.model.RoleModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "RoleListCtl", urlPatterns = { "/ctl/RoleListCtl" })
public class RoleListCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		return true;
	}

	@Override
	protected void preload(HttpServletRequest request) {
		RoleModel model = new RoleModel();
		try {
			List roleList = model.list();
			request.setAttribute("roleList", roleList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		RoleBean bean = new RoleBean();
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RoleBean bean = (RoleBean) populateBean(request);
		RoleModel model = new RoleModel();

		try {
			List list = model.search(bean, 0, 0);
			ServletUtility.setList(list, request);

		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RoleBean bean = (RoleBean) populateBean(request);
		RoleModel model = new RoleModel();

		String op = DataUtility.getString(request.getParameter("operation"));

		String[] ids = request.getParameterValues("ids");

		try {
			if (OP_DELETE.equalsIgnoreCase(op)) {

				for (String id : ids) {

					model.delete(Integer.parseInt(id));

					List list = model.search(bean, 0, 0);

					ServletUtility.setList(list, request);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.ROLE_LIST_VIEW;
	}

}
