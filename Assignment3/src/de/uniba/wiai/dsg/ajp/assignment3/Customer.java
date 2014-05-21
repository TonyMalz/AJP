package de.uniba.wiai.dsg.ajp.assignment3;

import java.util.LinkedList;
import java.util.List;

public class Customer {
    // TODO imput validation
    // TODO javadoc
    // TODO test mock/Stub
    // TODO test integration

    // TODO implement gutschein
    private String name;

    private List<Rental> rentals = new LinkedList<Rental>();

    public Customer() {

    }

    public Customer(final String name) {
	super();
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setName(final String name) {
	this.name = name;
    }

    public List<Rental> getRentals() {
	return rentals;
    }

    public void setRentals(final List<Rental> rentals) {
	this.rentals = rentals;
    }

    public String statement() {
	String result = "Rental Record for " + getName() + "\n";

	int frequentRenterPoints = 0;
	for (final Rental each : rentals) {
	    frequentRenterPoints += each.getFrequentRenterPoints();

	    // show figures for this rental
	    result += "\t" + each.getMovie().getTitle() + "\t"
		    + String.valueOf(each.getCharge()) + "\n";
	}

	// add footer lines
	result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
	result += "You earned " + String.valueOf(frequentRenterPoints)
		+ " frequent renter points";
	return result;
    }

    public String htmlStatement() {
	String result = "<H1>Rentals for <EM>" + getName() + "</EM></H1><P>\n";

	for (final Rental each : rentals) {
	    // show figures for each rental
	    result += each.getMovie().getTitle() + ": "
		    + String.valueOf(each.getCharge()) + "<BR>\n";
	}

	// add footer lines
	result += "<P>You owe <EM>" + String.valueOf(getTotalCharge())
		+ "</EM><P>\n";
	result += "On this rental you earned <EM>"
		+ String.valueOf(getTotalFrequentRenterPoints())
		+ "</EM> frequent renter points<P>";
	return result;
    }

    double getTotalCharge() {
	double result = 0;

	for (final Rental each : rentals) {
	    result += each.getCharge();
	}

	return result;
    }

    int getTotalFrequentRenterPoints() {
	int result = 0;

	for (final Rental each : rentals) {
	    result += each.getFrequentRenterPoints();
	}

	return result;
    }

}
