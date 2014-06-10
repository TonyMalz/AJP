package de.uniba.wiai.dsg.ajp.assignment3;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class RegularPriceTest {
	@Test
	public final void testGetChargeUpToThree() {
		final RegularPrice regularPrice = new RegularPrice();
		double charge = regularPrice.getCharge(0);
		Assert.assertEquals(2.0 * 1.42, charge, 0.0);

		charge = regularPrice.getCharge(1);
		Assert.assertEquals(2.0 * 1.42, charge, 0.0);

		charge = regularPrice.getCharge(2);
		Assert.assertEquals(2.0 * 1.42, charge, 0.0);
	}

	@Test
	public final void testGetChargeGreaterThree() {
		final RegularPrice regularPrice = new RegularPrice();
		double charge = regularPrice.getCharge(4);
		Assert.assertEquals((1.5 * 2 + 2.0) * 1.42, charge, 0.0);

		charge = regularPrice.getCharge(10);
		Assert.assertEquals((1.5 * 8 + 2.0) * 1.42, charge, 0.0);

		charge = regularPrice.getCharge(100);
		Assert.assertEquals((1.5 * 98 + 2.0) * 1.42, charge, 0.0);

		charge = regularPrice.getCharge(5);
		Assert.assertEquals((1.5 * 3 + 2.0) * 1.42, charge, 0.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testGetChargeDaysRentedLessZero() {
		final RegularPrice regularPrice = new RegularPrice();
		regularPrice.getCharge(-4);
	}

	@Test
	public final void testGetPriceCode() {
		Assert.assertEquals(0, new RegularPrice().getPriceCode());
	}

	@Test
	public final void testGetFrequentRenterPoints() {
		final Random r = new Random(123456789);
		final RegularPrice regularPrice = new RegularPrice();
		for (int i = 0; i < 10000; i++) {
			Assert.assertEquals(1,
					regularPrice.getFrequentRenterPoints(r.nextInt()));
		}
	}

	@Test
	public final void testSetCopyrightFee() {
		final RegularPrice releasePrice = new RegularPrice();
		releasePrice.setCopyrightFee(20.5);
		Assert.assertEquals(20.5, releasePrice.getCopyrightFee(), 0.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testSetCopyrightFeeLowerThanCurrentValue() {
		final RegularPrice releasePrice = new RegularPrice();
		releasePrice.setCopyrightFee(20.5);
		releasePrice.setCopyrightFee(10.5);
	}

	@Test
	public final void testGetCopyrightFeeForCalc() {
		Assert.assertEquals(1.42, new RegularPrice().getCopyrightFeeForCalc(),
				0.0);
	}
}
