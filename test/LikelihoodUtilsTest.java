import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LikelihoodUtilsTest {

    @Test
    void testBernoulliLikelihood() {
        assertThrows(IllegalArgumentException.class, () -> LikelihoodUtils.likelihood(LikelihoodUtils.BERNOULLI, -1, null, 1,1,1,1));
        assertThrows(IllegalArgumentException.class, () -> LikelihoodUtils.likelihood(LikelihoodUtils.BERNOULLI, 0.5, null, 2,1,1,1));
        assertThrows(IllegalArgumentException.class, () -> LikelihoodUtils.likelihood(LikelihoodUtils.BERNOULLI, 0.5, null));
        double result1 = 1*0.8*0.8*0.8*0.8*(1-0.8);
        double result2 = 1*0.5*0.5*0.5*0.5*(1-0.5);
        assertEquals(result1, LikelihoodUtils.likelihood(LikelihoodUtils.BERNOULLI, 0.8, null, 1, 1, 1, 1, 0));
        assertEquals(result2, LikelihoodUtils.likelihood(LikelihoodUtils.BERNOULLI, 0.5, null, 1, 1, 1, 1, 0));
    }

    @Test
    void testBinomialLikelihood(){
    }
}