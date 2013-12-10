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
		
	public void testgetXXfor0() {
		BigPoint n = new BigPoint();
		BigInteger expected = BigInteger.valueOf(-1);
		assertEquals(expected, n.getXX(BigInteger.valueOf(0)));
	}
	
 	public void testgetXXfor1() {
		BigPoint n = new BigPoint();
		BigInteger expected = BigInteger.valueOf(0);
		assertEquals(expected, n.getXX(BigInteger.valueOf(1)));
	}
 	
 	public void testgetXXfor2() {
		BigPoint n = new BigPoint();
		BigInteger expected = new BigInteger("159238947029881800007354471772647911766795077324544932017472991350137141588898");
		assertEquals(expected, n.getXX(BigInteger.valueOf(2))); 		
 	}

	public void testXrecoverEven() {
		BigPoint n = new BigPoint();
		BigInteger expected = new BigInteger("19681161376707505956807079304988542015446066515923890162744021073123829784752");
		assertEquals(expected, n.recoverX(BigInteger.valueOf(0)));
	}
	
	public void testXrecoverOdd() {
		BigPoint n = new BigPoint();
		BigInteger expected = new BigInteger("26193273134124080442446118532604303931175156347221147955160486408041496074798");
		assertEquals(expected, n.recoverX(BigInteger.valueOf(4)));
	}
	
//	public void testGetBasePoint() {
//		fail();
//	}
}
