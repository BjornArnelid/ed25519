package ed25519.application;

import ed25519.application.internal.ByteArray;


public class Key extends ByteArray {

	public Key(String input) {
		super(input.getBytes());
	}
	
	public Key(byte[] input) {
		super(input);
	}
	
	@Override
	public String toString() {
		return new String(value);
	}
}
