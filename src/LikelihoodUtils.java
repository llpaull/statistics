public class LikelihoodUtils {

    public final static int BERNOULLI = 1;
    public final static int BINOMIAL = 2;
    public final static int GEOMETRIC = 3;
    public final static int POISSON = 4;

    /**
     * calculates the likelihood of discrete distributions
     * @param distribution_type the type of discrete distribution to use, can be either:
     * <ul>
     *     <li>BER(p)</li>
     *     <li>BIN(n,p)</li>
     *     <li>GEO(p)</li>
     *     <li>POIS(mu)</li>
     * </ul>
     * @param arg1 the first argument of the distribution:
     * <ul>
     *     <li>for BER(p) that chance that X = 1</li>
     *     <li>for BIN(n,p) the amount of trials, must be a whole number</li>
     *     <li>for GEO(p) the chance for success</li>
     *     <li>for POIS(mu) the average</li>
     * </ul>
     * @param arg2 only needed in case of BIN(n,p) where it is the chance of success
     * @param values the values gathered from the experiment or simulation
     * @return the calculated likelihood
     * @throws IllegalArgumentException if invalid input was given:
     * <ul>
     *     <li>incorrect distribution type</li>
     *     <li>arg1 < 0</li>
     *     <li>arg1 % 1 != 0 (only in case of BIN(n,p) distribution)</li>
     *     <li>arg2 < 0 or arg2 == null (only in case of BIN(n,p) distribution)</li>
     *     <li>no values or values out of range.</li>
     * </ul>
     */
    static double likelihood(int distribution_type, double arg1, Double arg2, int... values) throws IllegalArgumentException{
        if (distribution_type != BERNOULLI && distribution_type != BINOMIAL
                && distribution_type != GEOMETRIC && distribution_type != POISSON)
            throw new IllegalArgumentException("Invalid distribution type");

        if (arg1 < 0) throw new IllegalArgumentException("arg1 must not be <0");
        if (values.length == 0) throw new IllegalArgumentException("values must not be empty");

        double result = 0;
        switch(distribution_type){
            case BERNOULLI -> result = bernoulli(arg1, values);
            case BINOMIAL -> {
                if (arg2 == null || arg2 < 0) throw new IllegalArgumentException("arg2 must not be null or <0");
                if (arg1 % 1 != 0) throw new IllegalArgumentException("arg1 must be a whole number");
                result = binomial(arg1, arg2, values);
            }
            case GEOMETRIC -> result = geometric();
            case POISSON -> result = poisson();
            }
        return result;
    }

    private static double bernoulli(double probability, int... values) {
        double result = 1;
        for(int i: values){
            if (i != 0 && i != 1) return 0;
            if (i == 1) result*= probability;
            else result*= (1-probability);
        }
        return result;
    }

    private static double binomial(double trials, double probability, int... values){
        double result = 1;
        for(int i: values){
            // P(X = i) is 0 if i>trials and the rest doesn't matter
            if (i > trials || i<0) return 0;
            result *= (choose((int) trials, i) * Math.pow(probability, i) * Math.pow((1-probability), (trials-i)));
        }
        return result;
    }

    private static double geometric() {
        return -1;
    }

    private static double poisson() {
        return -1;
    }

    private static int factorial(int num){
        if (num == 0 || num == 1) return 1;
        return num * factorial(num-1);
    }

    private static int choose(int n, int k){
        int factorialN = factorial(n);
        int factorialK = factorial(k);
        int factorialNMinusK = factorial(n-k);
        return factorialN/(factorialK*factorialNMinusK);
    }

}
