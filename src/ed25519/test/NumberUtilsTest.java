package ed25519.test;

import java.math.BigInteger;

import ed25519.application.BigPoint;
import ed25519.application.NumberUtils;
import junit.framework.TestCase;

public class NumberUtilsTest extends TestCase {
	

	public void testgetBaseNumber() {
		NumberUtils n = new NumberUtils();
		BigInteger expected = new BigInteger("28948022309329048855892746252171976963317496166410141009864396001978282409984");
		assertEquals(expected, n.getBaseNumber());
	}
	
	public void testGetPartNumber() {
		NumberUtils n = new NumberUtils();
		BigInteger expected = BigInteger.valueOf(8);
		assertEquals(expected, n.get2PowerOf(3));
	}
	
//	public void testGetBasePoint() {
//		NumberUtils n = new NumberUtils();
//		BigPoint basePoint = n.createBasePoint();
//		
//		BigInteger first = new BigInteger("46316835694926478169428394003475163141307993866256225615783033603165251855960");
//		BigInteger second = new BigInteger("15112221349535400772501151409588531511454012693041857206046113283949847762202");
//		BigPoint expectedPoint = new BigPoint(first,second);
//		assertEquals(expectedPoint, basePoint);
//	}
}
