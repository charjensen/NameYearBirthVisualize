
public class Name {

	private String name;
	private String gender;
	private int year;
	private int occurences;
	
	public Name() {
		
		name = "John/Jane Doe";
		gender = "N/A";
		year = 0;
		occurences = 0;
		
	}
	
	public Name(String n, String g, int y, int o) {
		
		name = n;
		gender = g;
		year = y;
		occurences = o;
		
	}
	
	public void increaseYear() {
		year++;
	}
	
	public void increaseYear(int n) {
		year += n;
	}
	
	public void increaseOccurences() {
		occurences++;
	}
	
	public void increaseOccurences(int n) {
		occurences += n;
	}
	
	public String getName() {
		return name;
	}
	
	public String getGender() {
		return gender;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getOccurences() {
		return occurences;
	}
	
	public String toString() {
		return "Name: " + name + ", Gender: " + gender + ", Year: " + year + ", Occurences: " + occurences;
	}
	
	public int hashCode() {
		int hash = 0;
		hash = 31 * (name.hashCode() + gender.hashCode() + Integer.hashCode(year));
		return hash;
	}
	
}
