package ed25519.test.application;

import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ed25519.application.Ed25519;
import ed25519.application.Key;
import ed25519.application.Message;


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
	public static Object[][] publicKeyProvider() {
		Key sk1 = new Key();
		Key pk1 = new Key(DatatypeConverter.parseHexBinary("e0b1fe74117e1b95b608a4f221df314774b20ea66842350d515371c7c6966c6e"));
		Key s1 = new Key(DatatypeConverter.parseHexBinary("513ab2383c400eada62f2a639382dfd7a521cc2e7e1e90cdc51640427b239f5ffea8ca2079b2ffc5316a704fc1052f0806c30e6e4ce7f408541b65f7d144130a"));
		Key sk2 = new Key("HelloWorld");
		Key pk2 = new Key(DatatypeConverter.parseHexBinary("30d1065ee6bb57a23c3309e83bf0d1fc3bd713ea61d2e76d19932b7adb8c83af"));
		Key s2 = new Key(DatatypeConverter.parseHexBinary("ab653994b2d58550ca2c14c892a8ea4f951b7a67549233dbd4369a58675f7f872adc0584e9834998f4087355e4a7afd92360b165b19394bf6e74448888c11909"));
		Key sk3 = new Key(DatatypeConverter.parseHexBinary("9d61b19deffd5a60ba844af492ec2cc44449c5697b326919703bac031cae7f60"));
		Key pk3 = new Key(DatatypeConverter.parseHexBinary("d75a980182b10ab7d54bfed3c964073a0ee172f3daa62325af021a68f707511a"));
		Key s3 = new Key(DatatypeConverter.parseHexBinary("8d25f311e3eb9047edf7e8185520a9981eef9557a1efba778c6c9daa3a0a37cf9fe308bc1a9a84e54ffe74a965693dfd8ab555e8c6f702f6011f8f7a833f390f"));
		Object[][] data = {{sk1,pk1,s1}, {sk2,pk2,s2}, {sk3,pk3,s3}};
		return data;
	}
	
	@Test(dataProvider = "sign", dependsOnGroups= {"basics","bigpoint","hash","byte"}, dependsOnMethods = {"ed25519.test.internal.CurveTest.testScalarMult","ed25519.test.internal.ConstantsTest.testGetBitLength"})
	public void testGetPublicKey(Key sk, Key pk, Key s) throws NoSuchAlgorithmException {
		Assert.assertEquals(crypto.getPublikKey(sk), pk);
	}
	
	@Test(dataProvider = "sign", dependsOnGroups = {"basics","bigpoint","hash","byte"}, dependsOnMethods = {"ed25519.test.internal.ConstantsTest.testGetBitLength","ed25519.test.internal.ConstantsTest.testGetL","ed25519.test.internal.CurveTest.testHint"})
	public void testSign(Key sk, Key pk, Key s) throws NoSuchAlgorithmException {
		Message m = new Message("Hello World");
		Key actual = crypto.sign(m, sk, pk);
		System.out.println(DatatypeConverter.printHexBinary(actual.getBytes()));
		Assert.assertEquals(actual, s);
	}
}
