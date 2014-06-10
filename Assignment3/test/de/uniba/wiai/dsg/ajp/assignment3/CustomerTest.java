package de.uniba.wiai.dsg.ajp.assignment3;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {

	@Test
	public final void testCustomerConstructor() {
		Customer customer = new Customer("Test Person");
		Assert.assertEquals("Test Person", customer.getName());
	}

	@Test
	public final void testSetName() {
		Customer customer = new Customer();
		customer.setName("Test Person");
		Assert.assertEquals("Test Person", customer.getName());
	}

	@Test(expected = NullPointerException.class)
	public final void testSetNameNull() {
		new Customer().setName(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testSetNameEmptyString() {
		new Customer().setName("");
	}

	@Test
	public final void testSetRentals() {
		List<Rental> rentals = new LinkedList<Rental>();
		Customer customer = new Customer();
		customer.setRentals(rentals);;
		Assert.assertEquals(rentals, customer.getRentals());
	}

	@Test(expected = NullPointerException.class)
	public final void testSetRentalsNull() {
		new Customer().setRentals(null);
	}

	private void setUpStatement(Customer customer) {
		Rental mockedRental1 = mock(Rental.class);
		Rental mockedRental2 = mock(Rental.class);
		Rental mockedRental3 = mock(Rental.class);

		Movie mockedMovie1 = mock(Movie.class);
		Movie mockedMovie2 = mock(Movie.class);
		Movie mockedMovie3 = mock(Movie.class);

		when(mockedRental1.getMovie()).thenReturn(mockedMovie1);
		when(mockedRental2.getMovie()).thenReturn(mockedMovie2);
		when(mockedRental3.getMovie()).thenReturn(mockedMovie3);

		when(mockedRental1.getFrequentRenterPoints()).thenReturn(1);
		when(mockedRental2.getFrequentRenterPoints()).thenReturn(2);
		when(mockedRental3.getFrequentRenterPoints()).thenReturn(3);

		when(mockedMovie1.getTitle()).thenReturn("Movie1");
		when(mockedMovie2.getTitle()).thenReturn("Movie2");
		when(mockedMovie3.getTitle()).thenReturn("Movie3");

		when(mockedRental1.getCharge()).thenReturn(1.5);
		when(mockedRental2.getCharge()).thenReturn(3.0);
		when(mockedRental3.getCharge()).thenReturn(4.5);

		List<Rental> mockedRentals = new LinkedList<Rental>();
		mockedRentals.add(mockedRental1);
		mockedRentals.add(mockedRental2);
		mockedRentals.add(mockedRental3);

		customer.setRentals(mockedRentals);
	}

	@Test
	public final void testStatement() {
		Customer customer = new Customer("Customer1");
		setUpStatement(customer);
		Assert.assertEquals(
				"Rental Record for Customer1\n\tMovie1\t1.5\n\tMovie2\t3.0\n\tMovie3\t4.5\nAmount owed is 9.0\nYou earned 6 frequent renter points",
				customer.statement());
	}

	@Test
	public final void testStatementCouponValueLessTotalCharge() {
		Customer customer = new Customer("Customer1");
		setUpStatement(customer);

		Coupon coupon = new Coupon("XYZ123", 5.0);

		Assert.assertEquals(
				"Rental Record for Customer1\n\tMovie1\t1.5\n\tMovie2\t3.0\n\tMovie3\t4.5\nAmount owed is 9.0\n#### Voucher redeemed ####\nCode: \tXYZ123\nValue:\t5.0\n####\nTotal amount owed is 4.0\nVoucher value left is 0.0\nYou earned 9 frequent renter points",
				customer.statement(coupon));
	}

	@Test
	public final void testStatementCouponValueGreaterTotalCharge() {
		Customer customer = new Customer("Customer1");
		setUpStatement(customer);

		Coupon coupon = new Coupon("XYZ123", 10.0);

		Assert.assertEquals(
				"Rental Record for Customer1\n\tMovie1\t1.5\n\tMovie2\t3.0\n\tMovie3\t4.5\nAmount owed is 9.0\n#### Voucher redeemed ####\nCode: \tXYZ123\nValue:\t10.0\n####\nTotal amount owed is 0.0\nVoucher value left is 1.0\nYou earned 9 frequent renter points",
				customer.statement(coupon));
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testStatementCouponLessThanZero() {
		Customer customer = new Customer("Customer1");

		Coupon mockedCoupon = mock(Coupon.class);
		when(mockedCoupon.getValue()).thenReturn(-10.0);
		customer.statement(mockedCoupon);

	}

	@Test
	public final void testHtmlStatement() {
		Customer customer = new Customer("Customer1");
		setUpStatement(customer);
		Assert.assertEquals(
				"<H1>Rentals for <EM>Customer1</EM></H1><P>\nMovie1: 1.5<BR>\nMovie2: 3.0<BR>\nMovie3: 4.5<BR>\n<P>You owe <EM>9.0</EM><P>\nOn this rental you earned <EM>6</EM> frequent renter points<P>",
				customer.htmlStatement());
	}

	@Test
	public final void testHtmlStatementCouponValueLessTotalCharge() {
		Customer customer = new Customer("Customer1");
		setUpStatement(customer);

		Coupon coupon = new Coupon("XYZ123", 5.0);

		Assert.assertEquals(
				"<H1>Rentals for <EM>Customer1</EM></H1><P>\nMovie1: 1.5<BR>\nMovie2: 3.0<BR>\nMovie3: 4.5<BR>\n<P>You owe 9.0<P>\n#### Voucher redeemed ####<BR>\nCode: \tXYZ123<BR>\nValue:\t5.0<BR>\n####<BR>\nTotal amount owed is <EM>4.0</EM><BR>\nVoucher value left is 0.0<BR>\nOn this rental you earned <EM>9</EM> frequent renter points<P>",
				customer.htmlStatement(coupon));
	}

	@Test
	public final void testHtmlStatementCouponGreaterTotalCharge() {
		Customer customer = new Customer("Customer1");
		setUpStatement(customer);

		Coupon coupon = new Coupon("XYZ123", 10.0);

		Assert.assertEquals(
				"<H1>Rentals for <EM>Customer1</EM></H1><P>\nMovie1: 1.5<BR>\nMovie2: 3.0<BR>\nMovie3: 4.5<BR>\n<P>You owe 9.0<P>\n#### Voucher redeemed ####<BR>\nCode: \tXYZ123<BR>\nValue:\t10.0<BR>\n####<BR>\nTotal amount owed is <EM>0.0</EM><BR>\nVoucher value left is 1.0<BR>\nOn this rental you earned <EM>9</EM> frequent renter points<P>",
				customer.htmlStatement(coupon));
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testHtmlStatementCouponLessThanZero() {
		Customer customer = new Customer("Customer1");

		Coupon mockedCoupon = mock(Coupon.class);
		when(mockedCoupon.getValue()).thenReturn(-10.0);
		customer.htmlStatement(mockedCoupon);
	}

	@Test
	public final void testGetTotalCharge() {
		Rental mockedRental1 = mock(Rental.class);
		Rental mockedRental2 = mock(Rental.class);
		Rental mockedRental3 = mock(Rental.class);

		when(mockedRental1.getCharge()).thenReturn(1.5);
		when(mockedRental2.getCharge()).thenReturn(3.0);
		when(mockedRental3.getCharge()).thenReturn(4.5);

		List<Rental> mockedRentals = new LinkedList<Rental>();
		mockedRentals.add(mockedRental1);
		mockedRentals.add(mockedRental2);
		mockedRentals.add(mockedRental3);

		Customer customer = new Customer();
		customer.setRentals(mockedRentals);

		Assert.assertEquals(9.0, customer.getTotalCharge(), 0.0);
	}

	@Test
	public final void testGetTotalFrequentRenterPoints() {
		Rental mockedRental1 = mock(Rental.class);
		Rental mockedRental2 = mock(Rental.class);
		Rental mockedRental3 = mock(Rental.class);

		when(mockedRental1.getFrequentRenterPoints()).thenReturn(1);
		when(mockedRental2.getFrequentRenterPoints()).thenReturn(2);
		when(mockedRental3.getFrequentRenterPoints()).thenReturn(3);

		List<Rental> mockedRentals = new LinkedList<Rental>();
		mockedRentals.add(mockedRental1);
		mockedRentals.add(mockedRental2);
		mockedRentals.add(mockedRental3);

		Customer customer = new Customer();
		customer.setRentals(mockedRentals);

		Assert.assertEquals(6, customer.getTotalFrequentRenterPoints(), 0);
	}
}
