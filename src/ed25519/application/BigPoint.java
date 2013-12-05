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
		//BigInteger x = 
		return null;
	}
	
	public BigInteger getXX(BigInteger input) {
		BigInteger modY = input.multiply(input);
		BigInteger part = modY.subtract(BigInteger.valueOf(1));
		
		//d*y*y+1
		BigInteger returnValue = part.multiply(invertPoint(input.add(BigInteger.valueOf(1))));
		return returnValue;
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