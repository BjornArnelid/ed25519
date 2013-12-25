package ed25519.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ed25519.application.CryptoNumber;

public class CryptoNumberTest {
	
	@DataProvider(name="equals")
	public static Object[][] equalsProvider() {
		CryptoNumber stringFive = new CryptoNumber("5");
		CryptoNumber five = new CryptoNumber(5);
		CryptoNumber otherFive = new CryptoNumber(5);
		Object[][] data = {{five, otherFive}, {five, stringFive}};
		return data;
	}
	
	@Test(dataProvider = "equals")
	public void testEquals(CryptoNumber first, CryptoNumber second) {
		Assert.assertEquals(first, second);
	}
	
	@Test
	public void testNotEquals() {
		CryptoNumber first = new CryptoNumber(5);
		CryptoNumber second = new CryptoNumber(8);
		Assert.assertNotEquals(first, second);
	}
	
	@Test
	public void testPow() {
		CryptoNumber expected = new CryptoNumber(5);
		CryptoNumber n = new CryptoNumber(5);
		n.pow(1);
		Assert.assertEquals(n, expected);
	}
}
