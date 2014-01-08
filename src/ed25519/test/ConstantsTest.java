package ed25519.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ed25519.application.Constants;
import ed25519.application.CryptoNumber;

public class ConstantsTest {
	private Constants c;
	
	@BeforeClass
	protected void setUp() throws Exception {
		c = new Constants();
	}
	
	@Test
	public void testGetBitLength() {
		int expected = 256;
		Assert.assertEquals(c.getb(), expected);
	}

	@Test(dependsOnGroups = "basics")
	public void testBigPrime() {
		CryptoNumber expected = new CryptoNumber("57896044618658097711785492504343953926634992332820282019728792003956564819949");
		CryptoNumber actual = c.getq();
		Assert.assertEquals(actual, expected);
		actual.add(1);
		Assert.assertEquals(c.getq(), expected);
	}
	
	@Test(dependsOnMethods = "ed25519.test.CryptoNumberTest.testExpmod", dependsOnGroups = "basics")
	public void testGetI() {
		CryptoNumber expected = new CryptoNumber("19681161376707505956807079304988542015446066515923890162744021073123829784752");
		CryptoNumber actual = c.getI();
		Assert.assertEquals(actual, expected);
		actual.add(2);
		Assert.assertEquals(c.getI(), expected);
	}
}
