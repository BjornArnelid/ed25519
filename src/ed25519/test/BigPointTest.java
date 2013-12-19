package ed25519.test;

import java.math.BigInteger;

import org.testng.Assert;
import org.testng.annotations.Test;

import ed25519.application.BigPoint;

public class BigPointTest {
	
	@Test(groups = {"bigpoint"})
	public void testBigPointEquals() {
		BigPoint first = new BigPoint(BigInteger.valueOf(1), BigInteger.valueOf(2));
		BigPoint second = new BigPoint(BigInteger.valueOf(1), BigInteger.valueOf(2));
		Assert.assertEquals(first,second);
	}
	
	@Test(groups = {"bigpoint"})
	public void testBigPointNotEquals() {
		BigPoint first = new BigPoint(BigInteger.valueOf(1), BigInteger.valueOf(2));
		BigPoint second = new BigPoint(BigInteger.valueOf(3), BigInteger.valueOf(4));
		Assert.assertNotEquals(first, second);
	}
	
	@Test(groups = {"bigpoint"}, dependsOnMethods = {"ed25519.test.ConstantsTest.testBigPrime"})
	public void testXMod() {
		BigInteger expected = new BigInteger("1");
		BigPoint point = new BigPoint();
		point.setX(new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564819950"));
		Assert.assertEquals(point.getX(), expected);
	}
	
	@Test(groups = {"bigpoint"}, dependsOnMethods = {"ed25519.test.ConstantsTest.testBigPrime"})
	public void testYMod() {
		BigInteger expected = new BigInteger("1");
		BigPoint point = new BigPoint();
		point.setY(new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564819950"));
		Assert.assertEquals(point.getY(), expected);
	}

}
