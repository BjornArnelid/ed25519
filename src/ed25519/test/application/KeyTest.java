package ed25519.test.application;

import org.testng.Assert;
import org.testng.annotations.Test;

import ed25519.application.Key;

public class KeyTest {

	@Test
	public void testCreateKeyFromString() {
		Key key = new Key("Value");
		Assert.assertEquals(key.toString(), "Value");
	}
	
	@Test
	public void testcreateKeyFromBytes() {
		Key key = new Key(new byte[32]);
		Assert.assertEquals(key.bytes(), new byte[32]);
	}
}
