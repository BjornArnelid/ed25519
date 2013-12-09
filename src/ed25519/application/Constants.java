package ed25519.application;

import java.math.BigInteger;

public class Constants {
	private int bitLength;
	
	private BigInteger i;
	private BigInteger bigPrime;
	private BigInteger baseNumber;
	
	public Constants() {
		bitLength = 256;
		baseNumber = BigInteger.valueOf(2).pow(256-2);
		bigPrime = BigInteger.valueOf(2).pow(255).subtract(BigInteger.valueOf(19));
		//I = expmod(2,(q-1)/4,q)
		
	}
	
	public int getBitLength() {
		return bitLength;
	}
	
	public BigInteger getBaseNumber() {
		return baseNumber;
	}
	
	public BigInteger getBigPrime() {
		return bigPrime;
	}

	public BigInteger getI() {
		return i;
	}


}
