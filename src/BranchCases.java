


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import bank_classes.Branch;
import bank_classes.Clerk;
import bank_classes.Client;
import bankexceptions.DuplicateException;
import bankexceptions.NotFoundException;

public class BranchCases {
	Branch b;
	Client c1;
	Clerk c2;
	@Before
	public void setUp() throws Exception {
		Calendar birthday = Calendar.getInstance();
		c1 = new Client("NAME","SURNAME",birthday,"CPF");
		c2 = new Clerk("Clerk","Smith",birthday, "SAME", "FORGET_IT");

		b = new Branch("193022","Agência Centro");
		b.add_clerk(c2);
		b.add_client(c1);
	}

	@Test 
	public void code_matches() {
		assertTrue(b.code_match("193022"));
	}

	@Test 
	public void code_should_not_match()  {
		assertFalse(b.code_match("193042"));
	}

	@Test(expected = NotFoundException.class) 
	public void cant_get_inexistent_client() throws NotFoundException {
		String inexistent_user = "John Constantine";
		b.get_client(inexistent_user);
	}
	@Test
	public void inexistent_client_doesnt_exist() {
		String inexistent_user = "John Constantine";
		boolean account_status = b.has_account(inexistent_user);
		assertFalse(account_status);
	}

	@Test //(expected = DuplicateException.class) 
	public void can_find_client() {//throws DuplicateException {
		String can_find = "SAME";
		assertTrue(b.has_account(can_find));
	}
	@Test(expected = DuplicateException.class) 
	public void cant_add_2_of_same() throws DuplicateException {
		b.add_client(c1);
	}


	@Test(expected = NotFoundException.class) 
	public void cant_get_inexistent_clerk() throws NotFoundException {
		String inexistent_user = "John Constantine";
		b.get_clerk(inexistent_user);
	}
	@Test
	public void inexistent_clerk_doesnt_work_here() {
		String inexistent_user = "John Constantine";
		boolean account_status = b.works_here(inexistent_user);
		assertFalse(account_status);
	}

	@Test //(expected = DuplicateException.class) 
	public void can_find_clerk() {//throws DuplicateException {
		String can_find = "SAME";
		assertTrue(b.works_here(can_find));
	}
	@Test(expected = DuplicateException.class) 
	public void cant_add_2_of_clerks() throws DuplicateException {
		b.add_clerk(c2);
	}
}
