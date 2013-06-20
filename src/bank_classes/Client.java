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
import bankexceptions.InvalidTransaction;

public class Client extends User {
	private String cpf;
	private Account account;

	public Client(String name, String surname, Calendar birthday, Account acc, String password, String cpf) {
		super(name, surname, birthday, acc.get_account_code(), password);
		this.cpf = cpf;
		this.account = acc;
	}
	
	public Client(String name, String surname, Calendar birthday, String cpf) {
		super(name, surname, birthday, null, null);
		this.cpf = cpf;
		this.account = null;
	}



	public String get_account_id() {
		return super.getUsername();
	}

	public String get_cpf(){
		return cpf;
	}
	
	public void add_transaction(Transaction t) throws InvalidTransaction{
		if(this.account != null){
			account.add_to_history(t);
		} else {
			throw new InvalidTransaction(t.toString());
		}
	}
	public Account get_account(){
		return account;
	}
	
	public String get_creation_data(){
		return "Number :" + super.getUsername() + " Password : " + super.getPassword();
	}
	
	public void add_account(Account account) throws DuplicateException {
		if(this.account != null){
			throw new DuplicateException(account.get_account_code());
		} else {
			this.account = account;
			this.setUsername(account.get_account_code());
			this.setPassword(User.new_random_password());
		}
	}

	public String toString(){
		return super.toString() + " CPF:" + cpf;
	}

}
