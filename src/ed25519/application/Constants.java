package ed25519.application;

import java.math.BigInteger;

public class Constants {
	private int b;
	private BigInteger qold;
	private CryptoNumber q;
	
	public Constants() {
		b = 256;
		qold = BigInteger.valueOf(2).pow(255).subtract(BigInteger.valueOf(19));
		q = new CryptoNumber(2).pow(255).subtract(19);
	}
	
	public int getb() {
		return b;
	}
	
	public BigInteger getqold() {
		return qold;
	}
	
	public CryptoNumber getq() {
		return q.copy();
	}
}
