


import static org.junit.Assert.*;

import org.junit.Test;

import bank_classes.Account;
import bank_classes.Money;
import bankexceptions.InvalidTransaction;

public class AccountTest {
	//testa o get_account_code
	@Test
	public void test_account_code() throws InvalidTransaction {
		String result;
		Money euro = new Money("10");
		Account Test = new Account("ABC","FIL002",euro);//Account(String account_code, String branch_code, Money initial_balance)
		result=Test.get_account_code();
		assertEquals(result,"ABC");
	}
	//testa o get_balace
	@Test
	public void test_get_balace() throws InvalidTransaction {
		
		Money euro = new Money("10");
		Money balance = new Money("10");
		Money expected = new Money("10");
		Account Test = new Account("ABC","FIL002",euro);//Account(String account_code, String branch_code, Money initial_balance)
		balance=Test.get_balance();
		assertEquals(expected,balance);
	}
	
	@Test
	public void test_get_negative_balace() throws InvalidTransaction {
		
		Money euro = new Money("-100");
		Money balance = new Money(0);
		Money expected = new Money("-100");
		Account Test = new Account("ABC","FIL002",euro);//Account(String account_code, String branch_code, Money initial_balance)
		balance=Test.get_balance();
		assertEquals(expected,balance);
	}
	
	//testa o get_branch_code
	@Test
	public void test_branch_code() throws InvalidTransaction {
		String result;
		Money euro = new Money("10");
		Account Test = new Account("ABC","FIL002",euro);//Account(String account_code, String branch_code, Money initial_balance)
		result=Test.get_branch_code();
		assertEquals(result,"FIL002");
	}
	
	
	
}
