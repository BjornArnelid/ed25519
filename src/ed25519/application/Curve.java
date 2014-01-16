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
		
		CryptoNumber part1 = first.getX().copy().multiply(second.getY());
		part1.add(second.getX().copy().multiply(first.getY()));
		CryptoNumber part2 = new CryptoNumber(1);
		part2.add(edwardsMultiply(first, second));
		part2.invert();
		return part1.multiply(part2);
	}

	private CryptoNumber getEdwardY(BigPoint first, BigPoint second) {
		CryptoNumber part1 = first.getY().copy().multiply(second.getY());
		part1.add(first.getX().multiply(second.getX()));
		
		CryptoNumber part2 = new CryptoNumber(1);
		part2.subtract(edwardsMultiply(first, second));
		part2.invert();
		return part1.multiply(part2);
	}

	private CryptoNumber edwardsMultiply(BigPoint first, BigPoint second) {
		Constants c = Constants.getInstance();
		CryptoNumber result = c.getD();
		
		result.multiply(first.getX()).multiply(first.getY());
		result.multiply(second.getX()).multiply(second.getY());
		return result;
	}
}
