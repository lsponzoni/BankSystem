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

	public Deposit(String acc_code, String access_branch_code, Money value, String cashParcelCode) {
		super(acc_code, access_branch_code, value);
		this.cashParcelCode = cashParcelCode;
	}
	public Deposit(Account account, Branch gate, Money value, Date date, String cashParcelCode) {
		super(account.get_account_code(), gate.get_code(), date, value);
		this.cashParcelCode = cashParcelCode;
	}
	
	public Deposit(String acc_code, String access_branch_code, Money value, String cashParcelCode, Date date) {
		super(acc_code, access_branch_code, date, value);
		this.cashParcelCode = cashParcelCode;
	}

	public String toString(){
		return "Deposit" + super.toString() + "\n " + "Parcel: " +cashParcelCode; 
	}
}
