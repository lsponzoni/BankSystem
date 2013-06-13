


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import bank_classes.Branch;
import bank_classes.User;
import bankexceptions.NotFoundException;

public class BranchCases {
	Branch b;
	List<User> clerks, clients;


	@Before
	public void setUp() throws Exception {
		 clients = new ArrayList<User>();
		 clerks = new ArrayList<User>();		 	
		 b = new Branch("193022","Agência Centro",clients, clerks);
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

}
