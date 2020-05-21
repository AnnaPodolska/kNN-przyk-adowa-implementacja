import java.util.*;
import java.util.stream.Collectors;

public class kNN {
    private List<Iris> train; //dane treningowe
    private List<Iris> test; //dane testowe
    private int k; // dokladnosc k
    //private Set<String> names = new HashSet<>(); // set do przechowywania nazw gatunku kwiatów, jakie wystepuja w danych
    private List<Result> sortedRes = new ArrayList<>();


    public kNN(List<Iris> train, List<Iris> test, int k) {
        this.test = test;
        this.train = train;
        this.k = k;
//        for (Iris i : train) {
//            names.add(i.getName());
//        }

    }

    public String checkVector(double[] vector) {
        List<Result> results = new ArrayList<>(); // lista wyników obliczeń tu dałam obiekt Result (odleglosc euk, nazwa kwiata)
        Map<String, Integer> wystapienia = new HashMap<>(); // mapa wystąpień (nazwa gatunku, ilosc wystapien)

        String tmpname;
        int tmpocc = 0;

        for (Iris ir : train) {
            results.add(new Result(euclides(ir.getValues(), vector), ir.getName()));
        } // results[result(odleglosc, nazwa kwiata)]
        sortedRes = results.stream().sorted(Comparator.comparing(Result::getEuc)).
                collect(Collectors.toList()); // posortowane po odleglosci euklidesowej wyniki w kolejnosci rosnącej


        for (int i = 0; i < k; i++) { // dla pierwszych k-elementów zliczamy ile razy wystąpiły dane gatunki kwiata i wrzucamy do mapy wystąpień
            // ilosc wystąpień

            System.out.println("Odleglosci euklidesowe " + i + " - " + sortedRes.get(i));
            tmpname = sortedRes.get(i).getFlowName(); // pobieramy nazwe kwiata z i-tego elementy z posortowanej listy wynikow results
            tmpocc = wystapienia.getOrDefault(tmpname, 0);
            tmpocc++;
            wystapienia.put(tmpname, tmpocc); // zwiekszenie wystapien
        }

        wystapienia = sortMap(wystapienia); // sortuje mape po ilosci wystapien dopasowań od najwiekszej do najmniejszej
        System.out.println("Wystapienia okazow : " + wystapienia);
        String category = wystapienia.keySet().stream().findFirst().get(); //biore pierwszy klucz - nazwe kwiata ktory wystapil najczesciej

        return category;
    }

    public double checkAccuracy() {
        double wellClassified = 0;
        double all = test.size();
        double accuracy;
        for (Iris ir : test) {
            String result = checkVector(ir.getValues());
            //System.out.println("Z obliczen " + result + ", a z prawdy " + ir.getName());
            if (result.equals(ir.getName())) { // sprawdzam czy wyliczenia programu zgadzają się z rzeczywistą klasyfikacją kwiata
                wellClassified++;
            }
        }
        //System.out.println(wellClassified);
        accuracy = wellClassified * 100 / all;
        //System.out.println(accuracy);
        return accuracy;
    }

    public double euclides(double[] v1, double[] v2) {
        if (v1.length != v2.length) {
            System.out.println("Dwa wektory sa sobie nierowne, bledne dane");
            return -1;
        } else {
            double sum = 0;
            for (int i = 0; i < v1.length; i++) {
                sum += Math.pow((v1[i] - v2[i]), 2);
            }
            double exe = Math.sqrt(sum);
            return exe;
        }
    }

    public Map<String, Integer> sortMap(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() > o2.getValue())
                    return -1;
                else if (o1.getValue() < o2.getValue())
                    return 1;
                else
                    return 0;
            }
        });
        Map<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
