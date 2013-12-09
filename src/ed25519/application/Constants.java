package ed25519.application;

import java.math.BigInteger;

public class Constants {
	private int b;
	private BigInteger q;
	
	public Constants() {
		b = 256;
		q = BigInteger.valueOf(2).pow(255).subtract(BigInteger.valueOf(19));
	}
	
	public int getb() {
		return b;
	}
	
	public BigInteger getq() {
		return q;
	}
}
