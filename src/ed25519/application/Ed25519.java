package ed25519.application;

import java.security.NoSuchAlgorithmException;

public class Ed25519 {

	public byte[] getPublikKey(byte[] privateKey)
			throws NoSuchAlgorithmException {
		Constants constants = Constants.getInstance();
		Curve curve = new Curve();
		Hash sha512 = new Hash("SHA-512");
		CryptoNumber n = new CryptoNumber(2).pow(256-2);
		sha512.digest(privateKey);
			
		for (int i=3;i<(constants.getb() - 2);i++) {
			if(sha512.getBit(i) == 1) {
				n = n.add(new CryptoNumber(2).pow(i));
			}
		}

		BigPoint A = curve.scalarmult(n);
		byte[] key = curve.encodePoint(A);
		return key;
	}

}
