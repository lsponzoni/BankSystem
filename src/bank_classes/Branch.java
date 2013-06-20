package bank_classes;

import java.util.ArrayList;
import java.util.Collection;

import bankexceptions.DuplicateException;
import bankexceptions.NotFoundException;

public class Branch {
	private Collection<User> accounts;
	private Collection<User> clerks;
	private String code;
	private String name;

	public Branch(String code, String name) {
		this.code = code;
		this.name = name;
		this.clerks = new ArrayList<User>(10);
		this.accounts = new ArrayList<User>(10);
	}

	public Branch(String code, String name, Collection<User> client,
			Collection<User> clerks) {
		this.code = code;
		this.name = name;
		this.clerks = clerks;
		this.accounts = client;
	}

	public void add_clerk(Clerk clerk) throws DuplicateException {
		add_user(clerks, clerk);
	}

	public void add_client(Client client) throws DuplicateException {
		add_user(accounts, client);
	}

	private void add_user(Collection<User> collection, User user)
			throws DuplicateException {
		if (find(collection, user.getUsername())) {
			throw new DuplicateException(user.toString());
		}
		collection.add(user);
	}

	public boolean code_match(String branch_code) {
		return code.equals(branch_code);
	}

	private boolean find(Iterable<User> collection, String user_id) {
		boolean found = true;
		try {
			get_user(collection, user_id);
		} catch (NotFoundException e) {
			found = false;
		}
		return found;
	}

	public Clerk get_clerk(String username) throws NotFoundException {
		return (Clerk) get_user(clerks, username);
	}

	public Client get_client(String username) throws NotFoundException {
		return (Client) get_user(accounts, username);
	}

	public String get_code() {
		return code;

	}

	public String get_name() {
		return name;
	}

	private User get_user(Iterable<User> collection, String user_id)
			throws NotFoundException {
		User search_result = null;
		
		for (User each : collection) {
			if (each.usernameMatch(user_id)) {
				search_result = each;
				break;
			}
		}
		
		if (search_result == null) {
			throw new NotFoundException(user_id);
		}
		return search_result;
	}

	public boolean has_account(String client) {
		return find(accounts, client);

	}

	public String string_branch_entry() {
		return this.get_code() + " :: " + this.get_name() + "\n";
	}

	public String toString() {
		String s;
		s = "Branch: " + name + "\n" + " Code: " + code + "\n"
				+ " Clerk List: \n " + clerks.toString() + " Client List: \n "
				+ accounts.toString();
		return s;
	}

	public boolean works_here(String clerk) {
		return find(clerks, clerk);
	}
}
