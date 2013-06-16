

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import bank_classes.Deposit;
import bank_classes.History;
import bank_classes.Money;
import bank_classes.Transaction;
import bank_classes.Transfer;
import bank_classes.TransferRole;
import bank_classes.Withdrawal;
import bankexceptions.InvalidTransaction;

 
public class HistoryTest {
	History h;
	static Date first_date;
	static Date second_date;
	static Date third_date;

	private static final Money m = new Money(1);

	@BeforeClass
	public static void date_Order() throws InterruptedException, ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	first_date = sdf.parse("2009-12-31");
    	second_date = sdf.parse("2010-01-31");
    	third_date = sdf.parse("2019-12-31");
	}
	@Before
	public void setUp() throws Exception {

		h = new History();
	}
	
	@Test
	public void history_starts_empty() {
		assertEquals("0,00",h.sum_transaction_values().toString());
	}

	@Test
	public void history_sums_all_values() throws InvalidTransaction {
		Transfer t = new Transfer(TransferRole.RECEIVE,"ok",first_date, m,"me", "ok","to", "to"	);
		Deposit d = new Deposit("me", "ok",m, "xyz", first_date);
		Withdrawal w = new Withdrawal("ok", "ok", m, first_date);
		h.store_transaction(d);
		h.store_transaction(w);
		h.store_transaction(t);
		assertEquals(m.toString(),h.sum_transaction_values().toString());
	}

	@Test
	public void history_filters_return_all() throws InvalidTransaction {
		Transfer t = new Transfer(TransferRole.RECEIVE,"ok",second_date, m,"me", "ok","to", "to"	);
		Deposit d = new Deposit("me", "ok",m, "xyz", second_date);
		Withdrawal w = new Withdrawal("ok", "ok", m, second_date);
		h.store_transaction(d);
		h.store_transaction(w);
		h.store_transaction(t);
		assertEquals(h.toString(),h.get_transactions(first_date,third_date).toString());
	}

	@Test
	public void history_filters_empty_when_out_of_bounds() throws InvalidTransaction {
		Transfer t = new Transfer(TransferRole.RECEIVE,"ok",first_date, m,"me", "ok","to", "to"	);
		Deposit d = new Deposit("me", "ok",m, "xyz", first_date);
		Withdrawal w = new Withdrawal("ok", "ok", m, first_date);
		h.store_transaction(d);
		h.store_transaction(w);
		h.store_transaction(t);
		
		assertEquals((new History()).toString(),h.get_transactions(second_date, third_date).toString());
	}

	@Test
	public void history_filters_empty_when_equal_to_begin_and_end() throws InvalidTransaction {
		Transfer t = new Transfer(TransferRole.RECEIVE,"ok",first_date, m,"me", "ok","to", "to"	);
		Deposit d = new Deposit("me", "ok",m, "xyz", first_date);
		Withdrawal w = new Withdrawal("ok", "ok", m, first_date);
		h.store_transaction(d);
		h.store_transaction(w);
		h.store_transaction(t);
		
		assertEquals((new History()).toString(), h.get_transactions(first_date,first_date).toString());
	}

	@Test
	public void history_filters_empty_when_equal_to_begin_() throws InvalidTransaction {
		Transfer t = new Transfer(TransferRole.RECEIVE,"ok",first_date, m,"me", "ok","to", "to"	);
		Deposit d = new Deposit("me", "ok",m, "xyz", first_date);
		Withdrawal w = new Withdrawal("ok", "ok", m, first_date);
		h.store_transaction(d);
		h.store_transaction(w);
		h.store_transaction(t);
		
		assertEquals((new History()).toString(), h.get_transactions(first_date,third_date).toString());
	}

	@Test
	public void history_filters_empty_when_equal_to_last() throws InvalidTransaction {
		Transfer t = new Transfer(TransferRole.RECEIVE,"ok",third_date, m,"me", "ok","to", "to"	);
		Deposit d = new Deposit("me", "ok",m, "xyz",third_date);
		Withdrawal w = new Withdrawal("ok", "ok", m,third_date);
		h.store_transaction(d);
		h.store_transaction(w);
		h.store_transaction(t);
		
		assertEquals((new History()).toString(), h.get_transactions(first_date,third_date).toString());
	}

	@Test //Unespected exception Invalid Transaction assert won't execute.
	public void history_adds_even_when_infered_Type() throws InvalidTransaction {
		Transaction t = new Transfer(TransferRole.RECEIVE,"ok",third_date, m,"me", "ok","to", "to"	);

		h.store_transaction(t);
		assertTrue(true);
	}
	@Ignore
	@Test (expected = InvalidTransaction.class)
	public void history_adds_even_when_transfer_fail_this() throws InvalidTransaction {
		Transaction t = new Transfer(TransferRole.RECEIVE,"ok",third_date, m,"me", "ok","to", "to"	);

		h.store_transaction(t);
	}

}
