package bank_classes;

import java.util.Date;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Transfer.java
//  @ Date : 01/06/2013
//  @ Author : 
//
//

import bankexceptions.InvalidTransfer;

public class Transfer extends Transaction {
	private String to_account_code;
	private String to_branch_code;
	private String from_account_code;
	private String from_branch_code;

	public Transfer(String this_acc_code, String location_access_branch_code, Date date, 
			 Money value,
			 String to_acc_code, String to_branch_code, 
			 String from_acc_code,String from_branch_code) throws InvalidTransfer {
		super(this_acc_code, location_access_branch_code, date, value);
		
		if(	!this_acc_code.equals(from_acc_code) 
			&& !this_acc_code.equals(to_acc_code)){
			throw new InvalidTransfer(" Trying to transfer data of another account.");
		}
		this.to_account_code = to_acc_code;
		this.to_branch_code = to_branch_code;
		this.from_account_code = from_acc_code;
		this.from_branch_code = from_branch_code;		
	}	

	public String get_from() {
		return from_account_code;
	}

	public String get_to() {
		return from_account_code;
	}

	public Money get_value() {
		Money value = super.get_value();
		if(this_acc_code.equals(from_account_code)){
			value = value.minus();
		}
		return value;
	}

}
