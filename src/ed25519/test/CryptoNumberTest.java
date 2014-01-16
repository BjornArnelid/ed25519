package ed25519.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ed25519.application.Constants;
import ed25519.application.CryptoNumber;

public class CryptoNumberTest {

	@BeforeClass
	protected void setUp() throws Exception {
	}
	
	@DataProvider(name="equals")
	public static Object[][] equalsProvider() {
		CryptoNumber zero = new CryptoNumber(0);
		CryptoNumber stringFive = new CryptoNumber("5");
		CryptoNumber five = new CryptoNumber(5);
		CryptoNumber otherFive = new CryptoNumber(5);
		Object[][] data = {{five, otherFive}, {five, stringFive}, {zero, zero.copy()}, {zero, five.copy().subtract(5)}};
		return data;
	}
	
	@Test(dataProvider = "equals", groups = {"basics"})
	public void testEquals(CryptoNumber first, CryptoNumber second) {
		Assert.assertEquals(first, second);
	}
	
	@DataProvider(name="noequals")
	public static Object[][] noEqualsProvider() {
		CryptoNumber five = new CryptoNumber(5);
		CryptoNumber eight = new CryptoNumber(8);
		CryptoNumber eightPlusOne = eight.copy().add(1);
		Object[][] data = {{five, eight}, {eight, eightPlusOne}};
		return data;
	}
	@Test(dataProvider = "noequals", groups = {"basics"})
	public void testNotEquals(CryptoNumber first, CryptoNumber second) {
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
		CryptoNumber zero = new CryptoNumber(0);
		CryptoNumber four = new CryptoNumber(4);
		CryptoNumber five = new CryptoNumber(5);
		CryptoNumber ten = new CryptoNumber(10);
		CryptoNumber twenty = new CryptoNumber(20);
		Object[][] data = {{twenty.copy(), five, four}, {twenty.copy(), 2, ten},
				{zero.copy(), 2, zero}};
		return data;
	}
	
	@Test(dataProvider="div", groups = {"basics"})
	public void testDivision(CryptoNumber number, Object divisor, CryptoNumber expected) {
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
		CryptoNumber zero = new CryptoNumber(0);
		CryptoNumber one = new CryptoNumber(1);
		CryptoNumber two = new CryptoNumber(2);
		CryptoNumber three = new CryptoNumber(3);
		CryptoNumber five = new CryptoNumber(5);
		CryptoNumber large = new CryptoNumber("19681161376707505956807079304988542015446066515923890162744021073123829784752");
		Object[][] data = {{five.copy(), three, two}, {five.copy(), 4, one}, {large, two, zero}};
		return data;
	}
	
	@Test(dataProvider="mod", groups = {"basics"})
	public void testModulus(CryptoNumber number, Object modulus, CryptoNumber expected) {
		if(modulus instanceof CryptoNumber) {
			Assert.assertEquals(number.mod((CryptoNumber)modulus), expected);
		}
		else {
			Assert.assertEquals(number.mod((Integer)modulus), expected);
		}
		Assert.assertEquals(number, expected);
	}
	
	@DataProvider(name="modQ")
	public static Object[][] modQPrivider() {
		Constants c = Constants.getInstance();
		CryptoNumber one = new CryptoNumber(1);
		CryptoNumber hundred = new CryptoNumber(100);
		CryptoNumber qPlusOne = c.getq().add(1);
		CryptoNumber qPlusHundred = c.getq().add(100);
		Object[][] data = {{one.copy(), one}, {qPlusOne, one}, {qPlusHundred, hundred}};
		return data;
	}
	
	@Test(dataProvider="modQ", groups = {"basics"})
	public void testModulusQ(CryptoNumber number, CryptoNumber expected) {
		Assert.assertEquals(number.modQ(), expected);
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
		Constants c = Constants.getInstance();
		CryptoNumber minusOne = new CryptoNumber(-1);
		CryptoNumber zero = new CryptoNumber(0);
		CryptoNumber one = new CryptoNumber(1);
		CryptoNumber two = new CryptoNumber(2);
		CryptoNumber three = new CryptoNumber(3);
		CryptoNumber four = new CryptoNumber(4);
		CryptoNumber twentyseven = new CryptoNumber(27);
		CryptoNumber qPart = (c.getq().add(3)).divide(8);
		CryptoNumber halfq = c.getq().divide(2);
		CryptoNumber qplusone = c.getq().add(1);
		CryptoNumber large1 =new CryptoNumber("43422033463993573283839119378257965444976244249615211514796594002967423614962");
		CryptoNumber large2 = new CryptoNumber("36185027886661311069865932815214971204146870208012676262330495002472853012468");
		CryptoNumber large3 = new CryptoNumber("39803530675327442176852526096736468324561557228813943888563544502720138313715");
		Object[][] data = new Object[][] {{minusOne, qPart, one}, {one, zero, one}, {one, one, one},
				{two, one, two}, {qplusone, one, one}, {one, two, one},
				{two, two, four}, {halfq, two, large1},
				{three, three, twentyseven}, {halfq, three, large2},
				{halfq, four, large3}};
		return data;
	}
	
	@Test(dataProvider = "expmod", dependsOnMethods = {"ed25519.test.ConstantsTest.testGetQ"}, dependsOnGroups = "basics")
	public void testExpmod(CryptoNumber number, CryptoNumber expModulus, CryptoNumber expected) {
		CryptoNumber actual = number.copy();
		Assert.assertEquals(actual.expmod(expModulus), expected);
		Assert.assertEquals(actual, expected);
	}
	
	@DataProvider(name = "invert")
	public static Object[][] invertProvider() {
		CryptoNumber one = new CryptoNumber(1);
		CryptoNumber five = new CryptoNumber(5);
		CryptoNumber large1 = new CryptoNumber("11579208923731619542357098500868790785326998466564056403945758400791312963990");
		Object[][] data = {{one, one}, {five, large1}};
		return data;
	}
	
	@Test(dataProvider = "invert", dependsOnMethods = {"ed25519.test.ConstantsTest.testGetQ", "testExpmod"}, dependsOnGroups = {"basics"})
	public void testInvert(CryptoNumber value, CryptoNumber expected) {
		Assert.assertEquals(value.invert(), expected);
	}
	
	@DataProvider(name = "square")
	public static Object[][] squareProvider() {
		Constants c = Constants.getInstance();
		CryptoNumber one = new CryptoNumber(1);
		CryptoNumber three = new CryptoNumber(3);
		CryptoNumber nine = new CryptoNumber(9);
		CryptoNumber q = c.getq();
		CryptoNumber large = new CryptoNumber("3351951982485649274893506249551461531869841455148098344430890360930441007516186694504959566828678008207342894297409383004791299986236948390458062788362601");
		Object[][] data = {{one, one}, {three, nine}, {q, large}};
		return data;
	}
	
	@Test(dataProvider = "square", groups = {"basics"})
	public void testSquare(CryptoNumber number, CryptoNumber expected) {
		Assert.assertEquals(number.square(), expected);
	}
	
	@Test(groups = "testbit")
	public void testTestBitTrue() {
		CryptoNumber number = new CryptoNumber(1);
		Assert.assertTrue(number.testBit(0));
	}
	
	@Test(groups = "testbit")
	public void testTestBitFalse() {
		CryptoNumber number = new CryptoNumber(0);
		Assert.assertFalse(number.testBit(0));
	}
}
