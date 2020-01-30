package interpolation;

import org.junit.Test;

public class interpolationTest {

    Double[] x = {1.0, 2.0, 3.0, 4.0};
    Double[] y = {1.0, 8.0, 27.0, 64.0};

    @Test
    public void generalForm() {

        Interpolation interpolation = new Interpolation(x, y);
        interpolation.generalForm();
    }

    @Test
    public void lagrangeForm() {
        Interpolation interpolation = new Interpolation(x, y);
        interpolation.lagrangeForm();
    }

    @Test
    public void newtonForm() {
        Interpolation interpolation = new Interpolation(x, y);
        interpolation.newtonForm();
    }

    @Test
    public void splines() {
        Interpolation interpolation = new Interpolation(x, y);
        interpolation.splines();
    }
}
