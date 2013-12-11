package ed25519.test;

import java.math.BigInteger;

import junit.framework.TestCase;
import ed25519.application.BigPoint;

public class BigPointTest extends TestCase {
	
	public void testBigPointEquals() {
		BigPoint first = new BigPoint(BigInteger.valueOf(1), BigInteger.valueOf(2));
		BigPoint second = new BigPoint(BigInteger.valueOf(1), BigInteger.valueOf(2));
		assertEquals(first,second);
	}
	
	public void testBigPointNotEquals() {
		BigPoint first = new BigPoint(BigInteger.valueOf(1), BigInteger.valueOf(2));
		BigPoint second = new BigPoint(BigInteger.valueOf(3), BigInteger.valueOf(4));
		assertFalse("BigPoints should not be equal",first.equals(second));
	}
}
