package bankexceptions;

public class InvalidTransfer extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7262491504485042855L;
	private String message;
	public InvalidTransfer(String message){
		super();
		this.message = message;
	}
	
	public String toString(){
		return message + super.toString();  
	}
}
