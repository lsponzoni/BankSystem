package Stubs;


public class Client extends bank_classes.Client {

	public Client(String username, String password) {
		super("", "", null, "", "", "");
		super.setUsername(username);
		super.setPassword(password);
	}

	public String toString(){
		return "Ignore";
	}
	public boolean usernameMatch(String username) {
		return "SAME".equals(username);
	}
	/**
	 * @param args
	 */

}
