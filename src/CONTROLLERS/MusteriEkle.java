package CONTROLLERS;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DTO.Customer;

import DTO.PhoneNumber;
import SERVICE.ServiceFacede;

public class MusteriEkle extends HttpServlet {

	private static final long serialVersionUID = 1L;

	String name, lastName;
	int phoneNumber, phoneNumber2;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Integer> customerPhoneNumber = new ArrayList<Integer>();
		PhoneNumber newNumber = new PhoneNumber();
		Customer newCustomer = new Customer();

		response.setContentType("text/html");

		name = request.getParameter("isim");
		newCustomer.setName(name);

		lastName = request.getParameter("soyisim");
		newCustomer.setLastname(lastName);

		phoneNumber = Integer.valueOf(request.getParameter("tel"));
		phoneNumber2 = Integer.valueOf(request.getParameter("tel2"));
		customerPhoneNumber.add(phoneNumber);
		customerPhoneNumber.add(phoneNumber2);

		newNumber.setCustomerPhoneNumber(customerPhoneNumber);

		try {

			ServiceFacede.getInstance().addCustomer(newCustomer, newNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
