package ed25519.application.internal;

import java.security.NoSuchAlgorithmException;

public class Curve {

	private Constants c;

	public Curve() {
		c = Constants.getInstance();
	}

	public BigPoint getBasePoint() {
		CryptoNumber tmp = new CryptoNumber(5).invert();
		CryptoNumber y = new CryptoNumber(4).multiply(tmp);
		return new BigPoint(y);
	}

	public BigPoint scalarmult(CryptoNumber input) {
		if(input.equals(new CryptoNumber(0))) {
			return new BigPoint(new CryptoNumber("0"), new CryptoNumber("1"));
		}
		BigPoint tmp = scalarmult(input.copy().divide(2));
		BigPoint returnValue = edwards(tmp.copy(), tmp.copy());
		if(input.testBit(0)){
			returnValue = edwards(returnValue, getBasePoint());
		}
		return returnValue;
	}

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
		part1.add(first.getX().copy().multiply(second.getX()));
		
		CryptoNumber part2 = new CryptoNumber(1);
		part2.subtract(edwardsMultiply(first, second));
		part2.invert();
		return part1.multiply(part2);
	}

	private CryptoNumber edwardsMultiply(BigPoint first, BigPoint second) {
		CryptoNumber result = c.getD();
		
		result.multiply(first.getX()).multiply(first.getY());
		result.multiply(second.getX()).multiply(second.getY());
		return result;
	}

	public byte[] encodePoint(BigPoint point) {
		byte[] bytes = encodeRange(point.getY());
		if(point.getX().testBit(0)) {
			bytes[31] |= 1 << 7;
		}
		return bytes;
	}
	
	public byte[] encodeNumber(CryptoNumber s) {
		byte[] bytes = encodeRange(s);
		if(s.testBit(c.getb())) {
			bytes[31] |= 1 << 7;			
		}
		return bytes;
	}

	private byte[] encodeRange(CryptoNumber number) {
		byte[] bytes = new byte[32];
		for(int i=0; i<(c.getb()-1);++i) {
			if(number.testBit(i)) {
				bytes[(i/8)] |= 1 << i%8;
			}
		}
		return bytes;
	}
	
	public CryptoNumber hint(byte[] m) throws NoSuchAlgorithmException {
		Hash hash = new Hash("SHA-512");
		hash.digest(m);
		CryptoNumber n = new CryptoNumber(0);
		for(int i=0; i<2*c.getb(); ++i) {
			if(hash.getBit(i) == 1) {
				n.add(new CryptoNumber(2).pow(i));
			}
		}
		return n;
	}
}
