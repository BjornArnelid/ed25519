package ed25519.application;

import java.security.NoSuchAlgorithmException;

import ed25519.application.internal.BigPoint;
import ed25519.application.internal.BitArray;
import ed25519.application.internal.Constants;
import ed25519.application.internal.CryptoNumber;
import ed25519.application.internal.Curve;
import ed25519.application.internal.Hash;

public class Ed25519 {
	
	private Curve curve;
	private Constants constants;
	private Hash sha512;

	public Ed25519() throws NoSuchAlgorithmException {
		curve = new Curve();
		constants = Constants.getInstance();
		sha512 = new Hash("SHA-512");
	}

	public Key getPublikKey(Key privateKey) {
		BitArray hash = sha512.digest(privateKey);
		
		CryptoNumber a = getA(hash);
		BigPoint A = curve.scalarmult(a);
		return new Key(A);
	}

	public Key sign(Message m, Key sk, Key pk) {
		int b = constants.getb();
		BitArray hash = sha512.digest(sk);
		CryptoNumber a = getA(hash);
		BitArray subHash = hash.getBits(b, b*2);
		subHash.append(m);
		CryptoNumber r = curve.hint(subHash);
		BigPoint R = curve.scalarmult(r);
		BitArray sInput = new BitArray(R).append(pk);
		sInput.append(m);
		CryptoNumber S = r.copy().add(curve.hint(sInput).multiply(a));
		S.mod(constants.getL());
		Key returnKey = new Key(R);
		returnKey.append(new BitArray(S));
		return returnKey;
	}

	private CryptoNumber getA(BitArray array) {
		CryptoNumber n = new CryptoNumber(2).pow(256-2);
			
		for (int i=3; i<(constants.getb() - 2); ++i) {
			if(array.getBit(i)) {
				n = n.add(new CryptoNumber(2).pow(i));
			}
		}
		return n;
	}
}
