package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.DoctorBean;
import in.co.rays.bean.PurchaseBean;
import in.co.rays.model.DoctorModel;
import in.co.rays.model.PurchaseModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "DoctorCtl", urlPatterns = { "/ctl/DoctorCtl" })

public class DoctorCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean isValid = true;
		
		String name = request.getParameter("name");
		if (DataValidator.isNull(name)) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			isValid = false;
		} else if (!DataValidator.isName(name)) {
			request.setAttribute("name", "Invalid name");
			isValid = false;
		}

		String dob = request.getParameter("dob");
		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date of Birth"));
			isValid = false;
		}
		String mobile = request.getParameter("mobile");
		if (DataValidator.isNull(mobile)) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", "Mobile No"));
			isValid = false;
		} else if (!DataValidator.isPhoneLength(mobile)) {
			request.setAttribute("mobile", "Mobile No must have 10 digits");
			isValid = false;
		} else if (!DataValidator.isPhoneNo(mobile)) {
			request.setAttribute("mobileNo", "Invalid Mobile No");
			isValid = false;
		}

		if (DataValidator.isNull(request.getParameter("expertise"))) {
			request.setAttribute("expertise", PropertyReader.getValue("error.require", "expertise"));
			isValid = false;
		}
		return isValid;

	}

	@Override
	protected DoctorBean populateBean(HttpServletRequest request) {
		DoctorBean bean = new DoctorBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setMobile(DataUtility.getString(request.getParameter("mobile")));
		bean.setExpertise(DataUtility.getString(request.getParameter("expertise")));

		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String op = DataUtility.getString(request.getParameter("operation"));
		Long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {

			DoctorModel model = new DoctorModel();

			try {
				DoctorBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		DoctorBean bean = (DoctorBean) populateBean(request);

		DoctorModel model = new DoctorModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			try {
				model.add(bean);
				ServletUtility.setSuccessMessage("Doctor Added Succesfully", request);
				ServletUtility.forward(getView(), request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (OP_UPDATE.equalsIgnoreCase(op)) {
			try {
				System.out.println("In update");
				model.update(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Doctor Updated Successfully", request);
				ServletUtility.forward(getView(), request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.DOCTOR_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.DOCTOR_CTL, request, response);
			return;
		}

	}

	@Override
	protected String getView() {
		return ORSView.DOCTOR_VIEW;
	}

}
