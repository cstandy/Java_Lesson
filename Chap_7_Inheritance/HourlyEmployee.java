import java.util.Date;

public class HourlyEmployee extends Employee {
	
	private double wageRate;

	public HourlyEmployee(String theName, Date theDate, double rate){
		super (theName, theDate);
		wageRate = rate;
	}

	public String getName() { return "Hourly Employee: " + super.getName(); }
}
