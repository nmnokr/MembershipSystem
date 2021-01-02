package CONTROLLERS;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import SERVICE.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DTO.Customer;

public class MusteriListe extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Customer> customerList = new ArrayList<>();

		try {
			List<Customer> customerDTOs = ServiceFacede.getInstance().getCustomers();
			customerList = customerDTOs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.setAttribute("customerList", customerList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("customers.jsp");
		dispatcher.forward(req, resp);

	}

}
