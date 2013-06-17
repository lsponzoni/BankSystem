package bank_classes;

import bankexceptions.InvalidTransaction;
import bankexceptions.NotFoundException;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : atmUI.java
//  @ Date : 01/06/2013
//  @ Author : 
//
//

public class AtmUI extends UI {
	private static final String INVALID_TRANSACTION = null;
	private static final String OPERACAO_FINALIZADA = null;
	MenuOptions[] LOGGED_MENU_OPTIONS = {MenuOptions.LOGOUT, 
			   MenuOptions.BALANCE, 
			   MenuOptions.BRANCH, 
			   MenuOptions.DEPOSIT,
			   MenuOptions.HISTORY,
			   MenuOptions.TRANSFER,
			   };

	public AtmUI(Bank bank, Branch branch){
		super(bank, branch);
	}
	
	protected Client exist_at_system(String username,String branch) throws NotFoundException{
		return facade.get_client(username, branch);
	}

	protected String login(String username, String branch, String password) {
		String msg;
		try{
			current_user = exist_at_system( username, branch);
			if(current_user.passwordMatch(password)) {
				super.set_log_in();
				msg = LOGIN_OK;
			} else {
				msg = WRONG_PASSWORD;
			}
		}catch(NotFoundException excep){
			msg = USER_NOT_FOUND;
		}
		return msg;
	}

	public String deposit() {
		String ammount;
		String cashParcelId;
		ammount = get_string("Digite a quantia a ser depositada: ");
		cashParcelId = get_string("Digite o codigo do envelope: ");
		try{
			this.facade.deposit(ammount, cashParcelId, access_branch, ((Client) current_user).get_account());
		}
		catch(InvalidTransaction e){
			return INVALID_TRANSACTION;
		}
		return OPERACAO_FINALIZADA;
	}

	
	public String withdraw() {
		String ammount;
		ammount = get_string("Digite a quantia a ser depositada: \n");
		return this.facade.withdraw(ammount, access_branch, ((Client) current_user).get_account());
	}
	
	public String transfer() {
		String ammount;
		String to_account_id;
		String to_branch_id;

		to_account_id = get_string("Digite o codigo da conta destino: \n");
		to_branch_id = get_string("Digite o codigo da agencia destino: \n");
		ammount = get_string("Digite a quantia a ser transferida: \n");
		
		try{
			this.facade.transfer(ammount, to_account_id, to_branch_id, access_branch, ((Client) current_user).get_account());
		}
		catch(NotFoundException e){
			return USER_NOT_FOUND;
		}
		catch(InvalidTransaction e){
			return INVALID_TRANSACTION;
		}
		
		return OPERACAO_FINALIZADA;
	}

	@Override
	protected MenuOptions[] get_logged_menu_options() {
		return LOGGED_MENU_OPTIONS;
	}
}
