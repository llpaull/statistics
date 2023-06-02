public class LikelihoodUtils {

    public final static int BERNOULLI = 1;
    public final static int BINOMIAL = 2;
    public final static int GEOMETRIC = 3;
    public final static int POISSON = 4;

    static double likelihood(int distribution_type, double arg1, Double arg2, int... values){
        if (arg1 < 0) throw new IllegalArgumentException("arg1 cannot be less than 0");
        if (values.length == 0) throw new IllegalArgumentException("values cannot be empty");
        double result = 1;
        switch(distribution_type){
            case BERNOULLI -> {
                for (int i: values){
                    if (i != 0 && i != 1) throw new IllegalArgumentException();
                    if (i == 0) result *= (1-arg1);
                    else result *= arg1;
                }
            }
            case BINOMIAL -> {
                if (arg2 == null || arg2 < 0) throw new IllegalArgumentException("arg2 cannot be null");
                for (int i: values){

                }
            }
            case GEOMETRIC -> {
            }
            case POISSON -> {
            }
        }
        return result;
    }
}
