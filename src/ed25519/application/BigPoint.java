package ed25519.application;

import java.math.BigInteger;

public class BigPoint {

	private BigInteger x;
	private BigInteger y;
	
	private BigInteger bigPrime;

	public BigPoint(BigInteger first, BigInteger second) {
		x = first;
		y = second;
		// 2^255 - 19
		bigPrime = BigInteger.valueOf(2).pow(255).subtract(BigInteger.valueOf(19));
	}

	//Lets make base-point baseValue...
	public BigPoint() {
		this(BigInteger.valueOf(0),BigInteger.valueOf(0));
		y = invertPoint(BigInteger.valueOf(5).multiply(BigInteger.valueOf(4)));
		//x = xrecover(y);
	
	}
	
	public BigInteger expmod(BigInteger multiplier, BigInteger primePart) {
		BigInteger returnValue;
		if(primePart.equals(BigInteger.valueOf(0))) {
			return BigInteger.valueOf(1);
		}
		returnValue = expmod(multiplier, primePart.divide(BigInteger.valueOf(2)));
		returnValue = returnValue.pow(2);
		returnValue = returnValue.mod(bigPrime);
		if(primePart.testBit(0)) {
			returnValue = multiplier.multiply(returnValue);
			returnValue = returnValue.mod(bigPrime);
		}
		return returnValue;
	}
	
	public BigInteger getBigPrime() {
		return bigPrime;
	}
	
	public BigInteger invertPoint(BigInteger point) {
		return expmod(point, bigPrime.subtract(BigInteger.valueOf(2)));
	}
	
	public BigInteger recoverX(BigInteger yValue) {
		BigInteger xx = getXX(yValue);
		BigInteger q = (bigPrime.add(BigInteger.valueOf(3))).divide(BigInteger.valueOf(8));
		BigInteger x = expmod(xx,q);
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
