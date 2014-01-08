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
	
	@DataProvider(name="subtract")
	public static Object[][] subPrivider() {
		CryptoNumber one = new CryptoNumber(1);
		CryptoNumber two = new CryptoNumber(2);
		CryptoNumber three = new CryptoNumber(3);
		Object[][] data = {{three, two}, {4, one}};
		return data;
	}
	
	@Test(dataProvider="subtract")
	public void testSubtract(Object subtrahend, CryptoNumber expected) {
		CryptoNumber number = new CryptoNumber(5);
		if(subtrahend instanceof CryptoNumber) {
			subtrahend = (CryptoNumber) subtrahend;
			number.subtract((CryptoNumber)subtrahend);
		}
		else {
			number.subtract((Integer)subtrahend);
		}
		Assert.assertEquals(number, expected);
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
	

}
