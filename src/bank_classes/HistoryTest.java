package bank_classes;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import bankexceptions.InvalidTransaction;

public class HistoryTest {
	History h;
	static Date first_date;
	static Date second_date;
	static Date third_date;

	private static final Money m = new Money(1);

	@BeforeClass
	public static void date_Order() throws InterruptedException{
		first_date = new Date();
		Thread.currentThread().sleep(1);
		second_date = new Date();
		Thread.currentThread().sleep(1);
		third_date = new Date();
	}
	@Before
	public void setUp() throws Exception {

		h = new History();
	}

	@Ignore
	@Test
	public void date_order(){
		assertTrue(first_date.before(second_date));
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
		Transfer t = new Transfer(TransferRole.RECEIVE,"ok",first_date, m,"me", "ok","to", "to"	);
		Deposit d = new Deposit("me", "ok",m, "xyz", first_date);
		Withdrawal w = new Withdrawal("ok", "ok", m, first_date);
		h.store_transaction(d);
		h.store_transaction(w);
		h.store_transaction(t);
		assertEquals(h,h.get_transactions(first_date,third_date));
	}

	@Test
	public void history_filters_empty_when_out_of_bounds() throws InvalidTransaction {
		Transfer t = new Transfer(TransferRole.RECEIVE,"ok",first_date, m,"me", "ok","to", "to"	);
		Deposit d = new Deposit("me", "ok",m, "xyz", first_date);
		Withdrawal w = new Withdrawal("ok", "ok", m, first_date);
		h.store_transaction(d);
		h.store_transaction(w);
		h.store_transaction(t);
		
		assertEquals((new History()).toString(),h.get_transactions(second_date,third_date).toString());
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


}
