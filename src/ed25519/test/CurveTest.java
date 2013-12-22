package ed25519.test;

import java.math.BigInteger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ed25519.application.BigPoint;
import ed25519.application.Curve;

public class CurveTest {
	private Curve c;

	@BeforeClass
	protected void setUp() throws Exception {
		c = new Curve();
	}

	@DataProvider(name = "invert")
	public static Object[][] invertProvider() {
		BigInteger one = BigInteger.valueOf(1);
		BigInteger five = BigInteger.valueOf(5);
		BigInteger large1 = new BigInteger("11579208923731619542357098500868790785326998466564056403945758400791312963990");
		Object[][] data = {{one, one}, {five, large1}};
		return data;
	}
	
	@Test(dataProvider = "invert", dependsOnMethods = {"ed25519.test.NumberUtilsTest.testExpmod"}, dependsOnGroups = {"getxx"}, groups = {"invert"})
	public void testInvert(BigInteger value, BigInteger expected) {
		Assert.assertEquals(c.invert(value), expected);
	}

	@DataProvider(name = "getxx")
	public static Object[][] getxxProvider() {
		BigInteger minusOne = BigInteger.valueOf(-1);
		BigInteger zero = BigInteger.valueOf(0);
		BigInteger one = BigInteger.valueOf(1);
		BigInteger two = BigInteger.valueOf(2);
		BigInteger large1 = new BigInteger("159238947029881800007354471772647911766795077324544932017472991350137141588898");
		Object[][] data = {{minusOne, zero}, {one, zero}, {two, large1}};
		return data;
	}
	
	@Test(dataProvider = "getxx", dependsOnMethods = "ed25519.test.NumberUtilsTest.testExpmod", groups = {"getxx"})
	public void testGetXX(BigInteger value, BigInteger expected) {
		Assert.assertEquals(c.getXX(value), expected);
	}

	@DataProvider(name = "xrecover")
	public static Object[][] xRecoverProvider() {
		BigInteger zero = BigInteger.valueOf(0);
		BigInteger four = BigInteger.valueOf(4);
		BigInteger large1 = new BigInteger("19681161376707505956807079304988542015446066515923890162744021073123829784752");
		BigInteger large2 = new BigInteger("26193273134124080442446118532604303931175156347221147955160486408041496074798");
		Object[][] data = {{zero, large1}, {four, large2}};
		return data;
	}
	
	@Test(dataProvider = "xrecover", dependsOnMethods = {"ed25519.test.NumberUtilsTest.testExpmod", "testGetXX"}, groups = {"recoverx"})
	public void testXRecover(BigInteger value, BigInteger expected) {
		Assert.assertEquals(c.recoverX(value), expected);
	}

	@Test(dependsOnGroups = {"bigpoint", "invert", "recoverx"}, groups = {"basepoint"})
	public void testGetBasePoint() {
		BigInteger x = new BigInteger("15112221349535400772501151409588531511454012693041857206046113283949847762202");
		BigInteger y = new BigInteger("46316835694926478169428394003475163141307993866256225615783033603165251855960");
		BigPoint expected = new BigPoint(x,y);
		BigPoint basePoint = c.getBasePoint();
		Assert.assertEquals(basePoint, expected);
	}
	
	@Test
	public void testgetD() {
		BigInteger expected = new BigInteger("-4513249062541557337682894930092624173785641285191125241628941591882900924598840740");
		Assert.assertEquals(c.getD(), expected);
	}
	
	@Test(dependsOnGroups = {"bigpoint"}, groups = {"edward"})
	public void testEdwards0and0() {
		BigPoint first = new BigPoint(new BigInteger("0"), new BigInteger("0"));
		BigPoint second = new BigPoint(new BigInteger("0"), new BigInteger("0"));
		BigPoint expected = new BigPoint(new BigInteger("0"), new BigInteger("0"));
		BigPoint result = c.edwards(first, second);
		Assert.assertEquals(result, expected);
	}
	
//	@Test(dependsOnGroups = {"bigpoint"}, groups = {"edward"})
//	public void testEdwards1and1() {
//		BigPoint first = new BigPoint(new BigInteger("1"), new BigInteger("1"));
//		BigPoint second = new BigPoint(new BigInteger("1"), new BigInteger("1"));
//		BigPoint expected = new BigPoint(new BigInteger("243332"), new BigInteger("51380297829790456491237162401597246033761091082941378702394490295467508647106"));
//		BigPoint result = c.edwards(first, second);
//		Assert.assertEquals(result, expected);
//	}

	@Test(dependsOnGroups = {"bigpoint"})
	public void testScalarMult0() {
		BigPoint expected = new BigPoint(new BigInteger("0"), new BigInteger("1"));
		BigPoint result = c.scalarmult(new BigInteger("0"));
		Assert.assertEquals(result, expected);
	}
	
//	public void testScalarMult1() {
//		BigInteger x = new BigInteger("15112221349535400772501151409588531511454012693041857206046113283949847762202");
//		BigInteger y = new BigInteger("46316835694926478169428394003475163141307993866256225615783033603165251855960");
//		BigPoint expected = new BigPoint(x, y);
//		BigPoint result = c.scalarmult(new BigInteger("1"));
//		assertEquals(expected, result);
//	}
}
