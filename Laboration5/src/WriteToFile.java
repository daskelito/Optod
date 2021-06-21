import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class WriteToFile {
	private ArrayList<Person> persons = new ArrayList<Person>();
    private long[] primes = {2,3,5,7,11,13,17,19,13,19,31};
    private LinkedList<String> invited = new LinkedList<String>();
    
    public WriteToFile() {
    	persons.add(new Person("Anita", 39, 'K'));
    	persons.add(new Person("Gabriella", 12, 'K'));
    	persons.add(new Person("Bo", 55, 'M'));
    	persons.add(new Person("Bertil", 82, 'M'));
    	persons.add(new Person("Greta", 84, 'K'));
    	persons.add(new Person("Gun", 3, 'K'));
    	
    	invited.add("Erik");
    	invited.add("Elsa");
    	invited.add("Gustav");
    	invited.add("Inga");
    	invited.add("Anna-Lisa");
    	invited.add("Runer");
    }
    
    public int menu() {
        int choice;
        String menu = "\nMENY\n" +
                "1. Skriv personer\n" +
                "2. Läs personer\n" +
                "3. Skriv bjudna\n" +
                "4. Läs bjudna\n" +
                "5. Skriv primtal\n" +
                "6. Läs primtal\n" +
                "7. Avsluta\n\n" +
                "Ange val";
        
        do {
            choice = Integer.parseInt(JOptionPane.showInputDialog(menu));
        }while(choice<1 || choice>7);
        return choice;
    }
    
    public void action() {
        int choice = menu();
        while(choice!=7) {
            try {
                switch (choice) {
                    case 1:
                        writePersons("files/persons.dat");
                        break;
                    case 2:
                        readPersons("files/persons.dat");
                        printPersons();
                        break;
                    case 3:
                        writeInvited("files/invited.txt");
                        break;
                    case 4:
                        readInvited("files/invited.txt");
                        printInvited();
                        break;
                    case 5:
                        writePrimes("files/primes.dat");
                        break;
                    case 6:
                        readPrimes("files/primes.dat");
                        printPrimes();
                        break;
                }
            } catch(IOException e) {
                System.out.println(e);
            }
            choice = menu();
        }
    }
    

    public void writePrimes(String filename) throws IOException {
    }
    
    public void readPrimes(String filename) throws IOException {
    	primes = null;
    	try ( DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filename))) ){
    		int n = dis.readInt();
    		primes = new long[n];
    		for (int i = 0; i < n; i++) {
    			primes[i] = dis.readLong();
    		}
    	}
    }
    
    public void writeInvited(String filename) throws IOException {
    }
    
    public void readInvited(String filename) throws IOException {
    	invited.clear();
    	try( BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8")) ) {
    		String name = br.readLine();
    		while(name!=null) {
    			invited.add(name);
    			name = br.readLine();
    		}
    	}
    }
    
    public void writePersons(String filename) throws IOException {
    }
    
    public void readPersons(String filename) throws IOException {
    	persons.clear();
    	try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename))) ) {
    		int n = ois.readInt();
    		for (int i = 0; i < n; i++) {
    			try {
    				persons.add((Person) ois.readObject());
    			} catch(ClassNotFoundException e) {}
    		}
    	}
    }
    
    // Uppgift 5 
    public void writePrimes2(String filename) throws IOException {
    }
    
    public void readPrimes2(String filename) throws IOException {
    }
    
    public void writeInvited2(String filename) throws IOException {
    }
    
    public void readInvited2(String filename) throws IOException {
    }
    
    public void writePersons2(String filename) throws IOException {
    }
    
    public void readPersons2(String filename) throws IOException {
    }
    
    private void printPrimes() {
        for(int i=0; primes!=null && i<primes.length; i++)
            System.out.print(primes[i]+" ");
        System.out.println();
    }
   
    private void printInvited() {
        for(String s : invited) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
    
    private void printPersons() {
        for(int i=0; i<persons.size(); i++) {
            System.out.println(persons.get(i));
        }
    }
    
    public static void main(String[] args) {
        WriteToFile sf = new WriteToFile();
        sf.action();
    }
}
