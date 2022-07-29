package com.qa.ims.persistence.model;

import java.util.Objects;

public class Customer {
	private long customerId;
	private String firstName;
	private String surname;
	private String email;
	private String postCode;

	public Customer(String firstName, String surname, String email, String postCode) {
		this.setFirstName(firstName);
		this.setSurname(surname);
		this.setEmail(email);
		this.setPostCode(postCode);
	}

	public Customer(long customerId, String firstName, String surname, String email, String postCode) {
		this.setCustomerId(customerId);
		this.setFirstName(firstName);
		this.setSurname(surname);
		this.setEmail(email);
		this.setPostCode(postCode);
	}

    public Customer() {

    }

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"customerId=" + customerId +
				", firstName='" + firstName + '\'' +
				", surname='" + surname + '\'' +
				", email='" + email + '\'' +
				", postCode='" + postCode + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Customer customer)) return false;
		return getCustomerId() == customer.getCustomerId() && Objects.equals(getFirstName(), customer.getFirstName())
				&& Objects.equals(getSurname(), customer.getSurname())
				&& Objects.equals(getEmail(), customer.getEmail())
				&& Objects.equals(getPostCode(), customer.getPostCode());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCustomerId(), getFirstName(), getSurname(), getEmail(), getPostCode());
	}
}
