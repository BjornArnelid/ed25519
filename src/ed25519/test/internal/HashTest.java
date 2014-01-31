package ed25519.test.internal;

import javax.xml.bind.DatatypeConverter;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ed25519.application.Key;
import ed25519.application.internal.BitArray;
import ed25519.application.internal.Hash;

public class HashTest {
	
	private Hash sha512;
	private BitArray input;

	@BeforeMethod
	protected void setUp() throws Exception {
		sha512 = new Hash("SHA-512");
		input = new Key("0");
		sha512.digest(input);
	}
	
	@DataProvider(name="hashprovider")
	public static Object[][] hashProvider() {
		BitArray input1 = new BitArray("0");
		BitArray expected1 = new BitArray(DatatypeConverter.parseHexBinary("31bca02094eb78126a517b206a88c73cfa9ec6f704c7030d18212cace820f025f00bf0ea68dbf3f3a5436ca63b53bf7bf80ad8d5de7d8359d0b7fed9dbc3ab99"));
		BitArray input2 = new BitArray();
		BitArray expected2 = new BitArray(DatatypeConverter.parseHexBinary("cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e"));
		Object[][] data = {{input1,expected1}, {input2,expected2}};
		return data;
	}
	
	@Test(dataProvider="hashprovider", groups = {"hash"})
	public void testHashString(BitArray input, BitArray expected) {
		Assert.assertEquals(sha512.digest(input), expected);
	}
}