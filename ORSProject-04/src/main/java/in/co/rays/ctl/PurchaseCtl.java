package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.PurchaseBean;
import in.co.rays.model.PurchaseModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "PurchaseCtl", urlPatterns = { "/ctl/PurchaseCtl" })
public class PurchaseCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean isValid = true;
		
		String quantity = request.getParameter("quantity");
		if (DataValidator.isNull(quantity)) {
			request.setAttribute("quantity", PropertyReader.getValue("error.require", "Quantity"));
			isValid = false;
		} else if (!DataValidator.isInteger(quantity)) {
			request.setAttribute("quantity", "Invalid Quantity");
			isValid = false;
		}
		
		String price = request.getParameter("price");
		if (DataValidator.isNull(price)) {
			request.setAttribute("price", PropertyReader.getValue("error.require", "Cost"));
			isValid = false;
		} else if (!DataValidator.isLong(price)) {
			request.setAttribute("price", "Invalid Price");
			isValid = false;
		}
		
		String purchasedate = request.getParameter("purchasedate");
		if (DataValidator.isNull(purchasedate)) {
			request.setAttribute("purchasedate", PropertyReader.getValue("error.require", "Purchase Date"));
			isValid = false;
		} else if (!DataValidator.isDate(purchasedate)) {
			request.setAttribute("purchasedate", PropertyReader.getValue("error.date", "Purchase Date"));
			isValid = false;
		}
		
		if (DataValidator.isNull(request.getParameter("order_type"))) {
			request.setAttribute("order_type", PropertyReader.getValue("error.require", "Order type"));
			isValid = false;
		}
		
		return isValid;
	
	}
	
	@Override
	protected void preload(HttpServletRequest request) {
	
		PurchaseModel purchaseModel = new PurchaseModel();
		try {
			List purchaseList = purchaseModel.list();
			request.setAttribute("purchaseList", purchaseList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	ServletUtility.forward(getView(), request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		String op = DataUtility.getString(request.getParameter("operation"));

		PurchaseBean bean = (PurchaseBean) populateBean(request);

		PurchaseModel model = new PurchaseModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			try {
				model.add(bean);
				ServletUtility.setSuccessMessage("data Added Succesfully", request);
				ServletUtility.forward(getView(), request, response);
		
				
			} catch (Exception e) {
			e.printStackTrace();
			}
		}
		return;
		}
	
	
	
	@Override
	protected String getView() {
		return ORSView.PURCHASE_VIEW;
	}

}
