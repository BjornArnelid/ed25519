package ed25519.application.internal;

public class BigPoint {
	private CryptoNumber x;
	private CryptoNumber y;

	public BigPoint() {

	}

	public BigPoint(CryptoNumber xValue, CryptoNumber yValue) {
		this();
		setX(xValue);
		setY(yValue);
	}

	public BigPoint(CryptoNumber yValue) {
		this();
		Constants c = Constants.getInstance();
		
		CryptoNumber xx = getXX(yValue.copy());
		CryptoNumber qPart = c.getq().add(3).divide(8);
		CryptoNumber x = xx.copy().expmod(qPart);
		
		setX(pruneXX(x, xx));
		setY(yValue);
	}
	
	private CryptoNumber getXX(CryptoNumber input) {
		Constants c = Constants.getInstance();
		input.square();
		
		CryptoNumber first = input.copy().subtract(1);
		CryptoNumber second = input.multiply(c.getD()).add(1).invert();
		
		return first.multiply(second);
	}

	private CryptoNumber pruneXX(CryptoNumber x, CryptoNumber xx) {
		Constants c = Constants.getInstance();
		CryptoNumber zero = new CryptoNumber(0);
		if(!x.copy().square().subtract(xx).modQ().equals(zero)) {
			x.multiply(c.getI()).modQ();
		}
		if(!x.copy().mod(2).equals(zero)) {
			x = c.getq().subtract(x);
		}
		return x;
	}
	
	public void setY(CryptoNumber yValue) {
		y = yValue.modQ();	
	}

	public void setX(CryptoNumber xValue) {
		x = xValue.modQ();
	}
	
	public CryptoNumber getX() {
		return x;
	}
	
	public CryptoNumber getY() {
		return y;
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

	public BigPoint copy() {
		return new BigPoint(getX().copy(), getY().copy());
	}
}
