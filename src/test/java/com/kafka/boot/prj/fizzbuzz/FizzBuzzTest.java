package com.kafka.boot.prj.fizzbuzz;

import org.junit.Assert;
import org.junit.Test;

public class FizzBuzzTest {

    /*
    Write a program that prints the numbers from 1 to 100.
    But for multiples of three print “Fizz” instead of the number and for the multiples of five print “Buzz”.
    For numbers which are multiples of both three and five print “FizzBuzz”."

    1 == 1
    2 == 2
    3 == Fizz
    ...
    ..
    5 == Buzz
    6 == Fizz
    ..
    10 == Buzz
    ..
    15 = FizzBuzz

    * */

    @Test
    public void fizzBuzzConverterNotMultiple_3_5_BothTest(){
        FizzBuzzConverter fizzBuzz = new FizzBuzzConverter();

        Assert.assertEquals("1", fizzBuzz.printNumberOrFizzBuzz(1));

        Assert.assertEquals("2", fizzBuzz.printNumberOrFizzBuzz(2));
    }

    @Test
    public void fizzBuzzConverterMultipleOfThree(){
        FizzBuzzConverter fizzBuzz = new FizzBuzzConverter();

        Assert.assertEquals("Fizz", fizzBuzz.printNumberOrFizzBuzz(3));

    }

    @Test
    public void fizzBuzzConverterMultipleOfFive(){
        FizzBuzzConverter fizzBuzz = new FizzBuzzConverter();

        Assert.assertEquals("Buzz", fizzBuzz.printNumberOrFizzBuzz(5));

    }

    @Test
    public void fizzBuzzConverterMultipleOfThreeAndFive(){
        FizzBuzzConverter fizzBuzz = new FizzBuzzConverter();

        Assert.assertEquals("FizzBuzz", fizzBuzz.printNumberOrFizzBuzz(15));

    }


}
