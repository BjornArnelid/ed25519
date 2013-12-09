package ed25519.test;

import java.math.BigInteger;

import junit.framework.TestCase;
import ed25519.application.Constants;

public class ConstantsTest extends TestCase {

	protected static void setUpBeforeClass() throws Exception {
	}

	protected static void tearDownAfterClass() throws Exception {
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetBitLength() {
		Constants c = new Constants();
		int expected = 256;
		assertEquals(expected, c.getBitLength());
	}
	
	public void testGetBaseNumber() {
		Constants c = new Constants();
		BigInteger expected = new BigInteger("28948022309329048855892746252171976963317496166410141009864396001978282409984");
		assertEquals(expected, c.getBaseNumber());
	}

	public void testBigPrime() {
		Constants c = new Constants();
		BigInteger expected = new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564819949");
		assertEquals(expected, c.getBigPrime());
	}
	
//	public void testGetI() {
//		BigInteger result = new BigInteger("19681161376707505956807079304988542015446066515923890162744021073123829784752");
//		Constants constants = new Constants(); 
//		assertEquals(constants.getI(), result);
//	}
	
}
