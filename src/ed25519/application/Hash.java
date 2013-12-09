package ed25519.application;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

	private MessageDigest hasher;
	private byte[] bytes;
	
	public Hash(String hashAlgorithm) throws NoSuchAlgorithmException {
		hasher = MessageDigest.getInstance(hashAlgorithm);
	}

	public void digest(byte[] input) {
		hasher.reset();
		bytes = hasher.digest(input);
	}

	public byte[] getBytes() {
		return bytes;
	}

	public int getBit(int position) {
		return bytes[position/8] >> (position%8) & 1;
	}
}
