import static org.junit.Assert.*;

import org.junit.Test;
import bank_classes.Money;


public class MoneyTest {
	Money money;
	@Test
	public void create_money_from_int_pass() {
		money = new Money(112);
		assertEquals(money.toString(),"1,12");
	}
}
