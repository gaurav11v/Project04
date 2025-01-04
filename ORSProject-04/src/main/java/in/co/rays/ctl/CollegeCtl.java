package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.CourseBean;
import in.co.rays.exception.DublicateRecordException;
import in.co.rays.model.CollegeModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "CollegeCtl", urlPatterns = { "/ctl/CollegeCtl" })
public class CollegeCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean isValid = true;

		String name = request.getParameter("name");

		if (DataValidator.isNull(name)) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			isValid = false;
		} else if (!DataValidator.isName(name)) {
			request.setAttribute("name", "Invalid Name");
			isValid = false;
		}
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require", "Address"));
			isValid = false;
		}

		if (DataValidator.isNull(request.getParameter("state"))) {
			request.setAttribute("state", PropertyReader.getValue("error.require", "State"));
			isValid = false;
		} else if (!DataValidator.isName(request.getParameter("state"))) {
			request.setAttribute("state", "State Name contains alphabet only");
			isValid = false;
		}

		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", PropertyReader.getValue("error.require", "City"));
			isValid = false;
		} else if (!DataValidator.isName(request.getParameter("city"))) {
			request.setAttribute("city", "City Name contains alphabet only");
			isValid = false;
		}
		if (DataValidator.isNull(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", PropertyReader.getValue("error.require", "Mobile No"));
			isValid = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", "Mobile No. must be 10 Digit and No. Series start with 6-9");
			isValid = false;
		}

		return isValid;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		CollegeBean bean = (CollegeBean) populateBean(request);

		CollegeModel model = new CollegeModel();

		
			try {
				if (OP_SAVE.equalsIgnoreCase(op)) {
				model.add(bean);
				
				ServletUtility.setSuccessMessage("College Added Successfully..!!", request);
				ServletUtility.forward(getView(), request, response);
				}}
			catch (DublicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("College already exist", request);
				ServletUtility.forward(getView(), request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COLLEGE_CTL, request, response);
			return;
		}
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		CollegeBean bean = new CollegeBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setState(DataUtility.getString(request.getParameter("state")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));

		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.COLLEGE_VIEW;
	}

}
