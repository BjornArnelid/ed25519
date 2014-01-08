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

	public void subtract(CryptoNumber subtrahend) {
		value = value.subtract(subtrahend.getValue());
	}

	public void subtract(long subtrahend) {
		value = value.subtract(BigInteger.valueOf(subtrahend));
	}
	
	public void add(CryptoNumber addend) {
		value = value.add(addend.getValue());
		
	}

	public void add(long addend) {
		value = value.add(BigInteger.valueOf(addend));
		
	}
	
	public void pow(CryptoNumber exponent) {
		value = value.pow(exponent.getValue().intValue());
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

//	@Override
//	public int hashCode() {
//		return Long.valueOf(value).hashCode();
//	}

}
