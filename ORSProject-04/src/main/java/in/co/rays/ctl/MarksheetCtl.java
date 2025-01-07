package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.model.StudentModel;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "MarksheetCtl", urlPatterns = { "/ctl/MarksheetCtl" })

public class MarksheetCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean isValid = true;
		
		return isValid;
	}
	
	@Override
	protected void preload(HttpServletRequest request) {
		StudentModel studentModel = new StudentModel();
		try {
			List studentList = studentModel.list();
			request.setAttribute("studentList", studentList);
			} catch (Exception e) {
		e.printStackTrace();
			}
		
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		return super.populateBean(request);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	ServletUtility.forward(getView(), request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	
	
	@Override
	protected String getView() {
		return ORSView.MARKSHEET_VIEW;
	}

}
