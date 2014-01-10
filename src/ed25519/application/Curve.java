package ed25519.application;

public class Curve {

	public Curve() {
		
	}

	public BigPoint getBasePoint() {
		CryptoNumber tmp = new CryptoNumber(5).invert();
		CryptoNumber y = new CryptoNumber(4).multiply(tmp);
		System.out.println(y);
		return new BigPoint(y);
	}

//	public BigPoint scalarmult(BigInteger input) {
//		BigInteger x = new BigInteger("0");
//		BigInteger y = new BigInteger("1");
//		return new BigPoint(x, y);
//	}
//
	public BigPoint edwards(BigPoint first, BigPoint second) {
		CryptoNumber x = getEdwardX(first.copy(), second.copy());
		CryptoNumber y = getEdwardY(first.copy(), second.copy());
		return new BigPoint(x, y);
	}
	
	private CryptoNumber getEdwardX(BigPoint first, BigPoint second) {
		Constants c = Constants.getInstance();
		CryptoNumber part1 = first.getX().add(second.getX());
		CryptoNumber part2 = new CryptoNumber(1).add(c.getD()).invert();
		return part1.multiply(part2);
	}

	private CryptoNumber getEdwardY(BigPoint first, BigPoint second) {
		Constants c = Constants.getInstance();
		CryptoNumber part1 = first.getY().add(first.getX());
		CryptoNumber part2 = new CryptoNumber(1).subtract(c.getD()).invert();
		return part1.multiply(part2);
	}
}
