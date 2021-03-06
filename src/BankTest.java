import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;


import bank_classes.Bank;
import bank_classes.Clerk;
import bank_classes.Client;
import bankexceptions.DuplicateException;
import bankexceptions.InvalidTransaction;
import bankexceptions.NotFoundException;



public class BankTest {
	  @Test (expected = DuplicateException.class)
	   public void duplicated_account_mario() throws DuplicateException, InvalidTransaction, NotFoundException
	   {
		   Bank bank1 = new Bank();
		   bank1= SystemTest.populate();
		   bank1.add_client_account(bank1.get_branch("FIL2001"), bank1.get_client("193022","FIL2001"),"1000");
	   }
	   
	   @Test (expected = DuplicateException.class)
	   public void duplicated_account_julia() throws DuplicateException, InvalidTransaction, NotFoundException
	   {
		   Bank bank2 = new Bank();
		   bank2 = SystemTest.populate();
		   bank2.add_client_account(bank2.get_branch("FIL3001"), bank2.get_client("194022","FIL3001"),"1000");
	   }
	   
	   @Test (expected = DuplicateException.class)
	   public void duplicated_account_fefe() throws DuplicateException, InvalidTransaction, NotFoundException
	   {
		   Bank bank3 = new Bank();
		   bank3 = SystemTest.populate();
		   bank3.add_client_account(bank3.get_branch("FIL3001"), bank3.get_client("195022","FIL3001"),"1000");
	   }
	   
	   
	   //testa clerk duplicado	   
	   
	   @Test (expected = DuplicateException.class)
	   public void duplicated_clerks_mario() throws DuplicateException, NotFoundException, InvalidTransaction
	   {
		   Calendar born_at = new GregorianCalendar();
			born_at.set(Calendar.YEAR, 1980);
			born_at.set(Calendar.MONTH, 11); // 11 = december
			born_at.set(Calendar.DAY_OF_MONTH, 24);
		   Clerk clerk_test = new Clerk("Mario","Mario",born_at,"193022","It's me Mario");//cleark mario
		   Bank bank = new Bank();
		   bank = SystemTest.populate();
		   bank.add_clerk_account(bank.get_branch("FIL2001"),clerk_test);//tries to add an existing client
	   }
	   
	   @Test (expected = DuplicateException.class)
	   public void duplicated_clerks_super_mario() throws DuplicateException, NotFoundException, InvalidTransaction
	   {
		   Calendar born_at = new GregorianCalendar();
			born_at.set(Calendar.YEAR, 1980);
			born_at.set(Calendar.MONTH, 11); // 11 = december
			born_at.set(Calendar.DAY_OF_MONTH, 24);
		   Clerk clerk_test=new Clerk("Super","Mario",born_at,"333022","So many marios.");
		   Bank bank = new Bank();
		   bank = SystemTest.populate();
		   bank.add_clerk_account(bank.get_branch("FIL3001"),clerk_test);//tries to add an existing client
	   }   
	   
	     
	   
	   
	 //testa transferencia 
	   	   
	   @Test
	   public void test_transfer_mario_to_julia() throws InvalidTransaction, NotFoundException
	   {//mario to julia
		   String result;
		   Bank bank = new Bank();
		   bank = SystemTest.populate();
		   Client sender = bank.get_client("193022", "FIL2001");
		   result = bank.transfer("999","194022","FIL3001", bank.get_branch("FIL2001"), sender.get_account());
		   assertEquals("Sucess", result);
	   }  
	   
	   @Test
	   public void test_transfer_julia_to_mario() throws InvalidTransaction, NotFoundException
	   {//julia to mario
		   String result;
		   Bank bank = new Bank();
		   bank = SystemTest.populate();
		   Client sender = bank.get_client("194022", "FIL3001");
		   result = bank.transfer("999","193022","FIL2001", bank.get_branch("FIL3001"), sender.get_account());
		   assertEquals("Sucess", result);
	   }
	   
	   
	   @Test
	   public void test_transfer_mario_fefe() throws InvalidTransaction, NotFoundException
	   {//mario to fefe
		   String result;
		   Bank bank = new Bank();
		   bank = SystemTest.populate();
		   Client sender = bank.get_client("193022", "FIL2001");
		   result = bank.transfer("999","195022","FIL3001", bank.get_branch("FIL2001"), sender.get_account());
		   assertEquals("Sucess", result);
	   }
	   
	   
	   @Test
	   public void test_transfer_fefe_to_mario() throws InvalidTransaction, NotFoundException
	   {//fefe to mario
		   String result;
		   Bank bank = new Bank();
		   bank = SystemTest.populate();
		   Client sender = bank.get_client("195022", "FIL3001");
		   result = bank.transfer("999","193022","FIL2001", bank.get_branch("FIL3001"), sender.get_account());
		   assertEquals("Sucess", result);
	   }
	   	   
