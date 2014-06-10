package de.uniba.wiai.dsg.ajp.assignment3;

import java.util.Objects;

public class Coupon {

	String code;
	double value;

	public Coupon(String code, double value) {
		setCode(code);
		setValue(value);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		Objects.requireNonNull(code, "code is null");
		if (code.length() == 0) {
			throw new IllegalArgumentException("code is empty");
		}
		this.code = code;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		if (value < 0) {
			throw new IllegalArgumentException("value was less than 0");
		}
		this.value = value;
	}
}
