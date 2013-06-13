package bank_classes;

import java.util.Calendar;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Client.java
//  @ Date : 01/06/2013
//  @ Author : 
//
//

import bankexceptions.DuplicateException;

public class Client extends User {
	private String cpf;
	private Account account;
	public Client(String name, String surname, Calendar birthday, String password, String cpf) {
		super(name, surname, birthday, null, password);
		// TODO Auto-generated constructor stub
		this.cpf = cpf;
		this.account = null;
	}

	public String get_account_id() {
		return super.getUsername();
	}
	public String get_cpf(){
		return cpf;
	}
	
	public String toString(){
		return super.toString() + " CPF:" + cpf;
	}
	
	public Account get_account(){
		return account;
	}
	
	public void add_account(Account acc) throws DuplicateException {
		if(this.account != null){
			throw new DuplicateException(acc.get_account_code());
		} else {
			this.account = account;
			this.setUsername(account.get_account_code());
		}
	}
	
}
