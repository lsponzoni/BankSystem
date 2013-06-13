import static org.junit.Assert.*;

import org.junit.Test;
import bank_classes.Money;


public class MoneyTest {
	Money money;
	@Test
	public void create_money_from_int_pass() {
		money = new Money(112);
		assertEquals("1,12",money.toString());
	}
	@Test
	public void create_money_from_double(){
		money = new Money(1.12);
		assertEquals("1,12",money.toString());
	}
	@Test
	public void create_money_from_String(){
		money = new Money("1,12");
		assertEquals("1,12",money.toString());
	}

	@Test
	public void create_money_from_String_no_cents(){
		money = new Money("1");
		assertEquals("1,00",money.toString());
	}

	@Test
	public void create_cents_from_String_with_trailing_digits(){
		money = new Money("1,1298");
		assertEquals("1,12",money.toString());
	}

	@Test (expected = StringIndexOutOfBoundsException.class)
	public void create_cents_from_String_with_insufficient_digits(){
		money = new Money("1,1");
		
	}

	@Test
	public void add_money(){
		Money m1 = new Money(112);
		Money m2 = new Money(112);
		
		money = m1.add(m2);
		
		assertEquals(money.toString(),"2,24");
	}

	@Test
	public void subtract_money(){
		Money m1 = new Money(112);
		Money m2 = new Money(112);
		
		money = m1.add(m2.minus());
		
		assertEquals(money.toString(),"0,00");
	}

	@Test
	public void do_minus_money(){
		Money m1 = new Money(112);
		
		money = m1.minus();
		
		assertEquals(money.toString(),"-1,12");
	}
	
	@Test
	public void negative_value_money(){
		Money money = new Money(-102);
				
		assertEquals(money.toString(),"-1,02");
	}

	@Test
	public void not_a_fraction_string_money(){
		Money money = new Money("-102");
				
		assertEquals(money.toString(),"-102,00");
	}

	@Test
	public void negative_string_money(){
		Money money = new Money("-1,02");
				
		assertEquals(money.toString(),"-1,02");
	}

}
