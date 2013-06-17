import static org.junit.Assert.*;

import java.util.Calendar;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bank_classes.Money;
import bank_classes.Transaction;


public class TransactionTest {
	static Calendar first_date;
	static Calendar second_date;
	static Calendar third_date;
	static Money value = new Money(1);
	Transaction t1,t2,t3;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		first_date.set(Calendar.YEAR,2009);
    	first_date.set(Calendar.MONTH,11);
    	first_date.set(Calendar.DAY_OF_MONTH,31);
    	second_date.set(Calendar.YEAR,2010);
    	second_date.set(Calendar.MONTH,0);
    	second_date.set(Calendar.DAY_OF_MONTH,31);
    	third_date.set(Calendar.YEAR,2019);
    	third_date.set(Calendar.MONTH,11);
    	third_date.set(Calendar.DAY_OF_MONTH,31);
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
