package ed25519.application;

public class CryptoNumber {

	private long value;

	public CryptoNumber(long input) {
		value = input;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
