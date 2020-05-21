import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
        {
            Scanner scan = new Scanner(System.in);
            FileLoader fl = new FileLoader();
           List<Iris> trainData = fl.getFile(Paths.get("iris.csv"));
           List<Iris> testData = fl.getFile(Paths.get("irisTest.csv"));
           int k;
            System.out.println("Podaj parametr k: ");
            k = scan.nextInt();
            kNN tr = new kNN(trainData, testData, k) ;
            System.out.println("Skutecznosc rozpoznawania dla danych testowych wynosi " + tr.checkAccuracy() + "%");

            double[] vector = new double[4];
            do {
                System.out.println("Podaj dane do sprawdzenia...");
                System.out.println("sepal length: ");
                vector[0] = scan.nextDouble();
                System.out.println("width length: ");
                vector[1] = scan.nextDouble();
                System.out.println("petal length: ");
                vector[2] = scan.nextDouble();
                System.out.println("petal width: ");
                vector[3] = scan.nextDouble();
                System.out.println("Kwiat o podanych parametrach klasyfikuje sie do rodzaju " +
                        tr.checkVector(vector));
            }
            while(scan.nextDouble() != -1);

        }
    }

