package ed25519.test.application;

import org.testng.Assert;
import org.testng.annotations.Test;

import ed25519.application.Key;


public class KeyTest {

	@Test(groups="key", dependsOnGroups="byte")
	public void testCreateKeyFromString() {
		Key key = new Key("Value");
		Assert.assertEquals(key.toString(), "Value");
	}
	
	@Test(groups="key", dependsOnGroups="byte")
	public void testcreateKeyFromBytes() {
		Key key = new Key(new byte[32]);
		Assert.assertEquals(key.bytes(), new byte[32]);
	}
	
	@Test(groups="key", dependsOnGroups="byte")
	public void testKeyEquals() {
		Key k1 = new Key("Content");
		Key k2 = new Key("Content");
		Assert.assertEquals(k1, k2);
	}
	
	@Test(groups="key", dependsOnGroups="byte")
	public void testKeyNotEquals() {
		Key k1 = new Key("Content");
		Key k2 = new Key("Other");
		Assert.assertNotEquals(k1, k2);
	}
}
