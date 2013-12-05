package ed25519.application;

import java.math.BigInteger;

public class NumberUtils {
	public int BIT_LENGTH;
	public BigInteger BIG_PRIME;
	
	public NumberUtils() {
		BIT_LENGTH = 256;
	}
	
	public BigInteger getBaseNumber() {
		return BigInteger.valueOf(2).pow(BIT_LENGTH-2);
	}
	
	public BigInteger get2PowerOf(int power) {
		return BigInteger.valueOf(2).pow(power);
	}

	public BigPoint createBasePoint() {
		return new BigPoint();
	}
	


}
