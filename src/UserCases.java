

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;

import bank_classes.User;

public class UserCases {
	private static final Calendar birth = new GregorianCalendar(2021,12,13);
	private static final String name = "John";
	private static final  String surname = "Stuart";
	private static final  String first_username = "193022";
	private static final  String first_password = "XCKLMN$#え";
	
	User user; 
	

	@Before
	public void setUp() throws Exception {

		user = new User(name,surname,birth,first_username,first_password);
	}

	@Test
	public void username_matching_matches() {
		assertTrue(user.usernameMatch(first_username));
	}

	@Test
	public void username_matching_dont_match() {
		assertFalse(user.usernameMatch("179411"));
	}

	@Test
	public void password_matching_matches() {
		assertTrue(user.passwordMatch(first_password));
	}
	
	@Test
	public void password_matching_dont_match() {
		assertFalse(user.passwordMatch("OTHER"));
	}
	
	@Test
	public void user_change_matches_new_name() {
		user.setUsername("new");
		assertTrue(user.usernameMatch("new"));
	}

	@Test
	public void username_change_doesn_t_match_old() {
		user.setUsername("new");
		assertFalse(user.usernameMatch(first_username));
	}

	
	@Test
	public void password_change_matches_new_password() {
		user.setPassword("new");
		assertTrue(user.passwordMatch("new"));
	}

	@Test
	public void password_change_doesn_t_match_old_password() {
		user.setPassword("new");
		assertFalse(user.passwordMatch(first_password));
	}
	
	@Test
	public void new_random_password_is_not_same_between_two(){
		String password = User.new_random_password();
		String same_password = User.new_random_password();
		assertThat(password, is(not(same_password)));
	}

}
