package bankexceptions;

public class InvalidTransaction extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7262491504485042855L;
	private String message;
	public InvalidTransaction(String message){
		super();
		this.message = message;
	}
	
	public String toString(){
		return message + super.toString();  
	}
}
