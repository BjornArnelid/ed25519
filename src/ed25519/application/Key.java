package ed25519.application;

import ed25519.application.internal.BigPoint;
import ed25519.application.internal.BitArray;


public class Key extends BitArray {

	public Key(String input) {
		super(input);
	}
	
	public Key(byte[] input) {
		super(input);
	}

	public Key(BigPoint input) {
		super(input);
	}

	public Key() {
		super();
	}
}
