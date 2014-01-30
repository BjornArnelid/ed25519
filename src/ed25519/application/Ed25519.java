package ed25519.application;

import java.security.NoSuchAlgorithmException;

import ed25519.application.internal.BigPoint;
import ed25519.application.internal.ByteArray;
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
		ByteArray hash = sha512.digest(privateKey);
		
		CryptoNumber a = getA(hash);
		BigPoint A = curve.scalarmult(a);
		return new Key(curve.encodePoint(A).getBytes());
	}

	public Key sign(Message m, Key sk, Key pk) {
		int b = constants.getb();
		ByteArray hash = sha512.digest(sk);
		
		CryptoNumber a = getA(hash);
		ByteArray subHash = hash.getBytes(b/8, b/4);
		subHash.append(m);
		//ByteArray rInput = concatArrays(subHash, m.bytes());
		CryptoNumber r = curve.hint(subHash);
		BigPoint R = curve.scalarmult(r);
		//ByteArray sInput = concatArrays(curve.encodePoint(R), pk.bytes());
		ByteArray sInput = curve.encodePoint(R).append(pk);
		//sInput = concatArrays(sInput, m.bytes());
		sInput.append(m);
		CryptoNumber S = r.copy().add(curve.hint(sInput).multiply(a));
		S.mod(constants.getL());
		//return concatArrays(curve.encodePoint(R), curve.encodeNumber(S));
		return new Key(curve.encodePoint(R).append(curve.encodeNumber(S)).getBytes());
	}

	private CryptoNumber getA(ByteArray array) {
		CryptoNumber n = new CryptoNumber(2).pow(256-2);
			
		for (int i=3; i<(constants.getb() - 2); ++i) {
			if(array.getBit(i) == 1) {
				n = n.add(new CryptoNumber(2).pow(i));
			}
		}
		return n;
	}
	
//	public ByteArray concatArrays(byte[] array1, byte[] array2) {
//		int size = array1.length + array2.length;
//		byte[] result = new byte[size];
//		for(int i=0;i<size;++i) {
//			if(i<array1.length) {
//				result[i] = array1[i];
//			}
//			else {
//				result[i] = array2[i-array1.length];
//			} 
//		}
//		return result;
//	}
}
