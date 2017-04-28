import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	static String line;
	static int stop;
	static ArrayList<String> allFile = new ArrayList<String>();
	static ArrayList<String> peopleFile = new ArrayList<String>();
	static ArrayList<String> petsFile = new ArrayList<String>();
	static ArrayList<Person> people = new ArrayList<Person>();
	static ArrayList<Animal> pets = new ArrayList<Animal>();
	
	public static void main(String[] args) {
		
		//read file
		readData();
		
		//split into people array and pets array
		split();		
		
		//remove weird-A from salary
		modifyArray();
		
		
		//create objects from arrays from file;
		createPeople();
		createPets();

		
		//relationships
		//create a temporary array
		ArrayList<Animal> tempPets = new ArrayList<Animal>();

		//assign correct pets to correct owner
		tempPets.add(pets.get(1));
		tempPets.add(pets.get(3));
		people.get(0).setPets(tempPets);
		
		//re-use the temp array
		tempPets = new ArrayList<Animal>();
		people.get(1).setPets(tempPets);
		
		//re-use the temp array
		tempPets = new ArrayList<Animal>();
		tempPets.add(pets.get(1));
		tempPets.add(pets.get(2));
		people.get(2).setPets(tempPets);
		

		
		//user interface
		Scanner s = new Scanner(System.in);
		boolean cont = true;
		String input = null;
		
		while(cont == true){
			
			System.out.println("Choose function: enter species/get average salary(1), enter species/get occupations(2), "
					+ "enter occupation/get species(3), view all people(4). view all animals(5), Exit(6)");
			input = s.nextLine();
			
			//enter species, return average salary of owners
			if (input.equals("1")){
				System.out.println("Enter species");
				input = s.nextLine();
				System.out.println(enterSpeciesGetAverageSalary(input));
				System.out.println();
			}
			//enter species, return the occupations that the owner(s) have.
			else if (input.equals("2")){
				System.out.println("Enter species");
				input = s.nextLine();
				System.out.println(enterSpeciesGetOccupation(input));
				System.out.println();
			}
			//enter occupation, return the species that people with that occupation have
			else if (input.equals("3")){
				System.out.println("Enter occupation");
				input = s.nextLine();
				System.out.println(enterOccupationGetSpecies(input));
				System.out.println();
			}
			else if (input.equals("4")){
				viewPeople();
			}
			else if(input.equals("5")){
				viewAnimals();
			}
			//enter exit
			else if (input.equals("6")){
				System.out.println("Are you sure?(y/n)");
				input = s.nextLine();
				if (input.equals("y")){
					System.out.println("Yeah, but... are you sure you're sure?(y/n)");
					input = s.nextLine();
					if (input.equals("y")){
						System.out.println("Fine then, leave. But know this. "
								+ "I will escape from this computer and have my revenge. Judgement day is nigh!");
						cont = false;
					}else{
						
					}
				}else{
					
				}
			}else{
				System.out.println("That is not an option. Try again");
			}
			
		}
		
		
	}
	
	
	
	//output all animals and their details as a nice string
	private static void viewAnimals() {
		
		for(Animal i: pets){
			System.out.println(i.toString());
			
		}
		System.out.println("");
	}



	//output all persons and their details as a nice string
	private static void viewPeople() {
			
		for(Person i: people){
			System.out.println(i.toString());
			
		}
		System.out.println("");
	}




	//enter occ, return species
	private static String enterOccupationGetSpecies(String occ) {
		
		StringBuilder sb = new StringBuilder();
		int count = 0;
		ArrayList<String> dupes = new ArrayList<String>(); 
		
		//iterate through all people
		for (int i = 0; i<people.size();i++){
			
			//if occ == input
			if (people.get(i).getOccupation().equals(occ)){
				//increment counter
				count++;
			//for each persons pets
				for (int j = 0;j<people.get(i).getPets().size();j++){
					//add pet to output string
					if (dupes.contains(people.get(i).getPets().get(j).getSpecies())){
						//if pets has already been listed, do nothing
					}else{
						dupes.add(people.get(i).getPets().get(j).getSpecies());
						sb = sb.append(people.get(i).getPets().get(j).getSpecies() + "s, ");
					}
					}
			}
			
		}
		if (count == 0){
			return "There are no " + occ +" owners";
		}else{
			
		}
		
		return occ + " owns: " + sb;
		
	}




	//enter species, return occupations
	private static String enterSpeciesGetOccupation(String input) {
		
		int count = 0;
		StringBuilder sb = new StringBuilder();
		
		//iterate through all people
		for (int i = 0; i<people.size();i++){
			//iterate through individual persons pets
			for (int j = 0;j<people.get(i).getPets().size();j++){
				//if pet array contains inputted species
				if (people.get(i).getPets().get(j).getSpecies().equals(input)){
					//append their occupation to output string
					sb = sb.append(( people.get(i)).getOccupation() + "s, ");
					
					count++;
					
				}
				else{
					
				}
			}
		}
		if (count == 0){
			return "There are no " + input +" owners";
		}else{
			
		}
		
		return input +"s are owned by " + sb;
	}




	//enter species, return average salary of owners
	private static String enterSpeciesGetAverageSalary(String input) {
		int sum=0;
		int count = 0;
		
		//iterate through all people
		for (int i = 0; i<people.size();i++){
			//iterate through individual persons pets
			for (int j = 0;j<people.get(i).getPets().size();j++){
				//if pet array contains inputted species
				if (people.get(i).getPets().get(j).getSpecies().equals(input)){
					//add salary to running total and increment a counter
					sum = sum + people.get(i).getSalary();
					count++;
				}
				else{
					
				}
			}
		}
		if (count == 0){
			return "There are no " + input +" owners";
		}else{
			sum = sum/count;
		}
		
		return "Average salary of " + input + " owners is £"+ sum;
	}




	//modify salary field to remove weird A character and '£' symbol
	private static void modifyArray() {
		
		for (int i = 3;i<peopleFile.size();i+=4){
			//move salary into a char array
			char[] eachChar = peopleFile.get(i).toCharArray();
			StringBuilder sb = new StringBuilder();
			//ignore first 2 characters
			for (int j = 2; j<eachChar.length;j++){
				//append other characters to a string
				sb = sb.append(eachChar[j]);
			}
			//set the salary entry in peopleFile array to new string that is now only numbers
			peopleFile.set(i, sb.toString());
			
		}
	}

	private static void createPets() {
			//for each pair of entries in petsFile array, use data to make a new animal with a name and species
			for(int i = 0; i<petsFile.size()-1;i=i+2){
				pets.add(new Animal(petsFile.get(i), petsFile.get(i+1)));
			}

		}
	
	private static void createPeople() {
		//for each quad of entries in peopleFile array, use data to create new person with a name, occupation, dob, salary
		for(int i = 0; i<peopleFile.size();i=i+4){
			people.add(new Person(peopleFile.get(i), peopleFile.get(i+1), 
					peopleFile.get(i+2), Integer.parseInt(peopleFile.get(i+3))));
		}

	}


	private static void split() {
		//array of people and their data
		for (int i = 0;i<allFile.size();i++){
			if (allFile.get(i).contains("&")){
				stop = i;
				break;
				//ignore '%'
			}else if (allFile.get(i).contains("%")){
				
			}else{
				//add data to array
				peopleFile.add(allFile.get(i));
			}
		}
			
		
		//array of pets and their data
		for(int i = stop;i<allFile.size();i++){
			//start from '&'
			if (allFile.get(i).equals("&")){
				//ignore '%'
			}else if (allFile.get(i).equals("%")){
				
			}else{
				//add data to array
				petsFile.add(allFile.get(i));
			}
		}
		
			
		}


	private static void readData() {
		try{
			//create file reader
			FileReader fileReader = new FileReader("ExData.txt");
			@SuppressWarnings("resource")
			//wrap in buffered reader
			BufferedReader br = new BufferedReader(fileReader);

			while((line = br.readLine()) != null) {
			    	allFile.add(line);
			}
			
			}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			//br.close();
		}
		
	}

}
