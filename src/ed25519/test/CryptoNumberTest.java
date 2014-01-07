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
	
	@DataProvider(name="pow")
	public static Object[][] powProvider() {
		CryptoNumber one = new CryptoNumber(1);
		CryptoNumber two = new CryptoNumber(2);
		CryptoNumber large1 = new CryptoNumber(254);
		CryptoNumber large2 = new CryptoNumber("28948022309329048855892746252171976963317496166410141009864396001978282409984");
		Object[][] data = {{one, two}, {large1, large2}};
		return data;
	}
	@Test(dataProvider="pow")
	public void testPow(CryptoNumber exponent, CryptoNumber expected) {
		CryptoNumber base = new CryptoNumber(2);
		base.pow(exponent);
		Assert.assertEquals(base, expected);
	}
	
	@Test
	public void testSubtract() {
		CryptoNumber minuend = new CryptoNumber(2);
		CryptoNumber subtrahend = new CryptoNumber(1);
		minuend.subtract(subtrahend);
		CryptoNumber expected = new CryptoNumber(1);
		Assert.assertEquals(minuend, expected);
	}
}
