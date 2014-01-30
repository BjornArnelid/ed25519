package ed25519.test.internal;

import org.testng.Assert;
import org.testng.annotations.Test;

import ed25519.application.internal.ByteArray;

public class ByteArrayTest {
	
	@Test(groups="byte")
	public void testcreateKeyFromBytes() {
		ByteArray array = new ByteArray(new byte[32]);
		Assert.assertEquals(array.bytes(), new byte[32]);
	}
	
	@Test(groups="byte")
	public void testKeyEquals() {
		ByteArray k1 = new ByteArray("Content".getBytes());
		ByteArray k2 = new ByteArray("Content".getBytes());
		Assert.assertEquals(k1, k2);
	}
	
	@Test(groups="byte")
	public void testKeyNotEquals() {
		ByteArray k1 = new ByteArray("Content".getBytes());
		ByteArray k2 = new ByteArray("Other".getBytes());
		Assert.assertNotEquals(k1, k2);
	}
}
