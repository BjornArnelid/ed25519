package ed25519.application;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class Crypto {

	private NumberUtils util;
	private Hash sha512;
	
	public Crypto() throws NoSuchAlgorithmException {
		util = new NumberUtils();
		sha512 = new Hash("SHA-512");
	}
	public byte[] getPublikKey(byte[] privateKey) {
		BigInteger n = util.getBaseNumber();
		sha512.digest(privateKey);
			
		for (int i=3;i<(util.BIT_LENGTH - 2);i++) {
			if(sha512.getBit(i) == 1) {
				n = n.add(util.get2PowerOf(i));
			}
		}
		//scalarmult
		//encodepoint
		return null;
	}

}
