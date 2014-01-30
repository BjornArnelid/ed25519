package ed25519.application.internal;

import java.util.Arrays;

public class ByteArray {

	protected byte[] value;

	public ByteArray(byte[] input) {
		value = input;
	}

	public byte[] bytes() {
		return value;
	}

	@Override
	public boolean equals(Object other) {
		if(other instanceof ByteArray) {
			ByteArray otherKey = (ByteArray) other;
			return Arrays.equals(value, otherKey.value);
		}
		return true;
	}

}