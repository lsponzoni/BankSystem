package bank_classes;

import java.util.ArrayList;
import java.util.Collection;

import bankexceptions.DuplicateException;
import bankexceptions.InvalidTransaction;
import bankexceptions.NotFoundException;

public class Bank {
	private int next_account_number;
	private Collection<Branch> branches, atms;
	
	public Bank(){
		next_account_number = 11111;
		atms = new ArrayList<Branch>(10);
		branches = new ArrayList<Branch>(10);
	}

	public Branch get_branch(String branch_code) throws NotFoundException{
		for(Branch each: branches){
			if (each.code_match(branch_code))
				return each;
		}
		throw new NotFoundException(branch_code);
	}

	public Client get_client(String client_username, String branch_id) throws NotFoundException {
		Branch branch;
		branch = this.get_branch(branch_id);
		return branch.get_client(client_username);
	}

	public Clerk get_clerk(String clerk_username, String branch_id) throws NotFoundException {
		Branch branch;
		branch = this.get_branch(branch_id);
		return branch.get_clerk(clerk_username);
	}

	public String new_account_number() {
		String new_number = new Integer(next_account_number).toString();
		return new_number;
	}
	
	private void update_next_account_number(){
		next_account_number += 1;
	}

	public void add_atm(Branch branch) {
		atms.add(branch);
	}

	public void add_branch(Branch branch) {
		branches.add(branch);
	}

	public void add_clerk_account(Branch branch, Clerk new_user) throws DuplicateException {
		branch.add_clerk(new_user);
	}
	public void add_client_account(Branch branch, Client new_client, String initial_balance) throws DuplicateException, InvalidTransaction {
		Money first_deposit;
		String account_number = new_account_number();
		
		first_deposit = new Money(initial_balance);
		Account acc = new Account(account_number, branch, first_deposit);
		try{
		new_client.add_account(acc);
		branch.add_client(new_client);
		update_next_account_number();
		}catch(DuplicateException e){
			throw new DuplicateException("Trying to duplicate system info.");
		}

	}	
	public String transfer(String ammount, String to_account, String to_branch, Branch gate, Account account) throws NotFoundException, InvalidTransaction{
		Money transfer_ammount = new Money(ammount);
		Account to = get_client(to_account,to_branch).get_account();
		Transfer.execute_transfer(gate,transfer_ammount, account, to);
		return "Sucess";
	}
	public String deposit (String ammount, String cashParcelId, Branch gate, Account account) throws InvalidTransaction{
		Money deposited_value= new Money(ammount);
		Deposit d =	new Deposit(account.get_account_code(), gate.get_code(), deposited_value, cashParcelId);
		account.add_to_history(d);
		return "Success on deposit.\n";
	}
	
	public String withdraw(String ammount, Branch gate, Account acc){
		Money withdraw_ammount = new Money(ammount);
		try{
		Withdrawal w = new Withdrawal(acc,gate, withdraw_ammount);
		acc.add_to_history(w);
		return "Get the money.\n";
		} catch(InvalidTransaction e){
			return e.toString();
		}
	}
	
	public String elements
	public String print_atms(Collection<Branch> branches){
		String builder
		for (Branch branch : branches){
			
		}
	}
}
