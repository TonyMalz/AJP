package de.uniba.wiai.dsg.ajp.assignment3;

import java.util.Objects;

/**
 * This class contains the price range and the title of one Movie.
 */
public class Movie {
	/**
	 * Constants to easily determine movie's price via Integer
	 */
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int SERIES = 3;

	/**
	 * Movie's price range
	 */
	private Price price;

	/**
	 * Movie's title
	 */
	private String title;

	/**
	 * Default Constructor
	 */
	public Movie() {

	}

	/**
	 * Constructor sets title and priceCode
	 * 
	 * @param title
	 *            of movie
	 * @param priceCode
	 *            of movie
	 */
	public Movie(final String title, final int priceCode) {
		setTitle(title);
		setPriceCode(priceCode);
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            to set
	 * @throws NullPointerException
	 *             if title is null
	 * @throws IllegalArgumentException
	 *             if title is an empty String
	 */
	public void setTitle(final String title) {
		Objects.requireNonNull(title, "title is null");
		if (title.length() == 0) {
			throw new IllegalArgumentException("name is empty");
		}
		this.title = title;
	}

	/**
	 * Calculates charge for this movie with given number of days rented by
	 * calling {@link de.uniba.wiai.dsg.ajp.assignment3.Price#getCharge(int)}
	 * 
	 * @param daysRented
	 *            to calculate charge
	 * @return charge for this movie
	 */
	double getCharge(final int daysRented) {
		return price.getCharge(daysRented);
	}

	/**
	 * @return priceCode by calling
	 *         {@link de.uniba.wiai.dsg.ajp.assignment3.Price#getPriceCode()}
	 */
	public int getPriceCode() {
		return price.getPriceCode();
	}

	/**
	 * creates new ...Price class by given Integer and sets priceCode to this
	 * new class
	 * 
	 * <ul>
	 * <li>REGULAR: &nbsp &nbsp &nbsp &nbsp &nbsp 0 &nbsp -> &nbsp &nbsp new
	 * RegularPrice()</li>
	 * <li>NEW_RELEASE: &nbsp 1 &nbsp -> &nbsp &nbsp new NewReleasePrice()</li>
	 * <li>CHILDRENS: &nbsp &nbsp &nbsp 2 &nbsp -> &nbsp &nbsp new
	 * ChildrensPrice()</li>
	 * <li>SERIES: &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp 3 &nbsp ->
	 * &nbsp &nbsp new RegularPrice()</li>
	 * </ul>
	 * 
	 * @param priceCode
	 *            Integer of declared Constants
	 */
	public void setPriceCode(final int priceCode) {
		switch (priceCode) {
			case REGULAR :
				price = new RegularPrice();
				break;
			case CHILDRENS :
				price = new ChildrensPrice();
				break;
			case NEW_RELEASE :
				price = new NewReleasePrice();
				break;
			case SERIES :
				price = new SeriesPrice();
				break;
			default :
				throw new IllegalArgumentException("Incorrect Price Code");
		}
	}

	/**
	 * Calculates FrequentRenterPoints for this movie with given number of days
	 * rented by calling
	 * {@link de.uniba.wiai.dsg.ajp.assignment3.Price#getFrequentRenterPoints(int)}
	 * 
	 * @param daysRented
	 *            to calculate FrequentRenterPoints
	 * @return FrequentRenterPoints for this movie
	 */
	public int getFrequentRenterPoints(final int daysRented) {
		return price.getFrequentRenterPoints(daysRented);
	}

}
