package ed25519.application;

public class CryptoNumber {

	private long value;

	public CryptoNumber(long input) {
		value = input;
	}
	
	public CryptoNumber(String input) {
		value = Long.parseLong(input);
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public boolean equals(Object aThat) {
		if(aThat instanceof CryptoNumber) {
			long other = ((CryptoNumber) aThat).getValue();
			if(value == other) {
				return true;
			}
		}
		return false;

	}

	protected long getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Long.valueOf(value).hashCode();
	}

	public void pow(int pow) {

	}
}
