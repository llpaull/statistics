import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LikelihoodUtilsTest {

    @Test
    void testBernoulliLikelihood() {
        assertThrows(IllegalArgumentException.class, () -> LikelihoodUtils.likelihood(LikelihoodUtils.BERNOULLI, -1, null, 1,1,1,1));
        assertThrows(IllegalArgumentException.class, () -> LikelihoodUtils.likelihood(LikelihoodUtils.BERNOULLI, 0.5, null));
        double result1 = 1*0.8*0.8*0.8*0.8*(1-0.8);
        double result2 = 1*0.5*0.5*0.5*0.5*(1-0.5);
        assertEquals(result1, LikelihoodUtils.likelihood(LikelihoodUtils.BERNOULLI, 0.8, null, 1, 1, 1, 1, 0));
        assertEquals(result2, LikelihoodUtils.likelihood(LikelihoodUtils.BERNOULLI, 0.5, null, 1, 1, 1, 1, 0));
        assertEquals(0, LikelihoodUtils.likelihood(LikelihoodUtils.BERNOULLI, 0.5, null, 2,1,1,1));
    }

    @Test
    void testBinomialLikelihood(){
        assertThrows(IllegalArgumentException.class, () -> LikelihoodUtils.likelihood(LikelihoodUtils.BINOMIAL, 0.5, null, 1));
        assertThrows(IllegalArgumentException.class, () -> LikelihoodUtils.likelihood(LikelihoodUtils.BINOMIAL, 3, -1.0, 1));
        assertThrows(IllegalArgumentException.class, () -> LikelihoodUtils.likelihood(LikelihoodUtils.BINOMIAL, 0.5, 0.8, 1));
        double result1 = 1 * (3 * Math.pow(0.8, 1) * Math.pow((1-0.8), 2)) * (3 * Math.pow(0.8, 2) * Math.pow((1-0.8), 1)) * (1 * Math.pow(0.8, 3) * Math.pow((1-0.8), 0)) * (1 * Math.pow(0.8, 0) * Math.pow((1-0.8), 3));
        double result2 = 1 * (5 * Math.pow(0.35, 1) * Math.pow((1-0.35), 4)) * (5 * Math.pow(0.35, 1) * Math.pow((1-0.35), 4)) * (5 * Math.pow(0.35, 1) * Math.pow((1-0.35), 4));
        assertEquals(result1, LikelihoodUtils.likelihood(LikelihoodUtils.BINOMIAL, 3, 0.8, 1, 2, 3, 0));
        assertEquals(result2, LikelihoodUtils.likelihood(LikelihoodUtils.BINOMIAL, 5, 0.35, 1, 1, 1));
    }

    @Test
    void testGeometricLikelihood(){
    }

    @Test
    void testPoissonLikelihood(){
    }

}