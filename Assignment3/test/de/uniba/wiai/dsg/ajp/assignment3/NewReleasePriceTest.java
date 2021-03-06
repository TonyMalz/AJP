package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.Assert;
import org.junit.Test;

public class NewReleasePriceTest {

	@Test
	public final void testGetChargeZeroDays() {
		final NewReleasePrice newReleasePrice = new NewReleasePrice();
		final double charge = newReleasePrice.getCharge(0);
		Assert.assertEquals(3.0 * 1.42, charge, 0.0);

	}

	@Test
	public final void testGetChargeOneTwoDays() {
		final NewReleasePrice newReleasePrice = new NewReleasePrice();
		double charge = newReleasePrice.getCharge(1);
		Assert.assertEquals(3.0 * 1.42, charge, 0.0);

		charge = newReleasePrice.getCharge(2);
		Assert.assertEquals(6.0 * 1.42, charge, 0.0);
	}

	@Test
	public final void testGetChargeGreaterThree() {
		final NewReleasePrice newReleasePrice = new NewReleasePrice();
		double charge = newReleasePrice.getCharge(4);
		Assert.assertEquals(3.0 * 4 * 1.42, charge, 0.0);

		charge = newReleasePrice.getCharge(10);
		Assert.assertEquals(3.0 * 10 * 1.42, charge, 0.0);

		charge = newReleasePrice.getCharge(100);
		Assert.assertEquals(3.0 * 100 * 1.42, charge, 0.0);

		charge = newReleasePrice.getCharge(5);
		Assert.assertEquals(3.0 * 5 * 1.42, charge, 0.0);

	}

	@Test(expected = IllegalArgumentException.class)
	public final void testGetChargeDaysRentedLessZero() {
		final NewReleasePrice newReleasePrice = new NewReleasePrice();
		newReleasePrice.getCharge(-4);
	}

	@Test
	public final void testGetPriceCode() {
		Assert.assertEquals(1, new NewReleasePrice().getPriceCode());
	}

	@Test
	public final void testGetFrequentRenterPoints() {
		final NewReleasePrice newReleasePrice = new NewReleasePrice();

		Assert.assertEquals(1, newReleasePrice.getFrequentRenterPoints(0));
		Assert.assertEquals(1, newReleasePrice.getFrequentRenterPoints(1));

		Assert.assertEquals(2, newReleasePrice.getFrequentRenterPoints(2));
		Assert.assertEquals(2, newReleasePrice.getFrequentRenterPoints(3));
		Assert.assertEquals(2, newReleasePrice.getFrequentRenterPoints(4));
		Assert.assertEquals(2, newReleasePrice.getFrequentRenterPoints(5));

	}

	@Test(expected = IllegalArgumentException.class)
	public final void testGetFrequentRenterPointsDaysRentedLessZero() {
		final NewReleasePrice newReleasePrice = new NewReleasePrice();
		newReleasePrice.getFrequentRenterPoints(-4);
	}

	@Test
	public final void testSetCopyrightFee() {
		final NewReleasePrice newReleasePrice = new NewReleasePrice();
		newReleasePrice.setCopyrightFee(20.5);
		Assert.assertEquals(20.5, newReleasePrice.getCopyrightFee(), 0.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testSetCopyrightFeeLowerThanCurrentValue() {
		final NewReleasePrice newReleasePrice = new NewReleasePrice();
		newReleasePrice.setCopyrightFee(20.5);
		newReleasePrice.setCopyrightFee(10.5);
	}

	@Test
	public final void testGetCopyrightFeeForCalc() {
		Assert.assertEquals(1.42,
				new NewReleasePrice().getCopyrightFeeForCalc(), 0.0);
	}
}
