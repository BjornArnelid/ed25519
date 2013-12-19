package ed25519.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

import ed25519.application.Hash;

public class HashTest {

	@Test
	public void testHashString() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Hash sha512 = new Hash("SHA-512");
		byte[] input = "0".getBytes();
		sha512.digest(input);
		byte[] expected = DatatypeConverter.parseHexBinary("31bca02094eb78126a517b206a88c73cfa9ec6f704c7030d18212cace820f025f00bf0ea68dbf3f3a5436ca63b53bf7bf80ad8d5de7d8359d0b7fed9dbc3ab99");
		AssertJUnit.assertTrue("Expected: " + expected + "\nbut was " + sha512.getBytes() ,Arrays.equals(expected, sha512.getBytes()));
	}
	
	@Test
	public void testGetfirstBitInHash() throws NoSuchAlgorithmException {
		Hash sha512 = new Hash("SHA-512");
		byte[] input = "0".getBytes(); 
		sha512.digest(input);
		int expected = 1; // 31 = '0b110001'
		AssertJUnit.assertEquals(expected, sha512.getBit(0));
	}
	
	@Test
	public void testFirstBitInSecondByte() throws NoSuchAlgorithmException {
		Hash sha512 = new Hash("SHA-512");
		byte[] input = "0".getBytes(); 
		sha512.digest(input);
		int expected = 0; // bc = '0b10111100'
		AssertJUnit.assertEquals(expected, sha512.getBit(8));
	}
	
	@Test
	public void testThirdBitInSecondByte() throws NoSuchAlgorithmException {
		Hash sha512 = new Hash("SHA-512");
		byte[] input = "0".getBytes(); 
		sha512.digest(input);
		int expected = 1; // bc = '0b10111100'
		AssertJUnit.assertEquals(expected, sha512.getBit(10));
	}
}
