package DTO;

import java.util.ArrayList;

public class PhoneNumber {

	private long CustomerNo;
	 
	ArrayList<Integer> CustomerPhoneNumber = new ArrayList<Integer>();
 

public long getCustomerNo() {
	return CustomerNo;
}

	public void setCustomerNo(long customerNo) {
		CustomerNo = customerNo;
	}

public ArrayList<Integer> getCustomerPhoneNumber() {
	return CustomerPhoneNumber;
}
 public void setCustomerPhoneNumber(ArrayList<Integer> customerPhoneNumber) {
	CustomerPhoneNumber = customerPhoneNumber;
}
	
}
