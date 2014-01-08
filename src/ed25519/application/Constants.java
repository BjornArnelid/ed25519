package ed25519.application;

import java.math.BigInteger;

public class Constants {
	private int b;
	private BigInteger qold;
	private CryptoNumber q;
	private CryptoNumber I;
	
	public Constants() {
		b = 256;
		qold = BigInteger.valueOf(2).pow(255).subtract(BigInteger.valueOf(19));
		q = new CryptoNumber(2).pow(255).subtract(19);
		CryptoNumber exp = getq().subtract(1).divide(4);
		I = new CryptoNumber(2).expmod(exp, getq());
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

	public CryptoNumber getI() {
		return I.copy();
	}
}
