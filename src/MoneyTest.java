import static org.junit.Assert.*;

import org.junit.Test;
import bank_classes.Money;
import bankexceptions.InvalidTransaction;


public class MoneyTest {
	Money money;
	@Test
	public void create_money_from_double_looking_like_a_int() {
		money = new Money(112);
		assertEquals("112,00",money.toString());
	}
	@Test
	public void create_money_from_double(){
		money = new Money(1.12);
		assertEquals("1,12",money.toString());
	}
	@Test
	public void create_money_from_String() throws InvalidTransaction{
		money = Money.parseString("1,12");
		assertEquals("1,12",money.toString());
	}

	@Test
	public void create_money_from_String_no_cents() throws InvalidTransaction{
		money = Money.parseString("1");
		assertEquals("1,00",money.toString());
	}

	@Test
	public void create_cents_from_String_with_trailing_digits() throws InvalidTransaction{
		money = Money.parseString("1,1298");
		assertEquals("1,12",money.toString());
	}

	@Test (expected = StringIndexOutOfBoundsException.class)
	public void create_cents_from_String_with_insufficient_digits() throws InvalidTransaction{
		money = Money.parseString("1,1");
	}

	@Test
	public void add_money(){
		Money m1 = new Money(112);
		Money m2 = new Money(112);
		
		money = m1.add(m2);
		
		assertEquals(money.toString(),"224,00");
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
		
		assertEquals(money.toString(),"-112,00");
	}
	
	@Test 
	public void negative_value_money(){
		Money money = new Money(-102);
				
		assertEquals(money.toString(),"-102,00");
	}

	@Test (expected = InvalidTransaction.class)
	public void error_string_money() throws InvalidTransaction{
		Money money = Money.parseString("-102");
	}

	@Test
	public void fraction_string_money() throws InvalidTransaction{
		Money money = Money.parseString("1,02");
		assertEquals(money.toString(),"1,02");
	}

	@Test (expected = InvalidTransaction.class)
	public void fraction_negative_money() throws InvalidTransaction{
		Money money = Money.parseString("0,-02");
	}

	@Test 
	public void negative_money(){
		Money money = new Money(-0.02);
		assertTrue(money.is_negative());
	}

}
