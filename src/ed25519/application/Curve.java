package ed25519.application;

public class Curve {

	private CryptoNumber d;
	
	public Curve() {
		
	}

	public BigPoint getBasePoint() {
		CryptoNumber tmp = new CryptoNumber(5).invert();
		CryptoNumber y = new CryptoNumber(4).multiply(tmp);
		System.out.println(y);
		return new BigPoint(y);
//		CryptoNumber x = recoverX(y.copy());
//		return new BigPoint(x,y);
	}

//	public BigPoint scalarmult(BigInteger input) {
//		BigInteger x = new BigInteger("0");
//		BigInteger y = new BigInteger("1");
//		return new BigPoint(x, y);
//	}
//
//	public BigPoint edwards(BigPoint first, BigPoint second) {
//		BigInteger x = getEdwardX(first, second);
//		BigInteger y = getEdwardY(first, second);
//		return new BigPoint(x, y);
//	}
//	
//	private BigInteger getEdwardX(BigPoint first, BigPoint second) {
//		BigInteger part1 = first.getX().add(second.getX());
//		BigInteger part2 = invert(new BigInteger("0"));
//		return part1.multiply(part2);
//	}
//
//	private BigInteger getEdwardY(BigPoint first, BigPoint second) {
//		return new BigInteger("0");
//	}

	public Object getD() {
		return d;
	}

}
