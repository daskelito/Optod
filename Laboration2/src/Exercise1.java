import java.util.*; // ArrayList
import java.io.*; // BufferedReader, FileReader, IOException

public class Exercise1 {
    public static ArrayList<Person> readPersons( String filename ) {
        ArrayList<Person> list = new ArrayList<Person>();
        try {
            BufferedReader br = new BufferedReader( new FileReader( filename ) );
            String[] parts;
            Person person;
            String str = br.readLine();
            while( str != null ) {
                parts = str.split( "," );
                person = new Person( parts[ 0 ], parts[ 1 ], parts[ 2 ] );
                list.add( person );
                str = br.readLine();
            }
            br.close();
        } catch( IOException e ) {
            System.out.println( "readPersons: " + e );
        }
        return list;
    }
    
    public static void main( String[] args ) {
        ArrayList<Person> list = Exercise1.readPersons( "files/personer.txt" );
        Person person1 = new Person( "761201-7654", "Henry", "Smith" );
        Person person2 = new Person( "011003-4444", "Alma", "Björk" );
        Person p;
        int a;
        
        System.out.println( list.toString() ); // A
        
        p = list.get( 2 ); // B
        System.out.println( p.toString() ); 
        
        list.add( person1 ); // C
        System.out.println( list.toString() );
        
        list.add( 4, person2 ); // D
        System.out.println( list.toString() );
        
        a = list.size(); // E
        System.out.println( a );
        
        a = list.indexOf( person2 ); // F
        System.out.println( a );
        
        if( list.contains( person1 ) ) { // G
            System.out.println( person1.toString() + " FINNS" );
        } else {
            System.out.println( person1.toString() + " FINNS EJ" );
        }
        
        list.remove( 1 ); // H
        System.out.println( list.toString() );
        
        list.clear(); // I
        System.out.println( list.toString() );
    }
}
