package bank_classes;

import java.sql.Date;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Transaction.java
//  @ Date : 01/06/2013
//  @ Author : 
//
//

public class Transaction {
	private String location_access_branch_code;
	private Date date;
	private Number value;
	private String this_acc_code;

	public Transaction(String location_access_branch_code, Date date, Number value, String this_acc_code){
	    
	}
	
	
	
	
	public Number get_value() {
          return value;
	}

	public Date get_date() {
          return date;
	}

	public boolean newer_than(Date time) {
           return date.before(time);
	}

	public boolean older_than(Date time) {
           return date.after(time);
	}

	public boolean in_time_period(Date from, Date to) {
           return older_than(from) && newer_than(to);     
	}
}
