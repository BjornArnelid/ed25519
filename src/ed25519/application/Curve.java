package ed25519.application;

import java.math.BigInteger;

public class Curve {
	private NumberUtils util;
	private Constants constants;
	private BigInteger d;
	
	public Curve() {
		constants = new Constants();
		util = new NumberUtils(constants);
		d = new BigInteger("-121665").multiply(invert(new BigInteger("121666")));
	}

	public BigPoint getBasePoint() {
		BigInteger tmp = invert(BigInteger.valueOf(5));
		BigInteger y = BigInteger.valueOf(4).multiply(tmp);
		BigInteger x = recoverX(y);
		return new BigPoint(x,y);
	}

	public BigInteger invert(BigInteger point) {
		return util.expmod(point, constants.getqold().subtract(BigInteger.valueOf(2)));
	}
	
	public BigInteger recoverX(BigInteger yValue) {
		BigInteger xx = getXX(yValue);
		BigInteger q = (constants.getqold().add(BigInteger.valueOf(3))).divide(BigInteger.valueOf(8));
		BigInteger x = util.expmod(xx,q);
		x = pruneXX(x, xx);
		return x;
	}

	public BigInteger getXX(BigInteger input) {
		BigInteger input2 = powInput(input);
		BigInteger first = input2.subtract(BigInteger.valueOf(1));
		
		BigInteger d = BigInteger.valueOf(-121665).multiply(invert(BigInteger.valueOf(121666)));
		BigInteger partSecond = input2.multiply(d).add(BigInteger.valueOf(1));
		BigInteger second = invert(partSecond);
		return first.multiply(second);
	}
	
	private BigInteger pruneXX(BigInteger x, BigInteger xx) {
		BigInteger returnValue = x;

		BigInteger bla = powInput(x);
		if(bla.subtract(xx).mod(constants.getqold()) != BigInteger.valueOf(0) ) {
			BigInteger val =  x.multiply(util.getI());
			returnValue =val.mod(constants.getqold());
		}
		if(returnValue.mod(BigInteger.valueOf(2)) != BigInteger.valueOf(0)) {
			returnValue = constants.getqold().subtract(returnValue);
		}
		return returnValue;
	}
	
	private BigInteger powInput(BigInteger input) {
		return input.multiply(input);
	}

	public BigPoint scalarmult(BigInteger input) {
		BigInteger x = new BigInteger("0");
		BigInteger y = new BigInteger("1");
		return new BigPoint(x, y);
	}

	public BigPoint edwards(BigPoint first, BigPoint second) {
		BigInteger x = getEdwardX(first, second);
		BigInteger y = getEdwardY(first, second);
		return new BigPoint(x, y);
	}
	
	private BigInteger getEdwardX(BigPoint first, BigPoint second) {
		BigInteger part1 = first.getX().add(second.getX());
		BigInteger part2 = invert(new BigInteger("0"));
		return part1.multiply(part2);
	}

	private BigInteger getEdwardY(BigPoint first, BigPoint second) {
		return new BigInteger("0");
	}

	public Object getD() {
		return d;
	}

}
