package com.zpi;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class MethodsTest {

    @Test
    public void findMeanShouldReturnMeanValueArrayTest1() {
        double[] arrayTest1 = {44, 50, 38, 96, 42, 47, 40, 39, 46, 50};

        assertThat(Methods.findMean(arrayTest1), is(49.2));
    }

    @Test
    public void findMedianShouldMedianValueForOddLengthArrayTest() {
        double[] arrayTest = {4, 1, 7, 6, 10};

        assertThat(Methods.findMedian(arrayTest), is(6.0));
    }

    @Test
    public void findMedianShouldReturnMedianValueForEvenLengthArrayTest() {
        double[] arrayTest = {4, 1, 7, 6, 7, 10};

        assertThat(Methods.findMedian(arrayTest), is(6.5));
    }

    @Test
    public void findModeShouldReturnModeValueForArrayTest1() {
        double[] arrayTest1 = {4,2,4,3,2,2};

        assertThat(Methods.findMode(arrayTest1), is(2.0));
    }

    @Test
    public void findStandardDeviationShouldReturnStandardDeviationValueForArrayTest1() {
        double[] arrayTest1 = {6,2,3,1};

        assertThat(Methods.findStandardDeviation(arrayTest1), is(1.8708));
    }

    @Test
    public void findCoefficientOfVariationShouldReturnCoefficientOfVariationValueForArrayTest1() {
        double[] arrayTest1 = {6,2,3,1};

        assertThat(Methods.findMean(arrayTest1), is(3.0));
        assertThat(Methods.findStandardDeviation(arrayTest1), is(1.8708));
        assertThat(Methods.findCoefficientOfVariation(arrayTest1), is(62.36));
    }

    @Test
    public void findAmountOfGrowthSessionsShouldReturn4() {
        double[] values = {1,2,3,1,5,6,3,2,2,2};

        assertThat(Methods.findAmountOfGrowthSessions(values), is(4));
    }

    @Test
    public void findAmountOfGrowthSessionsShouldReturn3() {
        double[] values = {3,0,3,5,4,6,6};

        assertThat(Methods.findAmountOfGrowthSessions(values), is(3));
    }

    @Test
    public void findAmountOfDownwardSessionsShouldReturn3() {
        double[] values = {1,2,3,1,5,6,3,2,2,2};

        assertThat(Methods.findAmountOfDownwardSessions(values), is(3));
    }

    @Test
    public void findAmountOfDownwardSessionsShouldReturn0() {
        double[] values = {1,1,1,1,1,5,5};

        assertThat(Methods.findAmountOfDownwardSessions(values), is(0));
    }

    @Test
    public void findAmountOfDownwardSessionsShouldReturn2() {
        double[] values = {3,5,0,1,2,0,0,1};

        assertThat(Methods.findAmountOfDownwardSessions(values), is(2));
    }

    @Test
    public void findAmountOfUnchangedSessionsShouldReturn2() {
        double[] values = {5,4,6,6,9,4,3,3,1};

        assertThat(Methods.findAmountOfUnchangedSessions(values), is(2));
    }

    @Test
    public void findAmountOfUnchangedSessionsShouldReturnZero() {
        double[] values = {5,4,6,9,4,3,1};

        assertThat(Methods.findAmountOfUnchangedSessions(values), is(0));
    }

}
