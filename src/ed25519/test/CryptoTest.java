package ed25519.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.security.NoSuchAlgorithmException;

import ed25519.application.Crypto;


public class CryptoTest {

	@Test(dependsOnGroups= "constants")
	public void testCreateCrypto() throws NoSuchAlgorithmException {
		Crypto crypto = new Crypto();
		Assert.assertNotNull(crypto);
	}
	
	@DataProvider(name = "publickey")
	public static Object[][] publickeyProvider() {
		byte[] input1 = {};
		byte[] input2 = {0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x57, 0x6f, 0x72, 0x6c, 0x64};
		byte[] expected1 = {(byte) 0xe0, (byte) 0xb1, (byte) 0xfe, 0x74, 0x11, 0x7e, 0x1b, (byte) 0x95, (byte) 0xb6, 0x8, (byte) 0xa4, (byte) 0xf2, 0x21, (byte) 0xdf, 0x31, 0x47, 0x74, (byte) 0xb2, 0xe, (byte) 0xa6, 0x68, 0x42, 0x35, 0xd, 0x51, 0x53, 0x71, (byte) 0xc7, (byte) 0xc6, (byte) 0x96, 0x6c, 0x6e};
		byte[] expected2 = {0x30, (byte) 0xd1, 0x6, 0x5e, (byte) 0xe6, (byte) 0xbb, 0x57, (byte) 0xa2, 0x3c, 0x33, 0x9, (byte) 0xe8, 0x3b, (byte) 0xf0, (byte) 0xd1, (byte) 0xfc, 0x3b, (byte) 0xd7, 0x13, (byte) 0xea, 0x61, (byte) 0xd2, (byte) 0xe7, 0x6d, 0x19, (byte) 0x93, 0x2b, 0x7a, (byte) 0xdb, (byte) 0x8c, (byte) 0x83, (byte) 0xaf};
		Object[][] data = {{input1,expected1}, {input2,expected2}};
		return data;
	}
	
	@Test(dataProvider = "publickey", dependsOnGroups= {"basics", "bigpoint"}, dependsOnMethods = {"ed25519.test.CurveTest.testScalarMult","ed25519.test.CurveTest.testEncodePoint"})
	public void testGetPublicKey(byte[] input, byte[] expected) throws NoSuchAlgorithmException {
		Crypto crypto = new Crypto();
		Assert.assertEquals(crypto.getPublikKey(input), expected);
	}
	
}
