package bankexceptions;

public class DuplicateException extends Exception {
	/**
	 * Dup exception classes
	 */
	private static final long serialVersionUID = -5104294610892587932L;
	private String user_name;
	public DuplicateException(String user_name) {
		super();
		this.user_name = user_name;
	}
	
	public String toString(){
		return "Duplicating entry " + user_name + super.toString();
	}

}
