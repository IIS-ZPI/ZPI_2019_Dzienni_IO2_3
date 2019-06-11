package com.zpi;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class MethodsTest {

    @Test
    public void findMeanShouldReturn49coma2ForArrayTest() {
        double[] arrayTest = {44, 50, 38, 96, 42, 47, 40, 39, 46, 50};

        assertThat(Methods.findMean(arrayTest), is(49.2));
    }

    @Test
    public void findMedianShouldReturn6ForOddLengthArrayTest() {
        double[] arrayTest = {4, 1, 7, 6, 10};

        assertThat(Methods.findMedian(arrayTest), is(6.0));
    }

    @Test
    public void findMedianShouldReturn6coma5ForEvenLengthArrayTest() {
        double[] arrayTest = {4, 1, 7, 6, 7, 10};

        assertThat(Methods.findMedian(arrayTest), is(6.5));
    }

    @Test
    public void findModeShouldReturn2ForArrayTest() {
        double[] arrayTest = {4,2,4,3,2,2};

        assertThat(Methods.findMode(arrayTest), is(2.0));
    }

    @Test
    public void findStandardDeviationShouldReturn1coma8708ForArrayTest() {
        double[] arrayTest = {6,2,3,1};

        assertThat(Methods.findStandardDeviation(arrayTest), is(1.8708));
    }

    @Test
    public void findCoefficientOfVariationShouldReturn1coma8708ForArrayTest() {
        double[] arrayTest = {6,2,3,1};

        assertThat(Methods.findMean(arrayTest), is(3.0));
        assertThat(Methods.findStandardDeviation(arrayTest), is(1.8708));
        assertThat(Methods.findCoefficientOfVariation(arrayTest), is(62.36));
    }

}
