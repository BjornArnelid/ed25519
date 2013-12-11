package ed25519.application;

import java.math.BigInteger;

public class BigPoint {

	private BigInteger x;
	private BigInteger y;
	
	public BigPoint(BigInteger first, BigInteger second) {
		this();
		setX(first);
		setY(second);
	}

	public BigPoint() {
		
	}

	public BigInteger getY() {
		return y;
	}

	public BigInteger getX() {
		return x;
	}
	
	public void setX(BigInteger x) {
		this.x = x;
	}

	public void setY(BigInteger y) {
		this.y = y;
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
