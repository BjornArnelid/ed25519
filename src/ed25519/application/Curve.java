package ed25519.application;

import java.math.BigInteger;

public class Curve {
	private NumberUtils util;
	private Constants constants;
	
	public Curve() {
		constants = new Constants();
		util = new NumberUtils(constants);
	}

	public BigPoint getBasePoint() {
		// TODO Auto-generated method stub
		return null;
	}

	public BigInteger invertPoint(BigInteger point) {
		return util.expmod(point, constants.getq().subtract(BigInteger.valueOf(2)));
	}
	
	public BigInteger recoverX(BigInteger yValue) {
		BigInteger xx = getXX(yValue);
		BigInteger q = (constants.getq().add(BigInteger.valueOf(3))).divide(BigInteger.valueOf(8));
		BigInteger x = util.expmod(xx,q);
		x = pruneXX(x, xx);
		return x;
	}

	public BigInteger getXX(BigInteger input) {
		BigInteger input2 = powInput(input);
		BigInteger first = input2.subtract(BigInteger.valueOf(1));
		
		BigInteger d = BigInteger.valueOf(-121665).multiply(invertPoint(BigInteger.valueOf(121666)));
		BigInteger partSecond = input2.multiply(d).add(BigInteger.valueOf(1));
		BigInteger second = invertPoint(partSecond);
		return first.multiply(second);
	}
	
	private BigInteger pruneXX(BigInteger x, BigInteger xx) {
		BigInteger returnValue = x;

		BigInteger bla = powInput(x);
		if(bla.subtract(xx).mod(constants.getq()) != BigInteger.valueOf(0) ) {
			BigInteger val =  x.multiply(util.getI());
			returnValue =val.mod(constants.getq());
		}
		if(returnValue.mod(BigInteger.valueOf(2)) != BigInteger.valueOf(0)) {
			returnValue = constants.getq().subtract(returnValue);
		}
		return returnValue;
	}
	
	private BigInteger powInput(BigInteger input) {
		return input.multiply(input);
	}
}