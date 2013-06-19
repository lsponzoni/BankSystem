package bank_classes;

import java.util.Calendar;

public class Transaction {
	private String gate_branch_code;
	private Calendar time_stamp;
	private Money value;
	private String code;

	protected Transaction(String code, String gate_branch_code, Money value){
		this.gate_branch_code = gate_branch_code;
		this.time_stamp = Calendar.getInstance();
		this.value = value;
		this.code = code;
	}
	
	protected void set_date(Calendar time_stamp){
		this.time_stamp = time_stamp;
	}
	
	public Money get_value() {
          return value;
	}
	public String get_code(){
		return code;
	}
	public Calendar get_date() {
          return time_stamp;
	}

	public boolean newer_than(Calendar to) {
           return time_stamp.before(to);
	}

	public boolean older_than(Calendar from) {
           return time_stamp.after(from);
	}

	public boolean in_time_period(Calendar from, Calendar to) {
           return older_than(from) && newer_than(to);     
	}
	
	public String toString(){
		return " $" + value.toString() + " at "+ gate_branch_code + " " + time_stamp.toString();
	}
}
