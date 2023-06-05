import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class DiscreteLikelihoodUtilsTest {

    @ParameterizedTest
    @MethodSource("invalidTests")
    void testInvalidInputs(Distribution.Discrete distribution, double arg1, Double arg2, int... values){
        assertThrows(IllegalArgumentException.class, () -> DiscreteLikelihoodUtils.discreteLikelihood(distribution, arg1, arg2, values));
    }

    @ParameterizedTest
    @MethodSource("validTests")
    void testValidInputs(double expectation, Distribution.Discrete distribution, double arg1, Double arg2, int... values) {
        assertEquals(expectation, DiscreteLikelihoodUtils.discreteLikelihood(distribution, arg1, arg2, values));
    }

    public static Stream<Arguments> invalidTests() {
        return Stream.of(
                Arguments.of(
                        null,
                        -1,
                        null,
                        new int[]{}),

                Arguments.of(
                        Distribution.Discrete.BERNOULLI,
                        0.5,
                        null,
                        null),

                Arguments.of(
                        Distribution.Discrete.BERNOULLI,
                        -1,
                        null,
                        new int[]{1,1,1,1}),

                Arguments.of(
                        Distribution.Discrete.BERNOULLI,
                        0.5,
                        null,
                        new int[]{}),

                Arguments.of(
                        Distribution.Discrete.BINOMIAL,
                        0.5,
                        null,
                        new int[]{1}),

                Arguments.of(
                        Distribution.Discrete.BINOMIAL,
                        3,
                        -1.0,
                        new int[]{1}),

                Arguments.of(
                        Distribution.Discrete.BINOMIAL,
                        0.5,
                        0.8,
                        new int[]{1})
        );
    }

    public static Stream<Arguments> validTests(){
        return Stream.of(
                Arguments.of(
                        0.08192,
                        Distribution.Discrete.BERNOULLI,
                        0.8,
                        null,
                        new int[]{1, 1, 1, 1, 0}),

                Arguments.of(
                        0.03125,
                        Distribution.Discrete.BERNOULLI,
                        0.5,
                        null,
                        new int[]{1, 1, 1, 1, 0}),

                Arguments.of(
                        0,
                        Distribution.Discrete.BERNOULLI,
                        0,
                        null,
                        new int[]{1,1,1,1,1}),

                Arguments.of(
                        1,
                        Distribution.Discrete.BERNOULLI,
                        1,
                        null,
                        new int[]{1,1,1,1,1}),

                Arguments.of(
                        0,
                        Distribution.Discrete.BERNOULLI,
                        0.5,
                        null,
                        new int[]{2,1,1,1}),

                Arguments.of(
                        1.5099494399999989E-4,
                        Distribution.Discrete.BINOMIAL,
                        3,
                        0.8,
                        new int[]{1, 2, 3, 0}),

                Arguments.of(
                        0.030484173572582193,
                        Distribution.Discrete.BINOMIAL,
                        5,
                        0.35,
                        new int[]{1, 1, 1}),

                Arguments.of(
                        0,
                        Distribution.Discrete.BINOMIAL,
                        3,
                        0.6,
                        new int[]{5,4}),

                Arguments.of(
                        0,
                        Distribution.Discrete.BINOMIAL,
                        3,
                        0.6,
                        new int[]{-1,3}),

                Arguments.of(
                        5.308416000000002E-4,
                        Distribution.Discrete.GEOMETRIC,
                        0.6,
                        null,
                        new int[]{1,2,3,4}),

                Arguments.of(
                        0.7290000000000001,
                        Distribution.Discrete.GEOMETRIC,
                        0.9,
                        null,
                        new int[]{1,1,1}),

                Arguments.of(
                        0,
                        Distribution.Discrete.GEOMETRIC,
                        0.5,
                        null,
                        new int[]{0, -1}),

                Arguments.of(
                        2.90534062693268E-4,
                        Distribution.Discrete.POISSON,
                        0.5,
                        null,
                        new int[]{1,2,3}),

                Arguments.of(
                        1.3171806403807385E-5,
                        Distribution.Discrete.POISSON,
                        0.89,
                        null,
                        new int[]{1,7}),

                Arguments.of(
                        0,
                        Distribution.Discrete.POISSON,
                        0.45,
                        null,
                        new int[]{-1})
        );
    }

}