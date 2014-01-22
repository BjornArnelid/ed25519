package ed25519.application;

import java.security.NoSuchAlgorithmException;

public class Ed25519 {
	
	private Curve curve;
	private Constants constants;

	public Ed25519() {
		curve = new Curve();
		constants = Constants.getInstance();
	}

	public byte[] getPublikKey(byte[] privateKey)
			throws NoSuchAlgorithmException {
		Hash sha512 = new Hash("SHA-512");
		sha512.digest(privateKey);
		
		CryptoNumber a = getA(sha512);
		BigPoint A = curve.scalarmult(a);
		byte[] key = curve.encodePoint(A);
		return key;
	}

	public byte[] sign(byte[] m, byte[] sk, byte[] pk) 
			throws NoSuchAlgorithmException {
		int b = constants.getb();
		Hash sha512 = new Hash("SHA-512");
		sha512.digest(sk);
		
		CryptoNumber a = getA(sha512);
		byte[] subHash = sha512.getBytes(b/8, b/4);
		byte[] rInput = concatArrays(subHash, m);
		CryptoNumber r = curve.hint(rInput);
		BigPoint R = curve.scalarmult(r);
		byte[] sInput = concatArrays(curve.encodePoint(R), pk);
		sInput = concatArrays(sInput, m);
		CryptoNumber S = r.copy().add(curve.hint(sInput).multiply(a));
		S.mod(constants.getL());
		return concatArrays(curve.encodePoint(R), curve.encodeNumber(S));
	}

	private CryptoNumber getA(Hash sha512) {
		CryptoNumber n = new CryptoNumber(2).pow(256-2);
			
		for (int i=3; i<(constants.getb() - 2); ++i) {
			if(sha512.getBit(i) == 1) {
				n = n.add(new CryptoNumber(2).pow(i));
			}
		}
		return n;
	}
	
	public byte[] concatArrays(byte[] array1, byte[] array2) {
		int size = array1.length + array2.length;
		byte[] result = new byte[size];
		for(int i=0;i<size;++i) {
			if(i<array1.length) {
				result[i] = array1[i];
			}
			else {
				result[i] = array2[i-array1.length];
			} 
		}
		return result;
	}
}
