package ed25519.test;

import java.math.BigInteger;

import ed25519.application.BigPoint;
import ed25519.application.Constants;
import junit.framework.TestCase;

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
	
	public void testExpmodfor0() {
		BigPoint n = new BigPoint();
		BigInteger first = BigInteger.valueOf(1);
		BigInteger second = BigInteger.valueOf(0);
		BigInteger expected = BigInteger.valueOf(1);
		BigInteger actual = n.expmod(first, second);
		assertEquals(expected, actual);
	}
	
	public void testExpmodfor1and1() {
		BigPoint n = new BigPoint();
		BigInteger first = BigInteger.valueOf(1);
		BigInteger second = BigInteger.valueOf(1);
		BigInteger expected = BigInteger.valueOf(1);
		BigInteger actual = n.expmod(first, second);
		assertEquals(expected, actual);
	}
	
	public void testExpmodfor2and1() {
		BigPoint n = new BigPoint();
		BigInteger first = BigInteger.valueOf(2);
		BigInteger second = BigInteger.valueOf(1);
		BigInteger expected = BigInteger.valueOf(2);
		BigInteger actual = n.expmod(first, second);
		assertEquals(expected, actual);
	}
	
	public void testExpmodforHugeand1() {
		BigPoint n = new BigPoint();
		Constants c = new Constants();
		BigInteger first = c.getBigPrime().add(BigInteger.valueOf(1));
		BigInteger second = BigInteger.valueOf(1);
		BigInteger expected = BigInteger.valueOf(1);
		BigInteger actual = n.expmod(first, second);
		assertEquals(expected, actual);
	}
	
	public void testExpmodfor1and2() {
		BigPoint n = new BigPoint();
		BigInteger first = BigInteger.valueOf(1);
		BigInteger second = BigInteger.valueOf(2);
		BigInteger expected = BigInteger.valueOf(1);
		BigInteger actual = n.expmod(first, second);
		assertEquals(expected, actual);
	}
	
	public void testExpmodfor2and2() {
		BigPoint n = new BigPoint();
		BigInteger first = BigInteger.valueOf(2);
		BigInteger second = BigInteger.valueOf(2);
		BigInteger expected = BigInteger.valueOf(4);
		BigInteger actual = n.expmod(first, second);
		assertEquals(expected, actual);
	}

	public void testExpmodforHugeand2() {
		BigPoint n = new BigPoint();
		Constants c = new Constants();
		BigInteger first = c.getBigPrime().divide(BigInteger.valueOf(2));
		BigInteger second = BigInteger.valueOf(2);
		BigInteger expected = new BigInteger("43422033463993573283839119378257965444976244249615211514796594002967423614962");
		BigInteger actual = n.expmod(first, second);
		assertEquals(expected, actual);
	}
	
	public void testExpModfor3and3() {
		BigPoint n = new BigPoint();
		BigInteger first = BigInteger.valueOf(3);
		BigInteger second = BigInteger.valueOf(3);
		BigInteger expected = BigInteger.valueOf(27);
		BigInteger actual = n.expmod(first, second);
		assertEquals(expected, actual);
	}
	
	public void testExpmodforHugeand3() {
		BigPoint n = new BigPoint();
		Constants c = new Constants();
		BigInteger first = c.getBigPrime().divide(BigInteger.valueOf(2));
		BigInteger second = BigInteger.valueOf(3);
		BigInteger expected = new BigInteger("36185027886661311069865932815214971204146870208012676262330495002472853012468");
		BigInteger actual = n.expmod(first, second);
		assertEquals(expected, actual);
	}

	public void testExpmodforHugeand4() {
		BigPoint n = new BigPoint();
		Constants c = new Constants();
		BigInteger first = c.getBigPrime().divide(BigInteger.valueOf(2));
		BigInteger second = BigInteger.valueOf(4);
		BigInteger expected = new BigInteger("39803530675327442176852526096736468324561557228813943888563544502720138313715");
		BigInteger actual = n.expmod(first, second);
		assertEquals(expected, actual);
	}
	
	public void testInvertPoint1() {
		BigPoint n = new BigPoint();
		BigInteger expected = BigInteger.valueOf(1);
		assertEquals(expected, n.invertPoint(BigInteger.valueOf(1)));
	}
	
	public void testInvertPoint5() {
		BigPoint n = new BigPoint();
		BigInteger expected = new BigInteger("11579208923731619542357098500868790785326998466564056403945758400791312963990");
		assertEquals(expected, n.invertPoint(BigInteger.valueOf(5)));
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

//	public void testXrecover() {
//		BigPoint n = new BigPoint();
//		BigInteger expected = new BigInteger("19681161376707505956807079304988542015446066515923890162744021073123829784752");
//		assertEquals(expected, n.recoverX(BigInteger.valueOf(0)));
//	}
}
