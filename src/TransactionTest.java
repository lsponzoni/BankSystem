import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TransactionTest {
	static Date first_date;
	static Date second_date;
	static Date third_date;

	@SuppressWarnings("static-access")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		first_date = new Date();
		Thread.currentThread().sleep(1);
		second_date = new Date();
		Thread.currentThread().sleep(1);
		third_date = new Date();
	}

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void date_order(){
		assertTrue(first_date.before(second_date));
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
