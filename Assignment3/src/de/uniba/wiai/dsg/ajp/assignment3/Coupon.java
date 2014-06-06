package de.uniba.wiai.dsg.ajp.assignment3;

public class Coupon {

	String code;
	double value;

	public Coupon(String code, double value) {
		this.code = code;
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double useCoupon(double totalCharge) {
		if (value - totalCharge > 0) {
			value = value - totalCharge;
			return totalCharge;
		} else {
			value = 0.0;
			return value;
		}
	}

}
