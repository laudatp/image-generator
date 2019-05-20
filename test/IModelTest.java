
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


/**
 * Tests this imodel class.
 */
public class IModelTest {

    private IModel imodel;

    @Before
    public final void setup() {
        imodel = new Model();
    }
    
    
    @Test
    public final void  testGetBlurKernel() throws IOException {
        double[][] kernel = ((Model) imodel).getBlurKernel();
        assertEquals((double) 1 / (double) 16, kernel[0][0], .0001);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((i == 0 || i == 2) && (j == 0 || j == 2)) {
                    assertEquals((double) 1 / (double) 16, kernel[i][j], .0001);
                } else if (i == 1 && j == 1) {
                    assertEquals((double) 1 / (double) 4, kernel[i][j], .0001);
                } else {
                    assertEquals((double) 1 / (double) 8, kernel[i][j], .0001);
                }
            }
        }
    }

    @Test
    public final void testFilter() throws IOException {
        imodel.load("C:\\Users\\Peter\\mygitworkspace\\git-repos\\mvcPractice\\van.jpg");
        double[][] kernel = ((Model) imodel).getBlurKernel();

        assertEquals(2448, ((Model) imodel).getRgb().length);
        assertEquals(3264, ((Model) imodel).getRgb()[0].length);
        assertEquals(3, ((Model) imodel).getBlurKernel().length);
        assertEquals(3, ((Model) imodel).getBlurKernel()[0].length);
        assertEquals(1, ((Model) imodel).getBlurKernel().length / 2);
    }
}