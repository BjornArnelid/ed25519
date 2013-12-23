package ed25519.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import ed25519.application.CryptoNumber;

public class CryptoNumberTest {

	@Test
	public void testSetFromlong() {
		CryptoNumber n = new CryptoNumber(5);
		Assert.assertEquals(n.toString(), "5");
	}
}
