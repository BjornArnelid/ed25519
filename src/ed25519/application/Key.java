package ed25519.application;

import ed25519.application.internal.ByteArray;


public class Key extends ByteArray {

	public Key(String input) {
		super(input);
	}
	
	public Key(byte[] input) {
		super(input);
	}
}
