package ed25519.application.internal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

	private MessageDigest hasher;
	
	public Hash(String hashAlgorithm) throws NoSuchAlgorithmException {
		hasher = MessageDigest.getInstance(hashAlgorithm);
	}

	public ByteArray digest(ByteArray input) {
		hasher.reset();
		return new ByteArray(hasher.digest(input.getBytes()));
	}
}
