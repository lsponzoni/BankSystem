import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bank_classes.Money;
import bank_classes.Transfer;
import bankexceptions.InvalidTransfer;


public class TransferTest {
	Date time_stamp;
	Money m;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		time_stamp = new Date(); 
		m = new Money(111);
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test(expected = InvalidTransfer.class)
	public void error_when_this_is_not_from_or_to() throws InvalidTransfer {
		Transfer t = new Transfer("account code any", "here", time_stamp, m,
				 "account code to", "branch code to", 
				 "account code from", "branch code from");		
	}
	@Test
	public void value_is_negative_for_transfer_from() throws InvalidTransfer {
		Transfer t = new Transfer("account code from", "here", time_stamp, m,
				 "account code to", "branch code to", 
				 "account code from", "branch code from");
		
		assertEquals(new Money(-111).toString(), t.get_value().toString());
	}
	@Test
	public void value_is_positive_for_transfer_to() throws InvalidTransfer {
		Transfer t = new Transfer("account code to", "here", time_stamp, m,
				 "account code to", "branch code to", 
				 "account code from", "branch code from");
		Money v = t.get_value();
		assertEquals(new Money(111).toString(), t.get_value().toString());
	}
}
