package ed25519.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ed25519.application.Constants;
import ed25519.application.CryptoNumber;

public class CryptoNumberTest {
	
	private Constants c;

	@BeforeClass
	protected void setUp() throws Exception {
		c = new Constants();
	}
	
	@DataProvider(name="equals")
	public static Object[][] equalsProvider() {
		CryptoNumber stringFive = new CryptoNumber("5");
		CryptoNumber five = new CryptoNumber(5);
		CryptoNumber otherFive = new CryptoNumber(5);
		Object[][] data = {{five, otherFive}, {five, stringFive}};
		return data;
	}
	
	@Test(dataProvider = "equals", groups = {"basics"})
	public void testEquals(CryptoNumber first, CryptoNumber second) {
		Assert.assertEquals(first, second);
	}
	
	@Test(groups = {"basics"})
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
	
	@Test(dataProvider="subtract", groups = {"basics"})
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
	
	@Test(dataProvider="add", groups = {"basics"})
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
	
	@Test(dataProvider="div", groups = {"basics"})
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
	
	@Test(dataProvider="mult", groups = {"basics"})
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
	
	@Test(dataProvider="mod", groups = {"basics"})
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
	
	@Test(dataProvider="pow", groups = {"basics"})
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
	
	@Test(groups = {"basics"})
	public void testCopy() {
		CryptoNumber original = new CryptoNumber(0);
		CryptoNumber clone = original.copy();
		Assert.assertEquals(clone, original);
		clone.add(1);
		Assert.assertNotEquals(clone, original);
	}
	
	@DataProvider(name = "expmod")
	public static Object[][] expmodProvider() {
		Constants c = new Constants();
		CryptoNumber zero = new CryptoNumber(0);
		CryptoNumber one = new CryptoNumber(1);
		CryptoNumber two = new CryptoNumber(2);
		CryptoNumber three = new CryptoNumber(3);
		CryptoNumber four = new CryptoNumber(4);
		CryptoNumber twentyseven = new CryptoNumber(27);
		CryptoNumber halfq = c.getq().divide(2);
		CryptoNumber qplusone = c.getq().add(1);
		CryptoNumber large1 =new CryptoNumber("43422033463993573283839119378257965444976244249615211514796594002967423614962");
		CryptoNumber large2 = new CryptoNumber("36185027886661311069865932815214971204146870208012676262330495002472853012468");
		CryptoNumber large3 = new CryptoNumber("39803530675327442176852526096736468324561557228813943888563544502720138313715");
		Object[][] data = new Object[][] {{one, zero, one}, {one, one, one},
				{two, one, two}, {qplusone, one, one}, {one, two, one},
				{two, two, four}, {halfq, two, large1},
				{three, three, twentyseven}, {halfq, three, large2},
				{halfq, four, large3}};
		return data;
	}
	
	@Test(dataProvider = "expmod", dependsOnMethods = {"ed25519.test.ConstantsTest.testBigPrime"}, dependsOnGroups = "basics")
	public void testExpmod(CryptoNumber number, CryptoNumber expModulus, CryptoNumber expected) {
		CryptoNumber actual = number.copy();
		Assert.assertEquals(actual.expmod(expModulus, c.getq()), expected);
		Assert.assertEquals(actual, expected);
	}
}
