package ed25519.application;

public class Key {

	private byte[] value;
	
	public Key(String input) {
		value = input.getBytes();
	}
	
	public Key(byte[] input) {
		value = input;
	}

	@Override
	public String toString() {
		return new String(value);
	}

	public byte[] bytes() {
		return value;
	}
}
