package ed25519.application;

import java.math.BigInteger;

public class CryptoNumber {

	private BigInteger value;

	public CryptoNumber(long input) {
		value = BigInteger.valueOf(input);
	}
	
	public CryptoNumber(String input) {
		value = new BigInteger(input);
	}

	@Override
	public String toString() {
		return String.valueOf(value);
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

	protected BigInteger getValue() {
		return value;
	}

//	@Override
//	public int hashCode() {
//		return Long.valueOf(value).hashCode();
//	}

	public void pow(CryptoNumber exponent) {
		value = value.pow(exponent.getValue().intValue());
	}

	public void subtract(CryptoNumber subtrahend) {
		value = value.subtract(subtrahend.getValue());
	}
}
