import java.util.ArrayList;

public class Iris {
    private double sepalLength;
    private double sepalWidth;
    private double petalLength;
    private double petalWidth;
    private String name;
    private double[] values = new double[4];

    public Iris(double sepalLength, double sepalWidth, double petalLength,
                double petalWidth, String name){
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
        this.name = name;
        values[0] = sepalLength;
        values[1] = sepalWidth;
        values[2] = petalLength;
        values[3] = petalWidth;
    }
    public String toString(){
        return sepalLength + " " + sepalWidth + " " + petalLength + " " + petalWidth + " " + name;
    }
    public double[] getValues(){
        return values;
    }

    public double getSepalLength() {
        return sepalLength;
    }

    public double getSepalWidth() {
        return sepalWidth;
    }

    public double getPetalLength() {
        return petalLength;
    }

    public double getPetalWidth() {
        return petalWidth;
    }

    public String getName() {
        return name;
    }
}
