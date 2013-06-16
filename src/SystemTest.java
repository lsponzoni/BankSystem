import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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

	
	public static Bank populate(){
		// Create the bank 
		Bank b = new Bank();
		// Create the atm Branches(Notice how they don't have anything at the Collections 
		// Branch(String code, String name, Collection<User> client, Collection<User> clerks)
		Branch atm1 = new Branch("ATM2009","Caixa Eletrônico 9 Rua Ceará");
		Branch atm2 = new Branch("ATM3010","Caixa Eletrônico 10 Rua Anchieta");
		Branch atm3 = new Branch("ATM5012","Caixa Eletrônico 12 Shopping Iguatemi");
		// Create the clients
		//Client(String name, String surname, Calendar birthday,String username, String password, String cpf)
		Calendar born_at = new GregorianCalendar();
		born_at.set(Calendar.YEAR, 1980);
		born_at.set(Calendar.MONTH, 11); // 11 = december
		born_at.set(Calendar.DAY_OF_MONTH, 24); // christmas eve
		
        Client mario =  new Client("Mario","Brother",born_at,"193022","It's me Mario");
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
	    
		
		Branch br1 = new Branch("FIL2001","Filial Rua Cear�",clients_br1, clerks_br1);
		Branch br2 = new Branch("FIL3001","Filial Rua Anchieta",clients_br2, clerks_br2);

		
		b.add_branch(atm1);
		b.add_branch(atm2);
		b.add_branch(atm3);
		b.add_branch(br1);
		b.add_branch(br2);

		return b;
	}
	
	   
      
	   @Test (expected = DuplicateException.class)
	   public void duplicated_account() throws DuplicateException
	   {
		   Bank bank = populate();
		   bank.add_client_account(bank.get_branch("FIL2001"), bank.get_client("193022","ATM2009"),"1000");
	   }
	   @Test (expected = DuplicateException.class)
	   public void duplicated_clerks() throws DuplicateException
	   {
		   Clerk clerk_test = new Clerk("Mario","Mario",born_at,"193022","It's me Mario");//cleark mario
		   Bank bank = populate();
		   add_clerk_account(bank.get_branch("FIL2001"),clerk_test);//tries to add an existing client
	   }
	   @Test
	   public void test_transfer()//testa se transferiu
	   {
		   String result;
		   Bank bank = populate();
		   Client sender = bank.get_client("193022", "FIL2001");
		   result = bank.transfer("999",bank.get_branch("FIL3001"),"194022", bank.get_branch("ATM3010"), sender.get_account);
		   assertEquals("Sucess", result);
	   }

	   @Test
	   public void test_withdraw()//testa saque
	   {
		   String result;
		   Bank bank = populate();
		   result = bank.withdraw("10",)
		   assertEquals("Get your money.\n", result);
		   
	   }
	   @Test (expected = InvalidTransaction.class)
	   
	   
	   @Test (expected = InvalidTransaction.class)
	   public void test_invalid_deposit() throws InvalidTransaction
	   {
		   Bank bank = populate();
		   //mario1 fez um deposito
		   bank.deposit("-10", "1234", bank.get_branch("ATM"), );
	   }
	   @Test
	   public void test_deposit() throws InvalidTransaction
	   {
		   Bank bank = populate();
		   //mario1 fez um deposito
		   bank.deposit("2310", "1234", bank.get_branch("ATM"), );
	   }
	}

}
