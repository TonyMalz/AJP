package de.uniba.wiai.dsg.ajp.assignment3;

public class Movie {
	// TODO imput validation
	// TODO javadoc
	// TODO test mock/Stub
	// TODO test integration
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int SERIES = 3;

	private Price price;

	private String title;

	public Movie() {

	}

	public Movie(final String title, final int priceCode) {
		this.title = title;
		setPriceCode(priceCode);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	double getCharge(final int daysRented) {
		return price.getCharge(daysRented);
	}

	public int getPriceCode() {
		return price.getPriceCode();
	}

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
			default :
				throw new IllegalArgumentException("Incorrect Price Code");
		}
	}

	public int getFrequentRenterPoints(final int daysRented) {
		return price.getFrequentRenterPoints(daysRented);
	}

}
