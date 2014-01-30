package ed25519.application;

import ed25519.application.internal.ByteArray;

public class Message extends ByteArray {

	public Message(String input) {
		super(input);
	}

	public Message(byte[] input) {
		super(input);
	}

}
