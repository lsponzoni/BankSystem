package bank_classes;

import bankexceptions.InvalidTransaction;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : BranchUI.java
//  @ Date : 01/06/2013
//  @ Author : 
//
//

public class BranchUI extends UI {
	public BranchUI(Bank bank, Branch branch) {
		// TODO Auto-generated constructor stub
		super(bank,branch);
	}

	public String add_new_account_to_system(Client client, String initial_balance) {
		// ignore how hard it is to create the client out there
		String client_id, branch_id;
		Money balance; 
		Account new_acc;
		client_id = client.get_account_id();
		branch_id = access_branch.get_code();
		balance = new Money(initial_balance);
		new_acc = new Account(client_id, branch_id, balance);
		return "Success";

	}

	public String enable_management_functions() {
		return "Escolha uma operação: \n" +
				"1> Impressão saldo \n" +
				"2> Extrato \n" +
				"3> Depósito \n" +
				"4> Saque \n" +
				"5> Transferência \n" +
				"6> Criar nova conta \n" +
				"7> Logout de "+ current_user.getUsername();

	}

	public String login(String username, String password) {
		String msg;
		try{
			current_user = this.facade.get_clerk(username, access_branch.get_code());
			if(current_user.passwordMatch(password))
			{
				logged_in = true;
				msg = "Login realizado.";
				
			}
			else{
				msg = "Senha incorreta!";
			}
		}catch(NotFoundException excep){
			msg = "Funcionário não encontrado.";
		}
		return msg;

	}
}