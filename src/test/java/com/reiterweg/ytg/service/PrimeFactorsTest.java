package com.reiterweg.ytg.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PrimeFactorsTest {

	private PrimeFactorsServlet primeFactors;

	@Before
	public void setUp() {
		primeFactors = new PrimeFactorsServlet();
	}

	@Test
	public void testValueEven() {
		assertArrayEquals(new Integer[] { 2, 2, 2, 2 }, primeFactors.value("16").getDecomposition());
	}

	@Test
	public void testValueOdd() {
		assertArrayEquals(new Integer[] { 3, 3, 5, 7 }, primeFactors.value("315").getDecomposition());
	}

	@Test
	public void testValueNotNumber() {
		assertEquals("not a number", primeFactors.value("hello").getError());
	}

	@Test
	public void testValueTooBig() {
		assertEquals("too big number (>1e6)", primeFactors.value("1000001").getError());
	}

}
