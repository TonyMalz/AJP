import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.uniba.wiai.dsg.ajp.assignment3.Coupon;
import de.uniba.wiai.dsg.ajp.assignment3.Customer;
import de.uniba.wiai.dsg.ajp.assignment3.Movie;
import de.uniba.wiai.dsg.ajp.assignment3.Rental;

public class ValidRentalTest {
	private static final String VOUCHER_CODE_1 = "XYZ123";
	private static final String CUSTOMER_1 = "Hans Dampf";
	Movie childsMovie;
	Movie regularMovie;
	Movie newReleaseMovie;
	List<Rental> testRentals;

	@Before
	public void setUp() throws Exception {
		childsMovie = new Movie("A CHilds FLick", Movie.CHILDRENS);
		regularMovie = new Movie("A Regular Movie", Movie.REGULAR);
		newReleaseMovie = new Movie("A BlockBustOoor", Movie.NEW_RELEASE);
		testRentals = new ArrayList<>();
		testRentals.add(new Rental(childsMovie, 0));
		testRentals.add(new Rental(childsMovie, 3));
		testRentals.add(new Rental(childsMovie, 4));
		testRentals.add(new Rental(regularMovie, 0));
		testRentals.add(new Rental(regularMovie, 2));
		testRentals.add(new Rental(regularMovie, 3));
		testRentals.add(new Rental(newReleaseMovie, 0));
		testRentals.add(new Rental(newReleaseMovie, 1));
		testRentals.add(new Rental(newReleaseMovie, 2));

	}

	private String getTestContentFromFile(String path) throws IOException {
		String s = "";
		for (String line : Files.readAllLines(
				Paths.get("integration/" + path + ".log"),
				StandardCharsets.UTF_8)) {
			s += line + "\n";
		}
		return s.substring(0, s.length() - 1);
	}

	@Test
	public void testValidRentalWithoutVoucher() throws IOException {
		Customer customer = new Customer(CUSTOMER_1);
		customer.setRentals(testRentals);
		Assert.assertEquals(
				getTestContentFromFile("testValidRentalWithoutVoucher"),
				customer.statement());

	}

	@Test
	public void testValidRentalWithVoucherRedemptionGreaterThanTotalSum()
			throws IOException {
		Customer customer = new Customer(CUSTOMER_1);
		customer.setRentals(testRentals);

		Assert.assertEquals(
				getTestContentFromFile("testValidRentalWithVoucherRedemptionGreaterThanTotalSum"),
				customer.statement(new Coupon(VOUCHER_CODE_1, 20)));
	}

	@Test
	public void testValidRentalWithVoucherRedemptionLessThanTotalSum()
			throws IOException {
		Customer customer = new Customer(CUSTOMER_1);
		customer.setRentals(testRentals);

		Assert.assertEquals(
				getTestContentFromFile("testValidRentalWithVoucherRedemptionLessThanTotalSum"),
				customer.statement(new Coupon(VOUCHER_CODE_1, 2)));
	}

	@Test
	public void testValidRentalWithZeroVoucherRedemption() throws IOException {
		Customer customer = new Customer(CUSTOMER_1);
		customer.setRentals(testRentals);

		Assert.assertEquals(
				getTestContentFromFile("testValidRentalWithZeroVoucherRedemption"),
				customer.statement(new Coupon(VOUCHER_CODE_1, 0)));
	}

	@Test
	public void testValidRentalWithoutVoucherHTML() throws IOException {
		Customer customer = new Customer(CUSTOMER_1);
		customer.setRentals(testRentals);
		Assert.assertEquals(
				getTestContentFromFile("testValidRentalWithoutVoucherHTML"),
				customer.htmlStatement());

	}

	@Test
	public void testValidRentalWithVoucherRedemptionGreaterThanTotalSumHTML()
			throws IOException {
		Customer customer = new Customer(CUSTOMER_1);
		customer.setRentals(testRentals);
		Assert.assertEquals(
				getTestContentFromFile("testValidRentalWithVoucherRedemptionGreaterThanTotalSumHTML"),
				customer.htmlStatement(new Coupon(VOUCHER_CODE_1, 20)));
	}

	@Test
	public void testValidRentalWithVoucherRedemptionLessThanTotalSumHTML()
			throws IOException {
		Customer customer = new Customer(CUSTOMER_1);
		customer.setRentals(testRentals);
		Assert.assertEquals(
				getTestContentFromFile("testValidRentalWithVoucherRedemptionLessThanTotalSumHTML"),
				customer.htmlStatement(new Coupon(VOUCHER_CODE_1, 2)));
	}

	@Test
	public void testValidRentalWithZeroVoucherRedemptionHTML()
			throws IOException {
		Customer customer = new Customer(CUSTOMER_1);
		customer.setRentals(testRentals);
		Assert.assertEquals(
				getTestContentFromFile("testValidRentalWithZeroVoucherRedemptionHTML"),
				customer.htmlStatement(new Coupon(VOUCHER_CODE_1, 0)));
	}
}
