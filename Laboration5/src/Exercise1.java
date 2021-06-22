import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Exercise1 {

    public void sum(String filename) throws IOException {
        try (DataInputStream dis = new DataInputStream(
                new FileInputStream(filename))) {
            int sum = 0;
            int n = 2;
            for (int i = 0; i < n; i++){
                sum += dis.readInt();
            }
            System.out.println(sum);
        }

    }

    public static void main(String[] args) throws IOException {
        Exercise1 ex1 = new Exercise1();
        ex1.sum("C:\\Users\\Dragon\\IdeaProjects\\Optod\\Laboration5\\files\\heltal.dat");
    }
}
