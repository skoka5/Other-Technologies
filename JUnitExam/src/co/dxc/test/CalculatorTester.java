package co.dxc.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.dxc.beans.Calculator;

public class CalculatorTester {
	
	 Calculator calc = null;
	 
	 @BeforeClass
	 public static void meth3() {
		 System.out.println("STARTED");
	 }
	 
	 @AfterClass
	 public static void meth4() {
		 System.out.println("COMPLETED");
	 }
	
	@Before
	public  void meth1() {
		calc = new Calculator();
	}
	
	@After
	public void meth2() {
		calc = null;
	}

	@Test
	public void testAdd() {
		
		assertEquals(9, calc.add(5, 4));
		assertNotEquals(11, calc.add(5, 4));
	}

	@Test
	public void testSubstract() {
		
		assertEquals(1, calc.substract(5, 4));
		assertNotEquals(11, calc.substract(5, 4));
	}

	@Test
	public void testMultiply() {
		
		assertEquals(20, calc.multiply(5, 4));
		assertNotEquals(11, calc.multiply(5, 4));
	}

	@Test
	public void testDivide() {
		
		assertEquals(0.5, calc.divide(5, 10),0.01);
		assertNotEquals(11, calc.divide(5, 4));
		
	}

}
