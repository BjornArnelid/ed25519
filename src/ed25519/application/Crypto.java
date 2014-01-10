package ed25519.application;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class Crypto {

	public byte[] getPublikKey(byte[] privateKey)
			throws NoSuchAlgorithmException {
		Constants constants = Constants.getInstance();
		Curve curve = new Curve();
		Hash sha512 = new Hash("SHA-512");
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
