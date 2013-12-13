package ed25519.test;

import java.math.BigInteger;

import ed25519.application.BigPoint;
import ed25519.application.Curve;
import junit.framework.TestCase;

public class CurveTest extends TestCase {
	private Curve c;

	protected void setUp() throws Exception {
		super.setUp();
		c = new Curve();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testInvertPoint1() {
		BigInteger expected = BigInteger.valueOf(1);
		assertEquals(expected, c.invertPoint(BigInteger.valueOf(1)));
	}
	
	public void testInvertPoint5() {
		BigInteger expected = new BigInteger("11579208923731619542357098500868790785326998466564056403945758400791312963990");
		assertEquals(expected, c.invertPoint(BigInteger.valueOf(5)));
	}
	
	public void testgetXXfor0() {
		BigInteger expected = BigInteger.valueOf(-1);
		assertEquals(expected, c.getXX(BigInteger.valueOf(0)));
	}
	
 	public void testgetXXfor1() {
		BigInteger expected = BigInteger.valueOf(0);
		assertEquals(expected, c.getXX(BigInteger.valueOf(1)));
	}
 	
 	public void testgetXXfor2() {
		BigInteger expected = new BigInteger("159238947029881800007354471772647911766795077324544932017472991350137141588898");
		assertEquals(expected, c.getXX(BigInteger.valueOf(2))); 		
 	}

	public void testXrecoverEven() {
		BigInteger expected = new BigInteger("19681161376707505956807079304988542015446066515923890162744021073123829784752");
		assertEquals(expected, c.recoverX(BigInteger.valueOf(0)));
	}
	
	public void testXrecoverOdd() {
		BigInteger expected = new BigInteger("26193273134124080442446118532604303931175156347221147955160486408041496074798");
		assertEquals(expected, c.recoverX(BigInteger.valueOf(4)));
	}

	public void testGetBasePoint() {
		BigInteger x = new BigInteger("15112221349535400772501151409588531511454012693041857206046113283949847762202");
		BigInteger y = new BigInteger("46316835694926478169428394003475163141307993866256225615783033603165251855960");
		BigPoint expected = new BigPoint(x,y);
		BigPoint basePoint = c.getBasePoint();
		assertEquals(expected, basePoint);
	}
	
	public void testScalarMult0() {
		BigPoint expected = new BigPoint(new BigInteger("0"), new BigInteger("1"));
		BigPoint result = c.scalarmult(new BigInteger("0"));
		assertEquals(expected, result);
	}
	
	public void testScalarMult1() {
		BigInteger x = new BigInteger("15112221349535400772501151409588531511454012693041857206046113283949847762202");
		BigInteger y = new BigInteger("46316835694926478169428394003475163141307993866256225615783033603165251855960");
		BigPoint expected = new BigPoint(x, y);
		BigPoint result = c.scalarmult(new BigInteger("1"));
		assertEquals(expected, result);
	}
}
