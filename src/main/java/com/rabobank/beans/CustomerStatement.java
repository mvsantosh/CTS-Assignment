package com.rabobank.beans;

import java.io.Serializable;

public class CustomerStatement implements Serializable{
	
	/**
	 * Pojo object for customer statement
	 */
	private static final long serialVersionUID = 1L;
	private Integer transactionReference;
	private String accountNumber;
	private double startBalance;
	private double mutation;
	private String description;
	private double endBalance;
	
	public Integer getTransactionReference() {
		return transactionReference;
	}
	public void setTransactionReference(Integer transactionReference) {
		this.transactionReference = transactionReference;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getStartBalance() {
		return startBalance;
	}
	public void setStartBalance(double startBalance) {
		this.startBalance = startBalance;
	}
	public double getMutation() {
		return mutation;
	}
	public void setMutation(double mutation) {
		this.mutation = mutation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getEndBalance() {
		return endBalance;
	}
	public void setEndBalance(double endBalance) {
		this.endBalance = endBalance;
	}
	
	@Override
	public String toString() {
		return "CustomerStatement [transactionReference=" + transactionReference + ", accountNumber=" + accountNumber
				+ ", startBalance=" + startBalance + ", mutation=" + mutation + ", description=" + description
				+ ", endBalance=" + endBalance + "]";
	}
}
