package ed25519.application.internal;

import java.math.BigInteger;

public class CryptoNumber {

	private BigInteger value;

	public CryptoNumber(long input) {
		this();
		value = BigInteger.valueOf(input);
	}
	
	public CryptoNumber(String input) {
		this();
		value = new BigInteger(input);
	}
	
	public CryptoNumber() {
	}

	public CryptoNumber subtract(CryptoNumber subtrahend) {
		value = value.subtract(subtrahend.getValue());
		return this;
	}

	public CryptoNumber subtract(long subtrahend) {
		value = value.subtract(BigInteger.valueOf(subtrahend));
		return this;
	}
	
	public CryptoNumber add(CryptoNumber addend) {
		value = value.add(addend.getValue());
		return this;
	}

	public CryptoNumber add(long addend) {
		value = value.add(BigInteger.valueOf(addend));
		return this;
	}
	
	public CryptoNumber divide(CryptoNumber divisor) {
		value = value.divide(divisor.getValue());
		return this;
	}

	public CryptoNumber divide(long divisor) {
		value = value.divide(BigInteger.valueOf(divisor));
		return this;
	}

	public CryptoNumber multiply(CryptoNumber multiplier) {
		value = value.multiply(multiplier.getValue());
		return this;
	}

	public CryptoNumber multiply(long multiplier) {
		value = value.multiply(BigInteger.valueOf(multiplier));
		return this;
	}
	
	public CryptoNumber mod(CryptoNumber modulus) {
		value = value.mod(modulus.getValue());
		return this;
	}

	public CryptoNumber mod(long modulus) {
		value = value.mod(BigInteger.valueOf(modulus));
		return this;
	}
	
	public CryptoNumber pow(CryptoNumber exponent) {
		value = value.pow(exponent.getValue().intValue());
		return this;
	}
	
	public CryptoNumber pow(long exponent) {
		value = value.pow((int)exponent);
		return this;
	}
	
	public CryptoNumber square() {
		value = value.pow(2);
		return this;
	}
	
	public CryptoNumber expmod(CryptoNumber expModulus) {
		CryptoNumber newValue = expmodRecursive(expModulus);
		value = newValue.getValue();
		return this;
	}
	
	
	public CryptoNumber expmodRecursive( CryptoNumber expModulus) {
		if(expModulus.equals(new CryptoNumber(0))) {
			return new CryptoNumber(1);
		}
		CryptoNumber expCopy = expModulus.copy();
		CryptoNumber returnValue = expmodRecursive(expCopy.divide(2));
		returnValue.pow(2).modQ();
		if(expModulus.testBit(0)) {
			returnValue.multiply(this).modQ();
		}
		return returnValue;
	}
	
	public CryptoNumber modQ() {
		Constants c = Constants.getInstance();
		mod(c.getq());
		return this;
	}
	
	public CryptoNumber copy() {
		CryptoNumber copy = new CryptoNumber(0);
		copy.value = value;
		return copy;
	}

	public CryptoNumber invert() {
		Constants c = Constants.getInstance();
		expmod(c.getq().subtract(2));
		return this;
	}
	
	public boolean testBit(int i) {
		return value.testBit(i);
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	protected BigInteger getValue() {
		return value;
	}
	
	@Override
	public boolean equals(Object aThat) {
		if(aThat instanceof CryptoNumber) {
			BigInteger other = ((CryptoNumber) aThat).getValue();
			if(value.equals(other)) {
				return true;
			}
		}
		return false;
	}
}
