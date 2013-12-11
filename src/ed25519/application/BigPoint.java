package ed25519.application;

import java.math.BigInteger;

public class BigPoint {
	private Constants constants;
	private BigInteger x;
	private BigInteger y;
	
	public BigPoint(BigInteger first, BigInteger second) {
		this();
		setX(first);
		setY(second);
	}

	public BigPoint() {
		constants = new Constants();
	}

	public BigInteger getY() {
		return y;
	}

	public BigInteger getX() {
		return x;
	}
	
	public void setX(BigInteger x) {
		this.x = x.mod(constants.getq());
	}

	public void setY(BigInteger y) {
		this.y = y.mod(constants.getq());
	}
	
	@Override
	public boolean equals(Object aThat){
		if(this.getClass() == aThat.getClass()){
			BigPoint otherPoint = (BigPoint) aThat;
			if(getX().equals(otherPoint.getX()) && getY().equals(otherPoint.getY())) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return x.toString() + ", " + y.toString();
	}
}
