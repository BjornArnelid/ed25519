package ed25519.application.internal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

	private MessageDigest hasher;
	
	public Hash(String hashAlgorithm) throws NoSuchAlgorithmException {
		hasher = MessageDigest.getInstance(hashAlgorithm);
	}

	public BitArray digest(BitArray input) {
		hasher.reset();
		return new BitArray(hasher.digest(input.getBytes()));
	}
}
