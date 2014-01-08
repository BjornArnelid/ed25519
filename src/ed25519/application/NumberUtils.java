package ed25519.application;

import java.math.BigInteger;

public class NumberUtils {
	private Constants constants;
	private BigInteger i;
	
	public NumberUtils(Constants c) {
		constants = c;
		//I = expmod(2,(q-1)/4,q)
		BigInteger primePart = constants.getqold().subtract(BigInteger.valueOf(1));
		i = expmod(BigInteger.valueOf(2),primePart.divide(BigInteger.valueOf(4)));
	}
	
	public BigInteger expmod(BigInteger multiplier, BigInteger primePart) {
		BigInteger returnValue;
		if(primePart.equals(BigInteger.valueOf(0))) {
			return BigInteger.valueOf(1);
		}
		returnValue = expmod(multiplier, primePart.divide(BigInteger.valueOf(2)));
		returnValue = returnValue.pow(2);
		returnValue = returnValue.mod(constants.getqold());
		if(primePart.testBit(0)) {
			returnValue = multiplier.multiply(returnValue);
			returnValue = returnValue.mod(constants.getqold());
		}
		return returnValue;
	}

	public BigInteger getI() {
		return i;
	}

}
