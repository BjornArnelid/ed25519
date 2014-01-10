package ed25519.application;

public class Constants {
	
	private static Constants c;
	
	private int b;
	private CryptoNumber q;
	private CryptoNumber I;
	private CryptoNumber d;
	
	private Constants() {
		b = 256;
	}
	
	public int getb() {
		return b;
	}
	
	public CryptoNumber getq() {
		if(q == null) {
			q = new CryptoNumber(2).pow(255).subtract(19);
		}
		return q.copy();
	}

	public CryptoNumber getI() {
		if (I == null) {
			CryptoNumber exp = getq().subtract(1).divide(4);
			I = new CryptoNumber(2).expmod(exp);
		}
		return I.copy();
	}


	public static Constants getInstance() {
		if(c == null) {
			c = new Constants();
		}
		return c;
	}

	public CryptoNumber getD() {
		if(d == null) {
			CryptoNumber multiplier = new CryptoNumber("121666").invert();
			d = new CryptoNumber("-121665").multiply(multiplier);
		}
		return d.copy();
	}
}
