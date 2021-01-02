package DTO;

import java.util.List;

public class Customer {
	private long id;
	private String Name;
	private String Lastname;
	private List<PhoneNumber> customerPhoneDTOList;

	public long PhoneNumber() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	public List<PhoneNumber> getCustomerPhoneDTOList() {
		return customerPhoneDTOList;
	}

	public void setCustomerPhoneDTOList(List<PhoneNumber> customerPhoneDTOList) {
		this.customerPhoneDTOList = customerPhoneDTOList;
	}
	public long getId() {
		return id;
	}
}
