package com.kafka.boot.prj.fizzbuzz;

import org.junit.Test;

public class ActualFizzBuzzTest {

    @Test
    public void outputHundredFizzBuzz() {

        FizzBuzzConverter fizzBuzz = new FizzBuzzConverter();
        for (int i = 1; i <= 100; i++) {
            System.out.println(fizzBuzz.printNumberOrFizzBuzz(i));
        }
    }
}
