package ed25519.application;

import ed25519.application.internal.BitArray;

public class Message extends BitArray {

	public Message(String input) {
		super(input);
	}

	public Message(byte[] input) {
		super(input);
	}

}
