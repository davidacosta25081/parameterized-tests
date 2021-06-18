package com.qa.examples.parameterized_tests.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;

import com.qa.examples.parameterized_tests.hooks.Hooks;
import com.qa.examples.parameterized_tests.pages.CalculatorPage;

import io.cucumber.java.After;

public class Calculator {

	WebDriver driver;
    CalculatorPage calculator;
	
	public Calculator(Hooks hooks) {
		driver = hooks.getWebDriver();
	}

	@After
    public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}


    @ParameterizedTest
    @CsvSource({"2.5,2.5,5.0,+" , "3.0,3.0,9.0,*" })

    public void testOperation(double num1, double num2, double expectedAnswer, String operation) {
      calculator = new CalculatorPage(driver);
      driver.get(calculator.URL);
      calculator.calculateResult(num1,num2, operation);
      calculator.selectOperation("=");
      assertEquals(expectedAnswer, calculator.getResult());
    }






}
