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
import in.co.rays.model.CourseModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet("/CourseCtl")
public class CourseCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean isValid = true;


		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Course Name"));
			isValid = false ;
		}else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", " Course Name contains alphabet only");
			isValid = false ;
		}
		if (DataValidator.isNull(request.getParameter("duration"))) {
			request.setAttribute("duration", PropertyReader.getValue("error.require", "Duration"));
			isValid = false ;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			isValid = false ;
		}
		return isValid;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		CourseBean bean = new CourseBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String op = DataUtility.getString(request.getParameter("operation"));

		CourseBean bean = (CourseBean) populateBean(request);

		CourseModel model = new CourseModel();

		try {
			if (OP_SAVE.equalsIgnoreCase(op)) {
				model.add(bean);
				ServletUtility.setSuccessMessage("Course Added Successfully", request);
				ServletUtility.forward(getView(), request, response);
			
			} if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
				return;
			}
			
		} catch (Exception e) {
			ServletUtility.setBean(bean, request);
			ServletUtility.setErrorMessage("Course Already Exist", request);
			ServletUtility.forward(getView(), request, response);

		e.printStackTrace();
		}
		return;
	}

	@Override
	protected String getView() {
		return ORSView.COURSE_VIEW;
	}

}
