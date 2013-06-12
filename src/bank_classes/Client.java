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

public class Client extends User {
	private String cpf;

	public Client(String name, String surname, Calendar birthday,
			String username, String password, String cpf) {
		super(name, surname, birthday, username, password);
		// TODO Auto-generated constructor stub
		this.cpf = cpf;
	}


	public String get_account_id() {
		return super.getUsername();
	}
	public String get_cpf(){
		return cpf;
	}
	public String toString(){
		return super.toString() + "CPF:" + cpf;
	}
}
