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
	
//	@Test(dependsOnMethods = {"ed25519.test.CryptoNumberTest.testInvert", "ed25519.test.ConstantsTest.testGetQ"})
//	public void testgetD() {
//		CryptoNumber expected = new CryptoNumber("-4513249062541557337682894930092624173785641285191125241628941591882900924598840740");
//		Assert.assertEquals(c.getD(), expected);
//	}
	
//	@Test(dependsOnGroups = {"bigpoint"}, groups = {"edward"})
//	public void testEdwards0and0() {
//		BigPoint first = new BigPoint(new CryptoNumber("0"), new CryptoNumber("0"));
//		BigPoint second = new BigPoint(new CryptoNumber("0"), new CryptoNumber("0"));
//		BigPoint expected = new BigPoint(new CryptoNumber("0"), new CryptoNumber("0"));
//		BigPoint result = c.edwards(first, second);
//		Assert.assertEquals(result, expected);
//	}
	
//	@Test(dependsOnGroups = {"bigpoint"}, groups = {"edward"})
//	public void testEdwards1and1() {
//		BigPoint first = new BigPoint(new BigInteger("1"), new BigInteger("1"));
//		BigPoint second = new BigPoint(new BigInteger("1"), new BigInteger("1"));
//		BigPoint expected = new BigPoint(new BigInteger("243332"), new BigInteger("51380297829790456491237162401597246033761091082941378702394490295467508647106"));
//		BigPoint result = c.edwards(first, second);
//		Assert.assertEquals(result, expected);
//	}

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