	   @Test
	   public void test_transfer_julia_to_fefe() throws InvalidTransaction, NotFoundException
	   {//julia to fefe
		   String result;
		   Bank bank = new Bank();
		   bank = SystemTest.populate();
		   Client sender = bank.get_client("194022", "FIL3001");
		   result = bank.transfer("999","195022","FIL3001", bank.get_branch("FIL3001"), sender.get_account());
		   assertEquals("Sucess", result);
	   }
	   
	   @Test
	   public void test_transfer_fefe_to_julia() throws InvalidTransaction, NotFoundException
	   {//fefe to julia
		   String result;
		   Bank bank = new Bank();
		   bank = SystemTest.populate();
		   Client sender = bank.get_client("195022", "FIL3001");
		   result = bank.transfer("999","194022","FIL3001", bank.get_branch("FIL3001"), sender.get_account());
		   assertEquals("Sucess", result);
	   }
	     
	   
	      
	   //teste de saques
	   
	   @Test
	   public void test_withdraw_mario() throws InvalidTransaction//testa saque de mario
, NotFoundException
	   {
		   String result;
		   Bank bank = new Bank();
		   bank = SystemTest.populate();
		   Client sender = bank.get_client("193022", "FIL2001");
		   result = bank.withdraw("10",bank.get_branch("FIL2001"),sender.get_account());
		   assertEquals("Get your money.\n", result);
		   
	   }
	   @Test (expected = InvalidTransaction.class)
	   public void test_withdraw_invalido_mario() throws InvalidTransaction//testa saque invalido de mario
, NotFoundException
	   {
		   String result;
		   Bank bank = new Bank();
		   bank = SystemTest.populate();
		   Client sender = bank.get_client("193022", "FIL2001");
		   result = bank.withdraw("-10",bank.get_branch("FIL2001"),sender.get_account());
		      
	   }
	   
	   public void test_withdraw_julia() throws InvalidTransaction//testa saque de julia
, NotFoundException
	   {
		   String result;
		   Bank bank = new Bank();
		   bank = SystemTest.populate();
		   Client sender = bank.get_client("194022", "FIL3001");
		   result = bank.withdraw("10",bank.get_branch("FIL3001"),sender.get_account());
		   assertEquals("Get your money.\n", result);	   
	   }
	   
	   @Test (expected = InvalidTransaction.class)
	   public void test_withdraw_invalido_julia() throws InvalidTransaction//testa saque invalido de julia
, NotFoundException
	   {
		   String result;
		   Bank bank = new Bank();
		   bank = SystemTest.populate();
		   Client sender = bank.get_client("194022", "FIL3001");
		   result = bank.withdraw("-10",bank.get_branch("FIL3001"),sender.get_account());
	   }
	   @Test
	   public void test_withdraw_fefe() throws InvalidTransaction, NotFoundException
	   {
		   String result;
		   Bank bank = new Bank();
		   bank = SystemTest.populate();
		   Client sender = bank.get_client("195022", "FIL3001");
		   result = bank.withdraw("10",bank.get_branch("FIL3001"),sender.get_account());
		   assertEquals("Get your money.\n", result);	   
	   }
	   
	   @Test (expected = InvalidTransaction.class)
	   public void test_withdraw_invalido_fefe() throws InvalidTransaction, NotFoundException
	   {
		   String result;
		   Bank bank = new Bank();
		   bank = SystemTest.populate();
		   Client sender = bank.get_client("195022", "FIL3001");
		   result = bank.withdraw("-10",bank.get_branch("FIL3001"),sender.get_account());   
	   }
	
}
