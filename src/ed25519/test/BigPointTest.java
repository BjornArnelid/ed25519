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
	
	public void testXMod() {
		BigInteger expected = new BigInteger("1");
		BigPoint point = new BigPoint();
		point.setX(new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564819950"));
		assertEquals(expected, point.getX());
	}
	
	public void testYMod() {
		BigInteger expected = new BigInteger("1");
		BigPoint point = new BigPoint();
		point.setY(new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564819950"));
		assertEquals(expected, point.getY());
	}

}
