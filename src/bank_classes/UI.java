package bank_classes;

import java.io.Console;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.Date;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : UI.java
//  @ Date : 01/06/2013
//  @ Author : 
//
//
import java.util.Scanner;




public class UI {
	private boolean logged_in;
	protected User current_user;
	protected Branch access_branch;
	protected Bank facade;
	
	public UI(Bank bank, Branch branch) {
		// Bank and branch data addition
	}
	public  String get_string(String question){
		InputStreamReader cin = new InputStreamReader(System.in);
		Scanner in = new Scanner(cin);
		System.out.println(question);
		String input = in.nextLine();
		in.close();
		return input;
	}
	public String operation_result(String effects){
		return "";
	}
	
	protected String login(String username, String branch, String password) {
		logged_in = true;
		return "";
	}
	
	protected String logout() {
		logged_in = false; 
		return "";
	}
	
	private void disable_operations() {
	}
	
	public boolean isLoggedIn() {
		return logged_in;
	}
	
	private String enable_financial_functions() {
		return "";
	
	}
	public String menu(){
		return "";
	}
	public String deposit() {
		return "Not implemented!";
	
	}
	
	public String withdraw(Number ammount, Client holder) {
		return "";
	
	}
	
	public String transfer() {
		return "";
	}
	
	public void get_period_from_user() {
	    //TODO this is not void
	}
	
	public void get_month_from_user() {
		//TODO this is not void
	}
	
	private String transaction_history() {
		return "";
	}
		
	public String transaction_history_to_print() {
		return "";
	}
	
	public String balance(Client holder) {
		return "";
	}
}
