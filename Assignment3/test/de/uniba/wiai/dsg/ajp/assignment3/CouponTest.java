package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CouponTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCouponConstructor() {
		Coupon coupon = new Coupon("XYZ123", 15.3);
		Assert.assertEquals("XYZ123", coupon.getCode());
		Assert.assertEquals(15.3, coupon.getValue(), 0.0);
	}

	@Test(expected = NullPointerException.class)
	public void testSetCodeNull() {
		Coupon coupon = new Coupon("XYZ123", 15.3);
		coupon.setCode(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetCodeEmptyString() {
		Coupon coupon = new Coupon("XYZ123", 15.3);
		coupon.setCode("");
	}

	@Test
	public void testSetValueZero() {
		Coupon coupon = new Coupon("XYZ123", 15.3);
		coupon.setValue(0.0);
		Assert.assertEquals(0.0, coupon.getValue(), 0.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetValueLessThanZero() {
		Coupon coupon = new Coupon("XYZ123", 15.3);
		coupon.setValue(-4.0);
	}

}
