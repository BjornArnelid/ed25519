package ed25519.application.internal;

import java.util.Arrays;
import java.util.BitSet;

public class ByteArray {

	protected BitSet bytes;

	public ByteArray(String input) {
		this(input.getBytes());
	}
	
	public ByteArray(byte[] input) {
		bytes = BitSet.valueOf(input);
	}

	public ByteArray() {
		Constants c = Constants.getInstance();
		bytes = new BitSet(c.getb());
	}

	public byte[] getBytes() {
		return bytes.toByteArray();
	}


	public ByteArray getBytes(int from, int to) {
		byte[] result = Arrays.copyOfRange(bytes, from, to);
		return new ByteArray(result);
	}
	
	public byte getByte(int i) {
		return (byte)0;
	}	

	public boolean getBit(int position) {
		return bytes.get(position);
		//return bytes[position/8] >> (position%8) & 1;
	}
	
	public void setBit(int i, boolean b) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof ByteArray) {
			ByteArray otherKey = (ByteArray) other;
			return bytes.equals(otherKey.bytes);
		}
		return true;
	}
	
	@Override
	public String toString() {
		return getBytes().toString();
	}

	public ByteArray append(ByteArray other) {
		//bytes.
		int size = bytes.length + other.bytes;
		
		// TODO Auto-generated method stub
		return this;
	}
	
//	public ByteArray concatArrays(byte[] array1, byte[] array2) {
//	int size = array1.length + array2.length;
//	byte[] result = new byte[size];
//	for(int i=0;i<size;++i) {
//		if(i<array1.length) {
//			result[i] = array1[i];
//		}
//		else {
//			result[i] = array2[i-array1.length];
//		} 
//	}
//	return result;
//}
}