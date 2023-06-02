import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class DiscreteLikelihoodUtilsTest {

    @ParameterizedTest
    @MethodSource("invalidTests")
    void testInvalidInputs(int distribution, double arg1, Double arg2, int... values){
        assertThrows(IllegalArgumentException.class, () -> DiscreteLikelihoodUtils.discreteLikelihood(distribution, arg1, arg2, values));
    }

    @ParameterizedTest
    @MethodSource("validTests")
    void testValidInputs(double expectation, int distribution, double arg1, Double arg2, int... values) {
        assertEquals(expectation, DiscreteLikelihoodUtils.discreteLikelihood(distribution, arg1, arg2, values));
    }

    public static Stream<Arguments> invalidTests() {
        return Stream.of(
                Arguments.of(-1, -1, null, new int[]{}),
                Arguments.of(DiscreteLikelihoodUtils.BERNOULLI, 0.5, null, null),
                Arguments.of(DiscreteLikelihoodUtils.BERNOULLI, -1, null, new int[]{1,1,1,1}),
                Arguments.of(DiscreteLikelihoodUtils.BERNOULLI, 0.5, null, new int[]{}),
                Arguments.of(DiscreteLikelihoodUtils.BINOMIAL, 0.5, null, new int[]{1}),
                Arguments.of(DiscreteLikelihoodUtils.BINOMIAL, 3, -1.0, new int[]{1}),
                Arguments.of(DiscreteLikelihoodUtils.BINOMIAL, 0.5, 0.8, new int[]{1})
        );
    }

    public static Stream<Arguments> validTests(){
        return Stream.of(
                Arguments.of(1*0.8*0.8*0.8*0.8*(1-0.8), DiscreteLikelihoodUtils.BERNOULLI, 0.8, null, new int[]{1, 1, 1, 1, 0}),
                Arguments.of(1*0.5*0.5*0.5*0.5*(1-0.5), DiscreteLikelihoodUtils.BERNOULLI, 0.5, null, new int[]{1, 1, 1, 1, 0}),
                Arguments.of(0, DiscreteLikelihoodUtils.BERNOULLI, 0, null, new int[]{1,1,1,1,1}),
                Arguments.of(1, DiscreteLikelihoodUtils.BERNOULLI, 1, null, new int[]{1,1,1,1,1}),
                Arguments.of(0, DiscreteLikelihoodUtils.BERNOULLI, 0.5, null, new int[]{2,1,1,1}),
                Arguments.of(1 * (3 * Math.pow(0.8, 1) * Math.pow((1-0.8), 2)) * (3 * Math.pow(0.8, 2) * Math.pow((1-0.8), 1)) * (1 * Math.pow(0.8, 3) * Math.pow((1-0.8), 0)) * (1 * Math.pow(0.8, 0) * Math.pow((1-0.8), 3)),
                        DiscreteLikelihoodUtils.BINOMIAL, 3, 0.8, new int[]{1, 2, 3, 0}),
                Arguments.of(1 * (5 * Math.pow(0.35, 1) * Math.pow((1-0.35), 4)) * (5 * Math.pow(0.35, 1) * Math.pow((1-0.35), 4)) * (5 * Math.pow(0.35, 1) * Math.pow((1-0.35), 4)),
                        DiscreteLikelihoodUtils.BINOMIAL, 5, 0.35, new int[]{1, 1, 1}),
                Arguments.of(0, DiscreteLikelihoodUtils.BINOMIAL, 3, 0.6, new int[]{5,4}),
                Arguments.of(0, DiscreteLikelihoodUtils.BINOMIAL, 3, 0.6, new int[]{-1,3}),
                Arguments.of(1*(0.6*1)*(0.6*Math.pow(0.4, 1))*(0.6*Math.pow(0.4, 2))*(0.6*Math.pow(0.4, 3)), DiscreteLikelihoodUtils.GEOMETRIC, 0.6, null, new int[]{1,2,3,4}),
                Arguments.of(1*(0.9*1)*(0.9*1)*(0.9*1), DiscreteLikelihoodUtils.GEOMETRIC, 0.9, null, new int[]{1,1,1}),
                Arguments.of(0, DiscreteLikelihoodUtils.GEOMETRIC, 0.5, null, new int[]{0, -1}),
                Arguments.of(1*((Math.pow(0.5, 1)/1)*Math.pow(Math.E, -0.5))*((Math.pow(0.5, 2)/2)*Math.pow(Math.E, -0.5))*((Math.pow(0.5, 3)/6)*Math.pow(Math.E, -0.5)), DiscreteLikelihoodUtils.POISSON, 0.5, null, new int[]{1,2,3}),
                Arguments.of(1*((Math.pow(0.89, 1)/1)*Math.pow(Math.E, -0.89))*((Math.pow(0.89, 7)/5040)*Math.pow(Math.E, -0.89)), DiscreteLikelihoodUtils.POISSON, 0.89, null, new int[]{1,7}),
                Arguments.of(0, DiscreteLikelihoodUtils.POISSON, 0.45, null, new int[]{-1})
        );
    }

}