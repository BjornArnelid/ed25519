package ed25519.application;

import java.math.BigInteger;

public class Curve {
	private NumberUtils util;
	private Constants constants;
	
	public Curve() {
		constants = new Constants();
		util = new NumberUtils(constants);
	}

	public BigInteger invertPoint(BigInteger point) {
		return util.expmod(point, constants.getq().subtract(BigInteger.valueOf(2)));
	}

	public BigPoint getBasePoint() {
		// TODO Auto-generated method stub
		return null;
	}

}
