//public class Result "implements Comparable<Result>"{
   public class Result{
    private String flowName;
    private double euc;

    public Result(double e, String f){
        this.flowName = f; // nazwa kwiata
        this.euc = e; // odleglosc euklidesowa
    }

    public String getFlowName(){
        return flowName;
    }

    public double getEuc(){
        return euc;
    }
    public String toString(){
        return (flowName + " " + euc);
    }

//    @Override
//    public int compareTo(Result o) {
//        if(this.euc > o.euc){
//
//        }
//        return 0;
//    }
}
