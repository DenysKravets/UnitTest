package org.example;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {

    private Main main = null;
    private final double a = 20.3;
    private final double start = 0.5;
    private final double end = 2.0;
    private final double step = 5e-3;
    private final double precision = 1e-3;
    private final int[] indexes = new int[] {0, 140, 300};

    @BeforeEach
    void generateMain() {
        main = new Main();
    }

    @Test
    void testLogOrSin() {

        double[] expectedValue = new double[] {0.0019, 0.9510, 1.0986};

        for (int i = 0; i < indexes.length; i++) {

            double x = start + step * indexes[i];

            double actualValueElement = main.logOrSin(x, a);
            double expectedValueElement = expectedValue[i];

            assertThat(actualValueElement).isCloseTo(expectedValueElement, Offset.offset(precision));
        }
    }

    @Test
    void testGetNumberOfElements() {

        int actualValue = main.getNumberOfElements(start, end, step);
        int expectedValue = 301;

        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testMapLogOrSin() {

        List<Main.Function> actualValue = main.mapLogOrSin(start, end, step, a);
        int expectedListSize = 301;

        assertThat(actualValue.size()).isEqualTo(301);
        if (actualValue.size() != expectedListSize)
            return;

        double[] expectedValue = new double[] {0.0019, 0.9510, 1.0986};

        for (int i = 0; i < indexes.length; i++) {
            int elementIndex = indexes[i];

            double actualValueElement = actualValue.get(elementIndex).y();
            double expectedValueElement = expectedValue[i];

            assertThat(actualValueElement).isCloseTo(expectedValueElement, Offset.offset(precision));
        }
    }

    @Test
    void testFindMax() {

        int actualMaxValue = main.findMax(start, end, step, a);
        int expectedMaxValue = 300;

        assertThat(actualMaxValue).isEqualTo(expectedMaxValue);
    }

    @Test
    void testFindMin() {

        int actualMaxValue = main.findMin(start, end, step, a);
        int expectedMaxValue = 0;

        assertThat(actualMaxValue).isEqualTo(expectedMaxValue);
    }

    @Test
    void testSum() {

        double actualSum = main.sum(start, end, step, a);
        double expectedSum = 239.4377;

        assertThat(actualSum).isCloseTo(expectedSum, Offset.offset(precision));
    }

    @Test
    void testAverage() {

        double actualAverage = main.average(start, end, step, a);
        double expectedAverage = 0.7954;

        assertThat(actualAverage).isCloseTo(expectedAverage, Offset.offset(precision));
    }
}