import java.util.Date;

public class Employee {
	
	private String name;
	private Date   hireDate;

	public Employee(){};

	public Employee(String theName, Date theDate){
		name     = theName;
		hireDate = theDate;
	}

	public Date   getHireDate() { return hireDate; }
	public String getName()     { return name; }
}
