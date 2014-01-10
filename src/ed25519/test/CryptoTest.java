package ed25519.test;

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
//	public void testGetPublicKey() throws NoSuchAlgorithmException {
//		Crypto crypto = new Crypto();
//		byte[] input = {0};
//		byte[] someArray = "3b6a27bcceb6a42d62a3a8d02a6f0d73653215771de243a63ac048a18b59da29".getBytes();
//		assertEquals(someArray,crypto.getPublikKey(input));
//	}
	
}
