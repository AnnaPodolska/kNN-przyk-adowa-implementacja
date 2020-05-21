import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
    public FileLoader(){}
    // ladujemy dane do listy przetrzymującej obiekty Iris
    public List<Iris> getFile(Path path) {
        List<String> fromFile = new ArrayList();
        try {
            fromFile = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Nie mozna znalezc pliku");
        }
        List<Iris> data = new ArrayList<>();
        //System.out.println(fromFile);
        for (String l : fromFile) {
            if (!l.isEmpty()) {

                String[] parts = l.split(";");
                data.add(new Iris(Double.valueOf(parts[0]),
                        Double.valueOf(parts[1]), Double.valueOf(parts[2]),
                        Double.valueOf(parts[3]), parts[4]));
            }
        }
        System.out.println("Data loaded succesfully");
        return data;
    }
    }

