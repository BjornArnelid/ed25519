package ed25519.test;

import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ed25519.application.Ed25519;


public class Ed25519Test {

	private Ed25519 crypto;

	@BeforeClass
	protected void setUp() throws Exception {
		crypto = new Ed25519();
	}
	
	@Test(dependsOnGroups= "constants")
	public void testCreateCrypto() throws NoSuchAlgorithmException {
		Assert.assertNotNull(crypto);
	}
	
	@DataProvider(name = "sign")
	public static Object[][] publickeyProvider() {
		byte[] sk1 = {};
		byte[] pk1 = DatatypeConverter.parseHexBinary("e0b1fe74117e1b95b608a4f221df314774b20ea66842350d515371c7c6966c6e");
		byte[] s1 = DatatypeConverter.parseHexBinary("513ab2383c400eada62f2a639382dfd7a521cc2e7e1e90cdc51640427b239f5ffea8ca2079b2ffc5316a704fc1052f0806c30e6e4ce7f408541b65f7d144130a");
		byte[] sk2 = "HelloWorld".getBytes();
		byte[] pk2 = DatatypeConverter.parseHexBinary("30d1065ee6bb57a23c3309e83bf0d1fc3bd713ea61d2e76d19932b7adb8c83af");
		byte[] s2 = DatatypeConverter.parseHexBinary("ab653994b2d58550ca2c14c892a8ea4f951b7a67549233dbd4369a58675f7f872adc0584e9834998f4087355e4a7afd92360b165b19394bf6e74448888c11909");
		byte[] sk3 = DatatypeConverter.parseHexBinary("9d61b19deffd5a60ba844af492ec2cc44449c5697b326919703bac031cae7f60");
		byte[] pk3 = DatatypeConverter.parseHexBinary("d75a980182b10ab7d54bfed3c964073a0ee172f3daa62325af021a68f707511a");
		byte[] s3 = DatatypeConverter.parseHexBinary("8d25f311e3eb9047edf7e8185520a9981eef9557a1efba778c6c9daa3a0a37cf9fe308bc1a9a84e54ffe74a965693dfd8ab555e8c6f702f6011f8f7a833f390f");
		Object[][] data = {{sk1,pk1,s1}, {sk2,pk2,s2}, {sk3,pk3,s3}};
		return data;
	}
	
	@Test(dataProvider = "sign", dependsOnGroups= {"basics","bigpoint","hash"}, dependsOnMethods = {"ed25519.test.CurveTest.testScalarMult","ed25519.test.CurveTest.testEncodePoint","ed25519.test.ConstantsTest.testGetBitLength"})
	public void testGetPublicKey(byte[] sk, byte[] pk, byte[] s) throws NoSuchAlgorithmException {
		Assert.assertEquals(crypto.getPublikKey(sk), pk);
	}
	
	@Test(dataProvider = "sign", dependsOnGroups = {"hash","basics"}, dependsOnMethods = {"ed25519.test.ConstantsTest.testGetBitLength","ed25519.test.HashTest.testGetBytes"})
	public void testSign(byte[] sk, byte[] pk, byte[] s) throws NoSuchAlgorithmException {
		byte[] m = "Hello World".getBytes();
		Assert.assertEquals(crypto.sign(m, sk, pk), s);
	}
	
}
