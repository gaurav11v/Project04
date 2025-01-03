package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.util.ServletUtility;

@WebServlet("/SubjectCtl")
public class SubjectCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean isValid = true;

		return isValid;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		return super.populateBean(request);
	}

	@Override
	protected void preload(HttpServletRequest request) {
		super.preload(request);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	}

	@Override
	protected String getView() {
		return ORSView.SUBJECT_VIEW;
	}

}
