package bank_classes;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Reader {
	Scanner scan_input;

	public Reader(){
		InputStreamReader cin = new InputStreamReader(System.in);
		scan_input = new Scanner(cin);
	}
	
	public String read(){
		return scan_input.nextLine();
	}
	
	public void close(){
		scan_input.close();
	}
}
