package ed25519.application;

import java.math.BigInteger;

public class BigPoint {

	private BigInteger x;
	private BigInteger y;
	
	private Constants constants;
	private NumberUtils util;
	
	public BigPoint(BigInteger first, BigInteger second) {
		x = first;
		y = second;
		constants = new Constants();
		util = new NumberUtils(constants);
	}

	//Lets make base-point baseValue...
	public BigPoint() {
		this(BigInteger.valueOf(0),BigInteger.valueOf(0));
		y = invertPoint(BigInteger.valueOf(5).multiply(BigInteger.valueOf(4)));
		//x = xrecover(y);
	
	}

	public BigInteger invertPoint(BigInteger point) {
		return util.expmod(point, constants.getBigPrime().subtract(BigInteger.valueOf(2)));
	}
	
	public BigInteger recoverX(BigInteger yValue) {
		BigInteger xx = getXX(yValue);
		BigInteger q = (constants.getBigPrime().add(BigInteger.valueOf(3))).divide(BigInteger.valueOf(8));
		BigInteger x = util.expmod(xx,q);
		x = pruneXX(x, xx);
		return x;
	}

	public BigInteger getXX(BigInteger input) {
		BigInteger input2 = modInput(input);
		BigInteger first = input2.subtract(BigInteger.valueOf(1));
		
		BigInteger d = BigInteger.valueOf(-121665).multiply(invertPoint(BigInteger.valueOf(121666)));
		BigInteger partSecond = input2.multiply(d).add(BigInteger.valueOf(1));
		BigInteger second = invertPoint(partSecond);
		return first.multiply(second);
	}
	
	private BigInteger pruneXX(BigInteger x, BigInteger xx) {
		BigInteger returnValue = x;
		//   if (x*x - xx) % q != 0: x = (x*I) % q
		BigInteger bla = modInput(x);
		if(bla.subtract(xx).mod(constants.getBigPrime()) != BigInteger.valueOf(0) ) {
			//returnValue = x.multiply(i).mod(bigPrime);
		}
		//   if x % 2 != 0: x = q-x
		return returnValue;
	}
	
	private BigInteger modInput(BigInteger input) {
		return input.multiply(input);
	}

	public BigInteger getY() {
		return y;
	}

	public BigInteger getX() {
		return x;
	}
	
	@Override
	public boolean equals(Object aThat){
		if(this.getClass() == aThat.getClass()){
			BigPoint otherPoint = (BigPoint) aThat;
			if(getX()==otherPoint.getX() && getY()==otherPoint.getY()) {
				return true;
			}
		}
		return false;
	}
}
