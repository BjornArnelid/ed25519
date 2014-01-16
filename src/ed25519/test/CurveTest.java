package ed25519.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ed25519.application.BigPoint;
import ed25519.application.CryptoNumber;
import ed25519.application.Curve;

public class CurveTest {
	private Curve c;

	@BeforeClass
	protected void setUp() throws Exception {
		c = new Curve();
	}

	@Test(dependsOnMethods = {"ed25519.test.CryptoNumberTest.testInvert", "ed25519.test.BigPointTest.testgetPointfromY"}, dependsOnGroups = {"bigpoint", "basics"}, groups = {"basepoint"})
	public void testGetBasePoint() {
		CryptoNumber x = new CryptoNumber("15112221349535400772501151409588531511454012693041857206046113283949847762202");
		CryptoNumber y = new CryptoNumber("46316835694926478169428394003475163141307993866256225615783033603165251855960");
		BigPoint expected = new BigPoint(x,y);
		BigPoint basePoint = c.getBasePoint();
		Assert.assertEquals(basePoint, expected);
	}
	
	@DataProvider(name = "edwards")
	public static Object[][] edwardsProvider() {
		BigPoint zero = new BigPoint(new CryptoNumber(0), new CryptoNumber(0));
		BigPoint zeroOne = new BigPoint(new CryptoNumber(0), new CryptoNumber(1));
		BigPoint oneZero = new BigPoint(new CryptoNumber(1), new CryptoNumber(0));
		BigPoint one = new BigPoint(new CryptoNumber(1), new CryptoNumber(1));
		BigPoint result2 = new BigPoint(new CryptoNumber("243332"), new CryptoNumber("51380297829790456491237162401597246033761091082941378702394490295467508647106"));
		//BigPoint result3 = new BigPoint();
		Object[][] data = {{zero.copy(), zero.copy(), zero},
				{one.copy(), one.copy(),result2},
				{zero.copy(), one.copy(), zero},
				{one.copy(), zero.copy(), zero},
				{zeroOne.copy(), oneZero.copy(), oneZero}};
		return data;
	}
	
	@Test(dataProvider= "edwards", dependsOnGroups = {"bigpoint"}, dependsOnMethods = "ed25519.test.CryptoNumberTest.testInvert", groups = {"edward"})
	public void testEdwards(BigPoint first, BigPoint second, BigPoint expected) {
		BigPoint result = c.edwards(first, second);
		Assert.assertEquals(result, expected);
	}

//	@Test(dependsOnGroups = {"bigpoint"})
//	public void testScalarMult0() {
//		CryptoNumber expected = new BigPoint(new BigInteger("0"), new BigInteger("1"));
//		CryptoNumber result = c.scalarmult(new BigInteger("0"));
//		Assert.assertEquals(result, expected);
//	}
	
//	public void testScalarMult1() {
//		BigInteger x = new BigInteger("15112221349535400772501151409588531511454012693041857206046113283949847762202");
//		BigInteger y = new BigInteger("46316835694926478169428394003475163141307993866256225615783033603165251855960");
//		BigPoint expected = new BigPoint(x, y);
//		BigPoint result = c.scalarmult(new BigInteger("1"));
//		assertEquals(expected, result);
//	}
}
