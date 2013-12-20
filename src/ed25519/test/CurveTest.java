package ed25519.test;

import java.math.BigInteger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ed25519.application.BigPoint;
import ed25519.application.Curve;

public class CurveTest {
	private Curve c;

	@BeforeClass
	protected void setUp() throws Exception {
		c = new Curve();
	}

	@Test(dependsOnGroups = {"expmod", "getxx"}, groups = {"invert"})
	public void testInvert1() {
		BigInteger expected = BigInteger.valueOf(1);
		Assert.assertEquals(c.invert(BigInteger.valueOf(1)), expected);
	}
	
	@Test(dependsOnGroups = {"expmod", "getxx"}, groups = {"invert"})
	public void testInvert5() {
		BigInteger expected = new BigInteger("11579208923731619542357098500868790785326998466564056403945758400791312963990");
		Assert.assertEquals(c.invert(BigInteger.valueOf(5)), expected);
	}
	
	@Test(dependsOnGroups = {"expmod"}, groups = {"getxx"})
	public void testgetXXfor0() {
		BigInteger expected = BigInteger.valueOf(-1);
		Assert.assertEquals(c.getXX(BigInteger.valueOf(0)), expected);
	}
	
 	@Test(dependsOnGroups = {"expmod"}, groups = {"getxx"})
	public void testgetXXfor1() {
		BigInteger expected = BigInteger.valueOf(0);
		Assert.assertEquals(c.getXX(BigInteger.valueOf(1)), expected);
	}
 	
 	@Test(dependsOnGroups = {"expmod"}, groups = {"getxx"})
	public void testgetXXfor2() {
		BigInteger expected = new BigInteger("159238947029881800007354471772647911766795077324544932017472991350137141588898");
		Assert.assertEquals(c.getXX(BigInteger.valueOf(2)), expected); 		
 	}

	@Test(dependsOnGroups = {"expmod", "getxx"}, groups = {"recoverx"})
	public void testXrecoverEven() {
		BigInteger expected = new BigInteger("19681161376707505956807079304988542015446066515923890162744021073123829784752");
		Assert.assertEquals(c.recoverX(BigInteger.valueOf(0)), expected);
	}
	
	@Test(dependsOnGroups = {"expmod", "getxx"}, groups = {"recoverx"})
	public void testXrecoverOdd() {
		BigInteger expected = new BigInteger("26193273134124080442446118532604303931175156347221147955160486408041496074798");
		Assert.assertEquals(c.recoverX(BigInteger.valueOf(4)), expected);
	}

	@Test(dependsOnGroups = {"bigpoint", "invert", "recoverx"}, groups = {"basepoint"})
	public void testGetBasePoint() {
		BigInteger x = new BigInteger("15112221349535400772501151409588531511454012693041857206046113283949847762202");
		BigInteger y = new BigInteger("46316835694926478169428394003475163141307993866256225615783033603165251855960");
		BigPoint expected = new BigPoint(x,y);
		BigPoint basePoint = c.getBasePoint();
		Assert.assertEquals(basePoint, expected);
	}
	
//	@Test
//	public void testgetD() {
//		
//	}
	
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
