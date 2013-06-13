

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;

import bank_classes.User;

public class UserCases {
	User user; 
	

	@Before
	public void setUp() throws Exception {
		Calendar birth = new GregorianCalendar(2021,12,13);
		user = new User("John","Stuart",birth,"193022","XCKLMN$#え");
	}

	@Test
	public void username_matching_matches() {
		assertTrue(user.usernameMatch("193022"));
	}

	@Test
	public void username_matching_dont_match() {
		assertFalse(user.usernameMatch("179411"));
	}

	@Test
	public void password_matching_matches() {
		assertTrue(user.passwordMatch("XCKLMN$#え"));
	}

	@Test
	public void password_matching_dont_match() {
		assertFalse(user.passwordMatch("OTHER"));
	}

}
