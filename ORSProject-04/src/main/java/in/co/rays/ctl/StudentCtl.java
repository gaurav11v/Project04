package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.StudentBean;
import in.co.rays.exception.DublicateRecordException;
import in.co.rays.model.CollegeModel;
import in.co.rays.model.RoleModel;
import in.co.rays.model.StudentModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "StudentCtl", urlPatterns = { "/ctl/StudentCtl" })
public class StudentCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean isValid = true;

		// Validate First Name
		String firstName = request.getParameter("firstName");
		if (DataValidator.isNull(firstName)) {
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
			isValid = false;
		} else if (!DataValidator.isName(firstName)) {
			request.setAttribute("firstName", "Invalid First Name");
			isValid = false;
		}

		// Validate Last Name
		String lastName = request.getParameter("lastName");
		if (DataValidator.isNull(lastName)) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
			isValid = false;
		} else if (!DataValidator.isName(lastName)) {
			request.setAttribute("lastName", "Invalid Last Name");
			isValid = false;
		}

		// Validate Date of Birth
		String dob = request.getParameter("dob");
		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date of Birth"));
			isValid = false;
		} else if (!DataValidator.isDate(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date of Birth"));
			isValid = false;
		}

		// Validate Gender
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			isValid = false;
		}

// Validate Mobile No
		String mobileNo = request.getParameter("mobileNo");
		if (DataValidator.isNull(mobileNo)) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "Mobile No"));
			isValid = false;
		} else if (!DataValidator.isPhoneLength(mobileNo)) {
			request.setAttribute("mobileNo", "Mobile No must have 10 digits");
			isValid = false;
		} else if (!DataValidator.isPhoneNo(mobileNo)) {
			request.setAttribute("mobileNo", "Invalid Mobile No");
			isValid = false;
		}
		// Validate Email
		String email = request.getParameter("email");
		if (DataValidator.isNull(email)) {
			request.setAttribute("email", PropertyReader.getValue("error.require", "email"));
			isValid = false;
		} else if (!DataValidator.isEmail(email)) {
			request.setAttribute("email", PropertyReader.getValue("error.email", "email"));
			isValid = false;
		}
		
		String collegeName = request.getParameter("collegeName");
		if (DataValidator.isNull(collegeName)) {
			request.setAttribute("collegeName", PropertyReader.getValue("error.require", "College Name"));
			isValid = false;
		} else if (!DataValidator.isName(collegeName)) {
			request.setAttribute("collegeName", "Invalid College Name");
			isValid = false;
		}

		return isValid;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		StudentBean bean = new StudentBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));

		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected void preload(HttpServletRequest request) {
		CollegeModel collegeModel = new CollegeModel();
		try {
			List collegeList = collegeModel.list();
			request.setAttribute("collegeList", collegeList);
		} catch (Exception e) {
			e.printStackTrace();
		}

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

		StudentBean bean = (StudentBean) populateBean(request);
	
		StudentModel model = new StudentModel();
	
	if (OP_SAVE.equalsIgnoreCase(op)) {
		try {
			model.add(bean);
			ServletUtility.setSuccessMessage("Student Added Succesfully", request);
			ServletUtility.forward(getView(), request, response);
		} catch (DublicateRecordException e){
			ServletUtility.setBean(bean, request);
			ServletUtility.setErrorMessage("Student id already exist", request);
			ServletUtility.forward(getView(), request, response);
			
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	return;
	}

	@Override
	protected String getView() {
		return ORSView.STUDENT_VIEW;
	}

}
