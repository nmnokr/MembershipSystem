package SERVICE;

import java.util.List;
import java.util.Properties;

 
import DTO.Customer;
import DTO.PhoneNumber;
import dao.customer.*;
public class ServiceFacede {

	private static ServiceFacede serviceFacede;
	private  Dao32Bit customerDAO;

	private ServiceFacede() {
	}

	public static ServiceFacede getInstance() {
		if (serviceFacede == null) {
			serviceFacede = new ServiceFacede();
		}
		return serviceFacede;
	}

	public void initialize(Properties appProperties) throws Exception {
		customerDAO = new Dao32Bit();
		customerDAO.init(appProperties);

	}

	public void shutdown() {

	}

	public void addCustomer(Customer customer, PhoneNumber phoneNumber) throws Exception {
		customerDAO.addCustomer(customer, phoneNumber);
	}

	public List<Customer> getCustomers() throws Exception {
		return customerDAO.getCustomer();
	}

}
