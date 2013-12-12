package ed25519.application;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class Crypto {

	private Constants constants;
	private Curve curve;
	private Hash sha512;
	
	public Crypto() throws NoSuchAlgorithmException {
		constants = new Constants();
		curve = new Curve();
		sha512 = new Hash("SHA-512");
	}

	public byte[] getPublikKey(byte[] privateKey) {
		BigInteger n = BigInteger.valueOf(2).pow(256-2);
		sha512.digest(privateKey);
			
		for (int i=3;i<(constants.getb() - 2);i++) {
			if(sha512.getBit(i) == 1) {
				n = n.add(BigInteger.valueOf(2).pow(i));
			}
		}
		BigPoint B = curve.getBasePoint();
		//BigPoint A = scalarmult(B,n);
		//scalarmult
		//encodepoint
		return null;
	}

}
