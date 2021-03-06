package bank_classes;

import java.util.ArrayList;
import java.util.Collection;

import bankexceptions.DuplicateException;
import bankexceptions.InvalidTransaction;
import bankexceptions.NotFoundException;

public class Bank {
	private static final String SUCESSO = "Sucesso.\n";
	private int next_account_number;
	private Collection<Branch> branches, atms;
	
	public Bank(){
		next_account_number = 11111;
		atms = new ArrayList<Branch>(10);
		branches = new ArrayList<Branch>(10);
	}

	private Branch query_branch(String branch_code, Collection<Branch> b)  throws NotFoundException{
		Branch search_result = null;

		for(Branch each: b){
			if (each.code_match(branch_code)){
				search_result = each;
				break;
			}
		}
		if (search_result == null) {
			throw new NotFoundException(branch_code);
		}
		return search_result;
	}
	
	public Branch get_branch(String branch_code) throws NotFoundException{
		return query_branch(branch_code, branches);
	}

	public Branch get_atm(String branch_code) throws NotFoundException {
		return query_branch(branch_code, atms);
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
		
		first_deposit = Money.parseString(initial_balance);
		Account acc = new Account(account_number, branch, first_deposit);
		try{
		new_client.add_account(acc);
		branch.add_client(new_client);
		update_next_account_number();
		}catch(DuplicateException e){
			throw new DuplicateException("Trying to duplicate system info.");
		}
	}	
	public String transfer(String ammount, String to_account, String to_branch, Branch gate, Account account) {
		Money transfer_ammount;
		String response;
		try{
			transfer_ammount = Money.parseString(ammount);
			Account to = get_client(to_account,to_branch).get_account();
			response = Transfer.add_transfer(gate,transfer_ammount, account, to);
		} catch(InvalidTransaction ite){
			return ite.toString();
		} catch(NotFoundException nfe){
			return nfe.toString();
		}
		return response;
	}
	public String deposit (String ammount, String cashParcelId, Branch gate, Account account) {
		Money deposited_value;
		try{
		deposited_value = Money.parseString(ammount);
		Deposit d =	new Deposit(account.get_account_code(), gate.get_code(), deposited_value, cashParcelId);
		account.add_to_history(d);
		} catch(InvalidTransaction e){
			return e.toString();
		}
		return SUCESSO;
	}
	
	public String withdraw(String ammount, Branch gate, Account acc) {
		Money withdraw_ammount;
		try{
			withdraw_ammount = Money.parseString(ammount);
			Withdrawal w = Withdrawal.newInstance(acc,gate, withdraw_ammount);
			acc.add_to_history(w);
		} catch(InvalidTransaction e){
			return e.toString();
		}
		return SUCESSO;
	}
	
	public String list_branches(Collection<Branch> branches){
		StringBuilder list = new StringBuilder();
		for (Branch branch : branches){
			list.append( branch.string_branch_entry());
		}
		return list.toString();
	}

	public String list_branches_with_data(){
		return list_branches(branches);
	}

	public String list_all_atms(){
		return list_branches(atms);
	}

}
