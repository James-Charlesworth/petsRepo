import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Person {
	
	String name;
	String dob;
	String occupation;
	int salary;
	ArrayList<Animal> pets;
	String[] date = new String[3];
	Date current = new Date();
	Date dateOfBirth;
	int age;
	
	public Person(String name, String dob, String occupation, int salary) {
		super();
		this.name = name;
		this.dob = dob;
		this.occupation = occupation;
		this.salary = salary;
		convertDob();
		
		this.age = age(current, this.dateOfBirth);
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getOccupation() {
		return occupation;
	}


	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}


	public int getSalary() {
		return salary;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}


	public ArrayList<Animal> getPets() {
		return pets;
	}


	public void setPets(ArrayList<Animal> pets2) {
		this.pets = pets2;
	}
	
	@SuppressWarnings("deprecation")
	public void convertDob(){
		this.date = this.dob.split("/");
		this.dateOfBirth = new Date(Integer.parseInt(this.date[2])-1900, Integer.parseInt(this.date[1])-1, 
				Integer.parseInt(this.date[0]));
		
	}
	
	public int age(Date current, Date dob){
		Calendar a = getCalendar(current);
	    Calendar b = getCalendar(dob);
	    int diff = a.get(Calendar.YEAR) - b.get(Calendar.YEAR);
	    if (b.get(Calendar.DAY_OF_YEAR) > a.get(Calendar.DAY_OF_YEAR)){
	        diff--;
	    }
	    return diff;
	}
	
	public static Calendar getCalendar(Date date) {
	    Calendar cal = Calendar.getInstance(Locale.UK);
	    cal.setTime(date);
	    return cal;
	}
	
	
	@Override
	public String toString() {
		return "name: "+this.name + ", occupation: "+this.occupation + ", salary: £" +this.salary + ", approximately " + this.age + " years old" + " owns: " + this.pets.toString();
	}
	
	
	
}
