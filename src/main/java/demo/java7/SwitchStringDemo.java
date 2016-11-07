package demo.java7;

public class SwitchStringDemo {
	
	public static void main(String[] args) {
		String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		for (String day : days) {
			System.out.println(day + ": \t" + getTypeOfDayWithSwitchStatement(day));
		}
	}
	
	public static String getTypeOfDayWithSwitchStatement(String dayOfWeekArg) {
	     String typeOfDay;
	     switch (dayOfWeekArg) {
	         case "Monday":
	             typeOfDay = "Start of work week";
	             break;
	         case "Tuesday":
	         case "Wednesday":
	         case "Thursday":
	             typeOfDay = "Midweek";
	             break;
	         case "Friday":
	             typeOfDay = "End of work week";
	             break;
	         case "Saturday":
	         case "Sunday":
	             typeOfDay = "Weekend";
	             break;
	         default:
	             throw new IllegalArgumentException("Invalid day of the week: " + dayOfWeekArg);
	     }
	     return typeOfDay;
	}

}
