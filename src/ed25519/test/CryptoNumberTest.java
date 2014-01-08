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
			Assert.assertEquals(number.subtract((CryptoNumber)subtrahend), expected);
		}
		else {
			Assert.assertEquals(number.subtract((Integer)subtrahend), expected);
		}
		Assert.assertEquals(number, expected);
		
	}
	
	@DataProvider(name="add")
	public static Object[][] addPrivider() {
		CryptoNumber three = new CryptoNumber(3);
		CryptoNumber eight = new CryptoNumber(8);
		CryptoNumber nine = new CryptoNumber(9);

		Object[][] data = {{three, eight}, {4, nine}};
		return data;
	}
	
	@Test(dataProvider="add")
	public void testAdd(Object addend, CryptoNumber expected) {
		CryptoNumber number = new CryptoNumber(5);
		if(addend instanceof CryptoNumber) {
			Assert.assertEquals(number.add((CryptoNumber)addend), expected);
		}
		else {
			Assert.assertEquals(number.add((Integer)addend), expected);
		}
		Assert.assertEquals(number, expected);
	}
	
	@DataProvider(name="div")
	public static Object[][] divPrivider() {
		CryptoNumber four = new CryptoNumber(4);
		CryptoNumber five = new CryptoNumber(5);
		CryptoNumber ten = new CryptoNumber(10);

		Object[][] data = {{five, four}, {2, ten}};
		return data;
	}
	
	@Test(dataProvider="div")
	public void testDivision(Object divisor, CryptoNumber expected) {
		CryptoNumber number = new CryptoNumber(20);
		if(divisor instanceof CryptoNumber) {
			Assert.assertEquals(number.divide((CryptoNumber)divisor), expected);
		}
		else {
			Assert.assertEquals(number.divide((Integer)divisor), expected);
		}
		Assert.assertEquals(number, expected);
	}
	
	@DataProvider(name="mult")
	public static Object[][] multPrivider() {
		CryptoNumber three = new CryptoNumber(3);
		CryptoNumber fifteen = new CryptoNumber(15);
		CryptoNumber twenty = new CryptoNumber(20);

		Object[][] data = {{three, fifteen}, {4, twenty}};
		return data;
	}
	
	@Test(dataProvider="mult")
	public void testMultiplication(Object multiplier, CryptoNumber expected) {
		CryptoNumber number = new CryptoNumber(5);
		if(multiplier instanceof CryptoNumber) {
			Assert.assertEquals(number.multiply((CryptoNumber)multiplier), expected);
		}
		else {
			Assert.assertEquals(number.multiply((Integer)multiplier), expected);
		}
		Assert.assertEquals(number, expected);
	}
	
	@DataProvider(name="mod")
	public static Object[][] modPrivider() {
		CryptoNumber one = new CryptoNumber(1);
		CryptoNumber two = new CryptoNumber(2);
		CryptoNumber three = new CryptoNumber(3);
		Object[][] data = {{three, two}, {4, one}};
		return data;
	}
	
	@Test(dataProvider="mod")
	public void testModulus(Object modulus, CryptoNumber expected) {
		CryptoNumber number = new CryptoNumber(5);
		if(modulus instanceof CryptoNumber) {
			Assert.assertEquals(number.mod((CryptoNumber)modulus), expected);
		}
		else {
			Assert.assertEquals(number.mod((Integer)modulus), expected);
		}
		Assert.assertEquals(number, expected);
	}
	
	@DataProvider(name="pow")
	public static Object[][] powProvider() {
		CryptoNumber one = new CryptoNumber(1);
		CryptoNumber two = new CryptoNumber(2);
		CryptoNumber large1 = new CryptoNumber(254);
		CryptoNumber large2 = new CryptoNumber(65536);
		CryptoNumber large3 = new CryptoNumber("28948022309329048855892746252171976963317496166410141009864396001978282409984");
		Object[][] data = {{one, two}, {large1, large3}, {16, large2}};
		return data;
	}
	
	@Test(dataProvider="pow")
	public void testPow(Object exponent, CryptoNumber expected) {
		CryptoNumber number = new CryptoNumber(2);
		if(exponent instanceof CryptoNumber) {
			Assert.assertEquals(number.pow((CryptoNumber)exponent), expected);
		}
		else {
			Assert.assertEquals(number.pow((Integer)exponent), expected);
		}
		Assert.assertEquals(number, expected);
	}
}
