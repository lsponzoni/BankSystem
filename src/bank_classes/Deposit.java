package bank_classes;

import java.util.Date;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Deposit.java
//  @ Date : 01/06/2013
//  @ Author : 
//
//

public class Deposit extends Transaction {
	private String cashParcelCode;

	public Deposit(String location_access_branch_code, Date date, Money value,
			String this_acc_code, String cashParcelCode) {
		super(this_acc_code, location_access_branch_code, date, value);
		this.cashParcelCode = cashParcelCode;
	}
	
	public String toString(){
		return "Deposit" + super.toString() + "\n " + "Parcel: " +cashParcelCode; 
	}
}
