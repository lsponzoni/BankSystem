import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import bank_classes.Account;
import bank_classes.AtmUI;
import bank_classes.Bank;
import bank_classes.Branch;
import bank_classes.BranchUI;
import bank_classes.Clerk;
import bank_classes.Client;
import bank_classes.Money;
import bank_classes.UI;
import bank_classes.User;


public class SystemTest {

	/**
	 * @param args
	 */
	public static Bank populate(){
		// Create the bank 
		Bank b = new Bank();
		// Create the atm Branches(Notice how they don't have anything at the Collections 
		// Branch(String code, String name, Collection<User> client, Collection<User> clerks)
		Branch atm1 = new Branch("ATM2009","Caixa Eletrônico 9 Rua Ceará",null,null);
		Branch atm2 = new Branch("ATM3010","Caixa Eletrônico 10 Rua Anchieta",null,null);
		Branch atm3 = new Branch("ATM5012","Caixa Eletrônico 12 Shopping Iguatemi",null,null);
		// Create the clients
		//Client(String name, String surname, Calendar birthday,String username, String password, String cpf)
		Calendar born_at = new GregorianCalendar();
		born_at.set(Calendar.YEAR, 1980);
		born_at.set(Calendar.MONTH, 11); // 11 = december
		born_at.set(Calendar.DAY_OF_MONTH, 24); // christmas eve

		Client mario =  new Client("Mario","Brother",born_at,"193022","It's me Mario","13189003018");
		Client julia =  new Client("Julia","Ana",born_at,"194022","I am not mario","114929803");
		Client fefe =  new Client("FE","FE",born_at,"195022","I am not mario also","1110821529");

		List<User> clients_br1 = new ArrayList<User>();

		clients_br1.add(mario);
		
		List<User> clients_br2 = new ArrayList<User>();

		clients_br2.add(julia);
		clients_br2.add(fefe);
		
		List<User> clerks_br1 = new ArrayList<User>();
		Clerk mario2 = new Clerk("Mario","Mario",born_at,"193022","It's me Mario");
		clerks_br1.add(mario2);
		
		List<User> clerks_br2 = new ArrayList<User>();
		Clerk mario3 = new Clerk("Super","Mario",born_at,"3022","So many marios.");
		clerks_br2.add(mario3);
		clerks_br2.add(mario3);
		
		Branch br1 = new Branch("FIL2001","Filial Rua Ceará",clients_br1, clerks_br1);
		Branch br2 = new Branch("FIL3001","Filial Rua Anchieta",clients_br2, clerks_br2);

		
		b.add_branch(atm1);
		b.add_branch(atm2);
		b.add_branch(atm3);
		b.add_branch(br1);
		b.add_branch(br2);

		// Create a few accounts
		// Account(String account_code, String branch_code, Money initial_balance)
		Money m1 = new Money(1991);
		Money m2 = new Money(19091);
		Money m3 = new Money(19031);

		Account mario_account = new Account(mario.get_account_id(),br1.get_code(),m1);
		Account julia_account = new Account(julia.get_account_id(),br2.get_code(),m2);
		Account fefe_account = new Account(fefe.get_account_id(),br2.get_code(),m3);
		
		b.add_account(mario_account);
		b.add_account(julia_account);
		b.add_account(fefe_account);
		// How to add the accounts to the system?
		// For now system accounts are going to be appended to client owner
		// or at least, it is what i believe will happen 
		return b;
	}
	public static void main(String[] args) {
	   Bank bank = populate();
	   AtmUI only_client_use = new AtmUI(bank, bank.get_branch("ATM2009"));
	   BranchUI only_clerk_use = new BranchUI(bank, bank.get_branch("FIL2001"));
	   //UI.main_loop(); 
	   Client client = only_clerk_use.client_dataform();
	   Money initial_balance = only_clerk_use.money_dataform();
	   only_clerk_use.add_new_account_to_system(client, initial_balance);
	}

}
