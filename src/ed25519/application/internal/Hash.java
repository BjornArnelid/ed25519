package ed25519.application.internal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Hash {

	private MessageDigest hasher;
	private byte[] bytes;
	
	public Hash(String hashAlgorithm) throws NoSuchAlgorithmException {
		hasher = MessageDigest.getInstance(hashAlgorithm);
	}

	public void digest(ByteArray input) {
		hasher.reset();
		bytes = hasher.digest(input.bytes());
	}

	public byte[] getBytes() {
		return bytes;
	}

	public int getBit(int position) {
		return bytes[position/8] >> (position%8) & 1;
	}

	public byte[] getBytes(int from, int to) {
		byte[] result = Arrays.copyOfRange(bytes, from, to);
		return result;
	}
}
