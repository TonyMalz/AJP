package de.uniba.wiai.dsg.ajp.assignment3;

import org.junit.Assert;
import org.junit.Test;

public class SeriesPriceTest {

	private static final double COPYRIGHT_FEE = 1.84;

	@Test
	public final void testGetChargeZeroDays() {
		final SeriesPrice sut = new SeriesPrice();
		final double charge = sut.getCharge(0);
		Assert.assertEquals(3.5 * COPYRIGHT_FEE, charge, 0.01);
	}

	@Test
	public final void testGetChargeOneTwoDays() {
		final SeriesPrice sut = new SeriesPrice();
		double charge = sut.getCharge(1);
		Assert.assertEquals(3.5 * COPYRIGHT_FEE, charge, 0.01);

		charge = sut.getCharge(2);
		Assert.assertEquals((3.5 + 3.) * COPYRIGHT_FEE, charge, 0.01);
	}

	@Test
	public final void testGetChargeGreaterTwo() {
		final SeriesPrice sut = new SeriesPrice();
		double charge = sut.getCharge(3);
		Assert.assertEquals((3.5 + 3. + .5) * COPYRIGHT_FEE, charge, 0.01);

		charge = sut.getCharge(4);
		Assert.assertEquals((3.5 + 3. + .5 + .5) * COPYRIGHT_FEE, charge, 0.01);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testGetChargeDaysRentedLessZero() {
		final SeriesPrice sut = new SeriesPrice();
		sut.getCharge(-4);
	}

	@Test
	public final void testGetPriceCode() {
		Assert.assertEquals(3, new SeriesPrice().getPriceCode());
	}

	@Test
	public final void testGetFrequentRenterPoints() {
		final SeriesPrice sut = new SeriesPrice();

		Assert.assertEquals(3, sut.getFrequentRenterPoints(0));
		Assert.assertEquals(3, sut.getFrequentRenterPoints(1));

		Assert.assertEquals(3, sut.getFrequentRenterPoints(2));
		Assert.assertEquals(3, sut.getFrequentRenterPoints(3));
		Assert.assertEquals(3, sut.getFrequentRenterPoints(4));
		Assert.assertEquals(3, sut.getFrequentRenterPoints(5));
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testGetFrequentRenterPointsDaysRentedLessZero() {
		final SeriesPrice sut = new SeriesPrice();
		sut.getFrequentRenterPoints(-1);
	}

	@Test
	public final void testSetCopyrightFee() {
		final SeriesPrice sut = new SeriesPrice();
		sut.setCopyrightFee(.42);
		Assert.assertEquals(.42, sut.getCopyrightFee(), 0.01);
	}
	@Test(expected = IllegalArgumentException.class)
	public final void testSetCopyrightFeeLowerThanCurrentValue() {
		final SeriesPrice sut = new SeriesPrice();
		sut.setCopyrightFee(.41);
	}

	@Test
	public final void testGetCopyrightFeeForCalc() {
		Assert.assertEquals(COPYRIGHT_FEE,
				new SeriesPrice().getCopyrightFeeForCalc(), 0.01);
	}
}
