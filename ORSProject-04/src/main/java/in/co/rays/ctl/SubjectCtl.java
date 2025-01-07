package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.exception.DublicateRecordException;
import in.co.rays.model.CourseModel;
import in.co.rays.model.SubjectModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "SubjectCtl", urlPatterns = { "/ctl/SubjectCtl" })
public class SubjectCtl extends BaseCtl {

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
		
		if (DataValidator.isNull(request.getParameter("courseName"))) {
			request.setAttribute("courseName", PropertyReader.getValue("error.require", "Course Name"));
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
		SubjectBean bean = new SubjectBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected void preload(HttpServletRequest request) {

		CourseModel courseModel = new CourseModel();
		try {
			List courseList = courseModel.list();
			request.setAttribute("courseList", courseList);
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

		SubjectBean bean = (SubjectBean) populateBean(request);

		SubjectModel model = new SubjectModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			try {
				model.add(bean);
				ServletUtility.setSuccessMessage("Subject added Successfully", request);
				ServletUtility.forward(getView(), request, response);
			} catch (DublicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Subject already exist", request);
				ServletUtility.forward(getView(), request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return;
	}

	@Override
	protected String getView() {
		return ORSView.SUBJECT_VIEW;
	}

}
