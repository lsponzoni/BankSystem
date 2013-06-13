package bankexceptions;

public class NotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private String user_id;
	
	public NotFoundException(String user_id){
		super();
		this.user_id = user_id;
	}
	
	public String toString(){
		return "Can't find " + user_id + "\n " + super.toString();
	}
}
