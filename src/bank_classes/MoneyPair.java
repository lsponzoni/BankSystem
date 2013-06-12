package bank_classes;

public class MoneyPair {
		private static final String locale_number_division = ",";
		private int cents, units; 
		
		public MoneyPair(int value){
			cents = (value % 100);
			units = (value - cents)/100;	
		}

		public MoneyPair(int units, int cents){
			this.cents = cents;
			this.units = units;	
		}
		
		public MoneyPair(String initial_balance) {
			String[] array = initial_balance.split(locale_number_division);
			if (array.length == 2){
			cents = parseCents(array[1]);
			units = Integer.parseInt(array[0]);
			}
			else {
			units = Integer.parseInt(initial_balance);
			cents = 0;
			}
		}
		private static final int parseCents(String cents){
			// Cents should end up being a 2 digit number this is not guaranteed by this version
			String first2digits = cents.substring(0, 2);
			return Math.abs(Integer.parseInt(first2digits));
		}
		public int get_cents(){
			int cents_value;
			if (units > 0){
				cents_value =  cents;
			}else{ 
				cents_value = -cents;
			}

			return cents_value;
		}
		
		public int get_dollars(){
			return units;
		}
		public String toString(){
			return String.format("%d,%02d",units,Math.abs(cents));
		}
	}	


