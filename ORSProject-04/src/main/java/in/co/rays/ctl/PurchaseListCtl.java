package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.PurchaseBean;
import in.co.rays.model.PurchaseModel;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "/PurchaseListCtl", urlPatterns = { "/ctl/PurchaseListCtl" })
public class PurchaseListCtl extends BaseCtl {

	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("in do getmethod");
		
		PurchaseModel model = new PurchaseModel();
		PurchaseBean bean = new PurchaseBean();

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

	}

	@Override
	protected String getView() {
		return ORSView.PURCHASE_LIST_VIEW;
	}
	

}
