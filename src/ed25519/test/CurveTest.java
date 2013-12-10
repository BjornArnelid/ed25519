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

//	public void testGetBasePoint() {
//		Curve c = new Curve();
//		BigInteger x = new BigInteger("15112221349535400772501151409588531511454012693041857206046113283949847762202");
//		BigInteger y = new BigInteger("46316835694926478169428394003475163141307993866256225615783033603165251855960");
//		BigPoint expected = new BigPoint(x,y);
//		BigPoint basePoint = c.getBasePoint();
//		assertEquals(expected, basePoint);
//	}
}
