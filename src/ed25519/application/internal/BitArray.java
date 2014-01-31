package ed25519.application.internal;

import java.util.BitSet;

public class BitArray {

	protected BitSet bytes;
	private Constants c;

	public BitArray() {
		c = Constants.getInstance();
		bytes = new BitSet();
	}
	
	public BitArray(String input) {
		this(input.getBytes());
	}
	
	public BitArray(byte[] input) {
		this();
		bytes = BitSet.valueOf(input);
	}

	public BitArray(BigPoint point) {
		this();
		encodeRange(point.getY());
		if(point.getX().testBit(0)) {
			setBit(255, true);
		}
	}
	
	public BitArray(CryptoNumber number) {
		this();
		encodeRange(number);
		if(number.testBit(c.getb())) {
			setBit(255, true);
		}
	}

	private void encodeRange(CryptoNumber number) {
		
		for(int i=0; i<(c.getb()-1);++i) {
			if(number.testBit(i)) {
				setBit(i,true);
			}
		}
	}

	public byte[] getBytes() {
		return bytes.toByteArray();
	}


	public BitArray getBits(int from, int to) {
		BitArray returnArray = new BitArray();
		returnArray.bytes = bytes.get(from, to);
		return returnArray;
	}	

	public boolean getBit(int position) {
		return bytes.get(position);
	}
	
	public void setBit(int index, boolean isSet) {
		bytes.set(index, isSet);
		
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof BitArray) {
			BitArray otherKey = (BitArray) other;
			return bytes.equals(otherKey.bytes);
		}
		return true;
	}
	
	@Override
	public String toString() {
		return new String(getBytes());
	}

	public BitArray append(BitArray other) {
		int thisSize = bytes.length() + (8-bytes.length()%8);
		int otherSize = other.bytes.length() + (8-other.bytes.length()%8);
		int totalSize = thisSize + otherSize;
		for(int i=thisSize; i<totalSize; ++i) {
			setBit(i, other.getBit(i-thisSize));
		}
		return this;
	}
}