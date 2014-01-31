package ed25519.test.internal;

import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ed25519.application.internal.BigPoint;
import ed25519.application.internal.BitArray;
import ed25519.application.internal.Constants;
import ed25519.application.internal.CryptoNumber;
import ed25519.application.internal.Curve;

public class BitArrayTest {
	
	private BitArray array;

	@BeforeMethod
	protected void setUp() {
		array = new BitArray(DatatypeConverter.parseHexBinary("31bca02094eb78126a517b206a88c73cfa9ec6f704c7030d18212cace820f025f00bf0ea68dbf3f3a5436ca63b53bf7bf80ad8d5de7d8359d0b7fed9dbc3ab99"));
	}
	
	@Test(groups="byte")
	public void testCreateEmpty() {
		BitArray a = new BitArray();
		byte[] expected = new byte[0];
		Assert.assertEquals(a.getBytes(), expected);
	}
	
	@Test(groups="byte")
	public void testCreateArrayFromString() {
		BitArray a = new BitArray("Value");
		Assert.assertEquals(a.toString(), "Value");
	}
	
	@Test(groups="byte")
	public void testArrayEquals() {
		BitArray k1 = new BitArray("Content".getBytes());
		BitArray k2 = new BitArray("Content".getBytes());
		Assert.assertEquals(k1, k2);
	}
	
	@Test(groups="byte")
	public void testArrayNotEquals() {
		BitArray k1 = new BitArray("Content".getBytes());
		BitArray k2 = new BitArray("Other".getBytes());
		Assert.assertNotEquals(k1, k2);
	}

	@DataProvider(name="getbits")
	public static Object[][] getBitsProvider() {
		Constants c = Constants.getInstance();
		int b = c.getb();
		byte[] byte1 = {(byte)0x31};
		BitArray expected1 = new BitArray(byte1);
		BitArray expected2 = new BitArray(DatatypeConverter.parseHexBinary("f00bf0ea68dbf3f3a5436ca63b53bf7bf80ad8d5de7d8359d0b7fed9dbc3ab99"));
		Object[][] data = {{0,8,expected1},{b/2,b,expected2}};
		return data;
	}
	
	@Test(dataProvider="getbits", groups="byte")
	public void testGetBits(int from, int to, BitArray expected) {
		Assert.assertEquals(array.getBits(from, to).getBytes(),  expected.getBytes());
	}
	
	@Test(groups="byte")
	public void testSetBit() {
		array.setBit(1,true);
		Assert.assertEquals(array.getBit(1), true);
	}
	
	@DataProvider(name="getbit")
	public static Object[][] getbitProvider() {
		Object[][] data = {{0,true},{8,false},{10,true}};
		return data;
	}
		
	@Test(dataProvider = "getbit", groups = {"byte"})
	public void testGetBit(int n, boolean expected) {
		Assert.assertEquals(array.getBit(n), expected);
	}
	
	@Test(groups="byte")
	public void testAppend() {
		BitArray a = new BitArray("Hello");
		BitArray b = new BitArray("World");
		BitArray expected = new BitArray("HelloWorld");
		Assert.assertEquals(a.append(b), expected);
		Assert.assertEquals(a, expected);
	}
	
	@DataProvider(name = "frompoint")
	public static Object[][] encodePointProvider() throws NoSuchAlgorithmException {
		Curve c = new Curve();
		BigPoint input1 = new BigPoint(new CryptoNumber(0), new CryptoNumber(0));
		BigPoint input2 = new BigPoint(new CryptoNumber(1), new CryptoNumber(0));
		BigPoint input3 = new BigPoint(new CryptoNumber(0), new CryptoNumber(1));
		BigPoint input4 = new BigPoint(new CryptoNumber(0), new CryptoNumber(128));
		BigPoint input5 = new BigPoint(new CryptoNumber(0), new CryptoNumber(1032));
		BigPoint input6 = c.getBasePoint();
		BitArray expected1 = new BitArray();
		byte[] array2 = new byte[32];
		array2[31] |= 1 << 7;
		BitArray expected2 = new BitArray(array2);
		byte[] array3 = new byte[32];
		array3[0] |= 1 << 0;
		BitArray expected3 = new BitArray(array3);
		byte[] array4 = new byte[32];
		array4[0] |= 1 << 7;
		BitArray expected4 = new BitArray(array4);
		byte[] array5 = new byte[32];
		array5[0] |= 1 << 3;
		array5[1] |= 1 << 2;
		BitArray expected5 = new BitArray(array5);
		BitArray expected6 = new BitArray(DatatypeConverter.parseHexBinary("5866666666666666666666666666666666666666666666666666666666666666"));
		Object[][] data = {{input1,expected1}, {input2,expected2},
				{input3,expected3}, {input4,expected4}, {input5,expected5},
				{input6,expected6}};
		return data;
	}
	
	@Test(dataProvider= "frompoint", groups="byte", dependsOnGroups="constants")
	public void testFromPoint(BigPoint input, BitArray expected) {
		BitArray actual = new BitArray(input);
		Assert.assertEquals(actual, expected);
	}
	
	@DataProvider(name="fromnum")
	public static Object[][] encodeNumProvider() {
		CryptoNumber input1 = new CryptoNumber(0);
		CryptoNumber input2 = new CryptoNumber(1);
		CryptoNumber input3 = Constants.getInstance().getq();
		BitArray expected1 = new BitArray();
		byte[] array2 = new byte[32];
		array2[0] |= 1 << 0;
		BitArray expected2 = new BitArray(array2);
		BitArray expected3 = new BitArray(DatatypeConverter.parseHexBinary("edffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff7f"));
		Object[][] data = {{input1,expected1}, {input2,expected2}, {input3,expected3}};
		return data;
	}
	
	@Test(dataProvider="fromnum", groups="byte")
	public void testFromNum(CryptoNumber input, BitArray expected) {
		BitArray actual = new BitArray(input);
		Assert.assertEquals(actual, expected);
	}
}
