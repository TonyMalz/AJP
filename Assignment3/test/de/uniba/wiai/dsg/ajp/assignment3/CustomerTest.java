package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerTest {
	// not implemented because trivial getter/setters:
	// getName()
	// setName()
	// getRentals()
	// setRentals()

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
	public final void testCustomer() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCustomerString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetName() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetName() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetRentals() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetRentals() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testStatement() {
		Customer cust = new Customer("Customer1");

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

		cust.setRentals(mockedRentals);

		assertEquals(
				"Rental Record for Customer1\n\tMovie1\t1.5\n\tMovie2\t3.0\n\tMovie3\t4.5\nAmount owed is 9.0\nYou earned 6 frequent renter points",
				cust.statement());
	}

	@Test
	public final void testHtmlStatement() {
		Customer cust = new Customer("Customer1");

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

		cust.setRentals(mockedRentals);

		assertEquals(
				"<H1>Rentals for <EM>Customer1</EM></H1><P>\nMovie1: 1.5<BR>\nMovie2: 3.0<BR>\nMovie3: 4.5<BR>\n<P>You owe <EM>9.0</EM><P>\nOn this rental you earned <EM>6</EM> frequent renter points<P>",
				cust.htmlStatement());
	}

	@Test
	public final void testGetTotalCharge() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetTotalFrequentRenterPoints() {
		fail("Not yet implemented"); // TODO
	}

}
