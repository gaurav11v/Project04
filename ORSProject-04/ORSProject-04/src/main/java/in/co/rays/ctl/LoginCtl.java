package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.UserBean;
import in.co.rays.model.UserModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.ServletUtility;

@WebServlet("/LoginCtl")
public class LoginCtl extends BaseCtl {

	public static final String OP_SIGN_IN = "Sign In";

	@Override
	protected boolean validate(HttpServletRequest request) {
		return false;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		UserBean bean = new UserBean();
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setLogin(DataUtility.getString(request.getParameter("password")));
		return bean;
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
		UserBean bean = (UserBean) populateBean(request);
		UserModel model = new UserModel();

		if (op.equalsIgnoreCase(op)) {
			try {
				model.Authenticate(bean.getLogin(), bean.getPassword());
			} catch (Exception e) {
				e.printStackTrace();
			}

			ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
		}

	}

	@Override
	protected String getView() {
		return ORSView.LOGIN_VIEW;
	}

}
