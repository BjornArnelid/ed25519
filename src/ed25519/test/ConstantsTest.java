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
		c = Constants.getInstance();
	}
	
	@Test(groups= "constants")
	public void getInstance() {
		Assert.assertNotNull(c);
	}
	
	@Test(groups= "constants")
	public void testGetInstanceExists() {
		Constants other = Constants.getInstance();
		Assert.assertEquals(c, other);
	}
	
	@Test
	public void testGetBitLength() {
		int expected = 256;
		Assert.assertEquals(c.getb(), expected);
	}

	@Test(dependsOnGroups = {"basics", "constants"})
	public void testGetQ() {
		CryptoNumber expected = new CryptoNumber("57896044618658097711785492504343953926634992332820282019728792003956564819949");
		CryptoNumber actual = c.getq();
		Assert.assertEquals(actual, expected);
		actual.add(1);
		Assert.assertEquals(c.getq(), expected);
	}
	
	@Test(dependsOnMethods = "ed25519.test.CryptoNumberTest.testExpmod", dependsOnGroups = {"basics", "constants"})
	public void testGetI() {
		CryptoNumber expected = new CryptoNumber("19681161376707505956807079304988542015446066515923890162744021073123829784752");
		CryptoNumber actual = c.getI();
		Assert.assertEquals(actual, expected);
		actual.add(2);
		Assert.assertEquals(c.getI(), expected);
	}
	
	@Test(dependsOnGroups = {"basics", "constants"}, dependsOnMethods = "ed25519.test.CryptoNumberTest.testInvert")
	public void testGetD() {
		CryptoNumber expected = new CryptoNumber("-4513249062541557337682894930092624173785641285191125241628941591882900924598840740");
		CryptoNumber actual = c.getD();
		Assert.assertEquals(actual, expected);
		actual.add(2);
		Assert.assertEquals(c.getD(), expected);
	}
}