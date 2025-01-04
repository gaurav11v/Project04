package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.RoleBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DublicateRecordException;
import in.co.rays.model.RoleModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "RoleCtl", urlPatterns = { "/ctl/RoleCtl" })
public class RoleCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean isValid = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			isValid = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", "Role Name contains alphabet only");
			isValid = false;
		}
		String description = request.getParameter("description");
		if (DataValidator.isNull(description)) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			isValid = false;
		} else if (!DataValidator.isName(description)) {
			request.setAttribute("description", "invalid description");
			isValid = false;
		}

		return isValid;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		RoleBean bean = new RoleBean();
		// bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected void preload(HttpServletRequest request) {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		RoleBean bean = (RoleBean) populateBean(request);
		RoleModel model = new RoleModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			try {
				model.add(bean);
				ServletUtility.setSuccessMessage("Role Added Successfully..!!", request);
				ServletUtility.forward(getView(), request, response);
			} catch (DublicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Role id already exist", request);
				ServletUtility.forward(getView(), request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.ROLE_CTL, request, response);
			return;
		}

	}

	@Override
	protected String getView() {
		return ORSView.ROLE_VIEW;
	}

}
