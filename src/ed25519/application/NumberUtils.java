package ed25519.application;

import java.math.BigInteger;

public class NumberUtils {
	private Constants constants;
	public NumberUtils(Constants c) {
		constants = c;
	}
	public BigInteger expmod(BigInteger multiplier, BigInteger primePart) {
		BigInteger returnValue;
		if(primePart.equals(BigInteger.valueOf(0))) {
			return BigInteger.valueOf(1);
		}
		returnValue = expmod(multiplier, primePart.divide(BigInteger.valueOf(2)));
		returnValue = returnValue.pow(2);
		returnValue = returnValue.mod(constants.getBigPrime());
		if(primePart.testBit(0)) {
			returnValue = multiplier.multiply(returnValue);
			returnValue = returnValue.mod(constants.getBigPrime());
		}
		return returnValue;
	}

}
