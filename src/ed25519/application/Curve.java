package ed25519.application;

public class Curve {

	public Curve() {
		
	}

	public BigPoint getBasePoint() {
		CryptoNumber tmp = new CryptoNumber(5).invert();
		CryptoNumber y = new CryptoNumber(4).multiply(tmp);
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
		CryptoNumber y = getEdwardY(first, second);
		return new BigPoint(x, y);
	}
	
	private CryptoNumber getEdwardX(BigPoint first, BigPoint second) {
		Constants c = Constants.getInstance();
		
		CryptoNumber part1 = first.getX().multiply(second.getY());
		part1.add(second.getX().multiply(first.getY()));
		
		CryptoNumber part2 = new CryptoNumber(1).add(c.getD()).invert();
		return part1.multiply(part2);
	}

	private CryptoNumber getEdwardY(BigPoint first, BigPoint second) {
		CryptoNumber part1 = first.getY().copy().multiply(second.getY());
		part1.add(first.getX().multiply(second.getX()));
		
		CryptoNumber part2 = edwardsMultiply(first, second).invert();
		return part1.multiply(part2);
	}

	private CryptoNumber edwardsMultiply(BigPoint first, BigPoint second) {
		Constants c = Constants.getInstance();
		CryptoNumber result = new CryptoNumber(1);
		CryptoNumber subtrahend = c.getD();
		
		subtrahend.multiply(first.getX()).multiply(first.getY());
		subtrahend.multiply(second.getX()).multiply(second.getY());
		return result.subtract(subtrahend);
	}
}
