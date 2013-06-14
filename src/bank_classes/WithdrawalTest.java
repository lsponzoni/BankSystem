package bank_classes;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import bankexceptions.InvalidTransaction;

public class WithdrawalTest {
	Withdrawal w;
	@Before
	public void setUp() throws Exception {
		w = new Withdrawal("","", new Money(1));
	}

	@Test
	public void returns_negative_value() {
		assertTrue(w.get_value().is_negative());
	}

	@Test(expected = InvalidTransaction.class)
	public void don_t_accept_negative() throws InvalidTransaction {
		new Withdrawal("","",new Money(-1));
	}

}
