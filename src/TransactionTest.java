import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bank_classes.Money;
import bank_classes.Transaction;


public class TransactionTest {
	static Date first_date;
	static Date second_date;
	static Date third_date;
	static Money value = new Money(1);
	Transaction t1,t2,t3;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	first_date = sdf.parse("2009-12-31");
    	second_date = sdf.parse("2010-01-31");
    	third_date = sdf.parse("2019-12-31");
	}

	@Before
	public void setUp() throws Exception {
		t1 = new Transaction("Ac","Br",first_date,value);
		t2 = new Transaction("Ac","Br",second_date,value);
		t3 = new Transaction("Ac","Br",third_date,value);;
	}
	
	@Test
	public void date_order(){
		assertTrue(t1.newer_than(second_date));
	}

	@Test
	public void date_order_1(){
		assertFalse(t1.older_than(second_date));
	}
	
	@Test
	public void date_order_2(){
		assertFalse(t1.older_than(first_date));
	}
	
	@Test
	public void date_order3(){
		assertFalse(t1.newer_than(first_date));
	}

	@Test
	public void between_both() {
		assertTrue(t2.in_time_period(first_date, third_date));
	}

	@Test
	public void before_both() {
		assertFalse(t1.in_time_period(second_date, third_date));
	}

	@Test
	public void after_both() {
		assertFalse(t3.in_time_period(first_date, second_date));
	}

	@Test
	public void between_both_swapped() {
		assertFalse(t2.in_time_period(third_date,first_date));
	}

}
