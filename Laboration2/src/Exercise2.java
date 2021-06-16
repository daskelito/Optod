import java.util.ArrayList;
import java.util.Collections;

public class Exercise2 {
    private ArrayList<Person> list = new ArrayList<Person>();

    public Exercise2(String filename) {
        list = Exercise1.readPersons(filename);
    }

    public String toString() {
        return "Lagrade Person-objekt: " + list.toString();
    }

    public void printPersons() {
        for (Person p : list) {
            System.out.println("Personnummer: " + p.getId() + ", " + "Namn: " + p.getFirstName() + " " + p.getLastName());
        }
    }

    public int position(String id) {
        Person p;
        for (int i = 0; i < list.size(); i++) {
            p = list.get(i);
            if (p.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void printName(String id) {
        int index = position(id);
        if (index >= 0) {
            Person p = list.get(index);
            System.out.println(p.getFirstName() + " " + p.getLastName());
        } else {
            System.out.println(id + " okänd");
        }
    }

    public boolean existsFirstName(String firstName) {
        for (Person p : list) {
            if (p.getFirstName().equals(firstName)) {
                return true;
            }
        }
        return false;
    }

    public boolean changeLastName(String id, String lastName) {
        for (Person p : list) {
            if (p.getId().equals(id)) {
                p.setLastName((lastName));
                return true;
            }
        }
        return false;
    }

    // Uppgift 3a
    public int position2(String id) {
        Person p = new Person(id, "", "");
        return list.indexOf(p);
    }


//     Uppgift 3b
    public void sort() {
    	Collections.sort(list);
    }
//     Uppgift 3b
    public int position3(String id) {
    	Person p = new Person(id,"","");
    	return Collections.binarySearch(list, p);
    }

    public static void main(String[] args) {
        Exercise2 ex2 = new Exercise2("C:\\Users\\Dragon\\IdeaProjects\\Optod\\Laboration2\\personer.txt");
        System.out.println(ex2.toString());
        ex2.printPersons();
        System.out.println(ex2.position("840102-4567"));
        System.out.println(ex2.position("111111-2222"));
        ex2.printName("840102-4567");
        ex2.printName("111111-2222");
        System.out.println(ex2.existsFirstName("Edit"));
        System.out.println(ex2.existsFirstName("Anna"));
        ex2.changeLastName("660503-8901", "Trädrot");
        System.out.println(ex2.toString());
        System.out.println(ex2.position2("840102-4567")); // Uppgift 3a
        ex2.sort(); // Uppgift 3b
        System.out.println( ex2.toString() );
        System.out.println( ex2.position3("840102-4567") ); // Uppgift 3b
    }
}
