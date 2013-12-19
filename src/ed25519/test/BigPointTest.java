package ed25519.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.math.BigInteger;

import ed25519.application.BigPoint;

public class BigPointTest {
	
	@Test
	public void testBigPointEquals() {
		BigPoint first = new BigPoint(BigInteger.valueOf(1), BigInteger.valueOf(2));
		BigPoint second = new BigPoint(BigInteger.valueOf(1), BigInteger.valueOf(2));
		AssertJUnit.assertEquals(first,second);
	}
	
	@Test
	public void testBigPointNotEquals() {
		BigPoint first = new BigPoint(BigInteger.valueOf(1), BigInteger.valueOf(2));
		BigPoint second = new BigPoint(BigInteger.valueOf(3), BigInteger.valueOf(4));
		AssertJUnit.assertFalse("BigPoints should not be equal",first.equals(second));
	}
	
	@Test
	public void testXMod() {
		BigInteger expected = new BigInteger("1");
		BigPoint point = new BigPoint();
		point.setX(new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564819950"));
		AssertJUnit.assertEquals(expected, point.getX());
	}
	
	@Test
	public void testYMod() {
		BigInteger expected = new BigInteger("1");
		BigPoint point = new BigPoint();
		point.setY(new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564819950"));
		AssertJUnit.assertEquals(expected, point.getY());
	}

}
