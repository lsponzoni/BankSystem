import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import bank_classes.Money;
import bank_classes.Transfer;
import bank_classes.TransferRole;


public class TransferTest {
	Date time_stamp;
	Money m;
	String acc_to, br_to, acc_from, br_from; 

	@Before
	public void setUp() throws Exception {
		time_stamp = new Date(); 
		m = new Money(111);
		acc_to = "account code to";
		br_to = "branch code to";
		acc_from = "account code from";
		br_from = "branch code from";
	}

	@Test
	public void value_is_negative_for_transfer_from()  {
		Transfer t = new Transfer(TransferRole.SEND, "here", time_stamp, m,
				 acc_to, br_to, acc_from, br_from);
		
		assertEquals(new Money(-111).toString(), t.get_value().toString());
	}
	@Test
	public void value_is_positive_for_transfer_to() {
		Transfer t = new Transfer(TransferRole.RECEIVE, "here", time_stamp, m,
				 "account code to", "branch code to", 
				 "account code from", "branch code from");
		Money v = t.get_value();
		assertEquals(new Money(111).toString(), t.get_value().toString());
	}
}
