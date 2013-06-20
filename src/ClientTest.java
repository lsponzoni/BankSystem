import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.util.Calendar;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bank_classes.Account;
import bank_classes.Client;
import bank_classes.Money;
import bank_classes.Transaction;
import bankexceptions.DuplicateException;
import bankexceptions.InvalidTransaction;


public class ClientTest {
	Client c ;
	Account account;
	
	@Before
	public void setUp() throws Exception {
		c = new Client("a", "s", Calendar.getInstance(), "139031131");
	}

	@Test (expected = DuplicateException.class)
	public void test_cant_add_two_accounts() throws InvalidTransaction, DuplicateException {
		account = new Account("Esse","Aqui",new Money(0));
		c.add_account(account);
		c.add_account(account);
	}

	@Test (expected = InvalidTransaction.class)
	public void before_adding_account_dont_add_transactions() throws InvalidTransaction, DuplicateException {
		Constructor<Transaction> constructor = Transaction.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		Transaction t = constructor.newInstance("","",Calendar.getInstance(),new Money(0)); 
		c.add_transaction(t);
	}

}
