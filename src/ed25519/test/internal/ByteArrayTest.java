package ed25519.test.internal;

import javax.xml.bind.DatatypeConverter;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ed25519.application.internal.ByteArray;

public class ByteArrayTest {
	
	private ByteArray array;

	@BeforeMethod
	protected void setUp() {
		array = new ByteArray(DatatypeConverter.parseHexBinary("31bca02094eb78126a517b206a88c73cfa9ec6f704c7030d18212cace820f025f00bf0ea68dbf3f3a5436ca63b53bf7bf80ad8d5de7d8359d0b7fed9dbc3ab99"));
	}
	
	@Test(groups="byte")
	public void testCreateEmpty() {
		ByteArray a = new ByteArray();
		Assert.assertEquals(a.getBytes(), new Byte[32]);
	}
	
	@Test(groups="byte")
	public void testCreateArrayFromString() {
		ByteArray a = new ByteArray("Value");
		Assert.assertEquals(a.toString(), "Value");
	}
	
	@Test(groups="byte")
	public void testArrayEquals() {
		ByteArray k1 = new ByteArray("Content".getBytes());
		ByteArray k2 = new ByteArray("Content".getBytes());
		Assert.assertEquals(k1, k2);
	}
	
	@Test(groups="byte")
	public void testArrayNotEquals() {
		ByteArray k1 = new ByteArray("Content".getBytes());
		ByteArray k2 = new ByteArray("Other".getBytes());
		Assert.assertNotEquals(k1, k2);
	}

	@Test(groups="byte")
	public void testGetBytes() {
		int from = 0; 
		int to = 1;
		byte[] b = {(byte)0x31};
		ByteArray expected = new ByteArray(b);
		Assert.assertEquals(array.getBytes(from, to),  expected);
	}
	
	@Test(groups="byte")
	public void testGetByte() {
		byte expected = (byte)0x31;
		Assert.assertEquals(array.getByte(0), expected);
	}
	
	@Test(groups="byte")
	public void testSetBit() {
		array.setBit(1,false);
		Assert.assertEquals(array.getBit(1), false);
	}
	
	@DataProvider(name="getbit")
	public static Object[][] getbitProvider() {
		Object[][] data = {{0,1},{8,0},{10,1}};
		return data;
	}
		
	@Test(dataProvider = "getbit", groups = {"byte"})
	public void testGetBitInHash(int n, int expected) {
		Assert.assertEquals(array.getBit(n), expected);
	}
	
	@Test(groups="byte")
	public void testAppend() {
		ByteArray a = new ByteArray("Hello");
		ByteArray b = new ByteArray("World");
		ByteArray expected = new ByteArray("HelloWorld");
		Assert.assertEquals(a.append(b), expected);
		Assert.assertEquals(a, expected);
		
	}
}
