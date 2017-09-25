package com.codeworks.util;

import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.time.DateUtils;
//import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.RandomStringGenerator;


public class RandomInviteCodeGenerator {
	
	public static final String SECRET_KEY = "prep@#omen$@txhn";
	
	public static String randomString(String firstName,String lastName){
		
		int firstNameLength = firstName.length();
		int lastNameLength = lastName.length();

		int randomStringLength = firstNameLength >= lastNameLength ? firstNameLength : lastNameLength;		
		
		randomStringLength = randomStringLength <= 9 ? 9 : 10;		
		
		
		return randomStringGenerator(randomStringLength);
	}

	public static String randomStringGenerator(int length) {

		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('@', 'Z').build();
		/*
		 * String timeStamp = new SimpleDateFormat("MMddYYHHmmss").format(new
		 * Date()); System.out.println("timeStamp "+timeStamp);
		 */

		String randomString = generator.generate(length);
		System.out.println("\n"+randomString.toUpperCase().toString());

		/*randomLetters = generator.generate(length);
		System.out.println("hello " + randomLetters.toUpperCase().toString());

		randomLetters = generator.generate(length);
		System.out.println("hello " + randomLetters.toUpperCase().toString());

		randomLetters = generator.generate(length);
		System.out.println("hello " + randomLetters.toUpperCase().toString());

		randomLetters = generator.generate(length);
		System.out.println("hello " + randomLetters.toUpperCase().toString());*/
		return randomString;
	}

	/*public static int maxLength(String firstName, String lastName) {

		int firstNameLength = firstName.length();
		int lastNameLength = lastName.length();

		int greaterLength = firstNameLength >= lastNameLength ? firstNameLength : lastNameLength;
		
		
		
		greaterLength = greaterLength <= 9 ? 9 : 10; 
		
		return greaterLength;

	}*/
	
	public static String decryptPwd(String cipherText) throws Exception {
		//String secret = "prep@#omen$@txhn";
		//String cipherText =	"U2FsdGVkX19mtT8EkaZ0ALq0++16U7P75/PuApXRAe7It5A3GA+scZHNfsXHH40C4FItM2580xMae+6x5hZ3OA==";
		//String cipherText = "U2FsdGVkX1+tsmZvCEFa/iGeSA0K7gvgs9KXeZKwbCDNCs2zPo+BXjvKYLrJutMK+hxTwl/hyaQLOaD7LLIRo2I5fyeRMPnroo6k8N9uwKk=";

		byte[] cipherData = Base64.getDecoder().decode(cipherText);
		byte[] saltData = Arrays.copyOfRange(cipherData, 8, 16);

		MessageDigest md5 = MessageDigest.getInstance("MD5");
		final byte[][] keyAndIV = GenerateKeyAndIV(32, 16, 1, saltData, SECRET_KEY.getBytes(StandardCharsets.UTF_8), md5);
		SecretKeySpec key = new SecretKeySpec(keyAndIV[0], "AES");
		IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);

		byte[] encrypted = Arrays.copyOfRange(cipherData, 16, cipherData.length);
		Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
		aesCBC.init(Cipher.DECRYPT_MODE, key, iv);
		byte[] decryptedData = aesCBC.doFinal(encrypted);
		String decryptedText = new String(decryptedData, StandardCharsets.UTF_8);

		System.out.println(decryptedText);
		return decryptedText;
	}

	public static void main(String[] args) throws Exception {
		
		
		
		System.setProperty("user.timezone", "UTC");
		//System.setProperty("user.timezone", "EST");
		TimeZone.setDefault(null);
		
		System.out.println(" timezone "+System.getProperty( "user.timezone" ) +" \nTimeZone details "+
		TimeZone.getDefault());
		
		GregorianCalendar gc = new GregorianCalendar(TimeZone.getDefault());
		Date d = gc.getTime();
		Date d2 = DateUtils.addDays(d, 90);
		
		//SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd 'T' HH:mm:ss");
		
		SimpleDateFormat isoFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		System.out.println("Creation Date "+isoFormat.format(d));
		System.out.println("Target Date "+isoFormat.format(d2));
		
		System.out.println(" hello "+isoFormat.parse(isoFormat.format(d)));
		

		//randomStringGenerator(maxLength("JONATHON", "DEQUEEN"));
		
		/*String xyz= "surendar";
		
		String text = "Message";
		//String key = "Simple Passpharase";
        String key = "prep@#omen$@txhn"; // 128 bit key
        // Create key and cipher
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        // encrypt the text
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encrypted = cipher.doFinal(text.getBytes());
        System.err.println(new String(encrypted));
        // decrypt the text
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        String decrypted = new String(cipher.doFinal(encrypted));
        System.err.println(decrypted);*/
		
		/*String plainText = "Hello, World! This is a Java/Javascript AES test.";
		SecretKey key = new SecretKeySpec(
		    Base64.decodeBase64("u/Gu5posvwDsXUnV5Zaq4g=="), "AES");
		AlgorithmParameterSpec iv = new IvParameterSpec(
		    Base64.decodeBase64("5D9r9ZVzEYYgha93/aUK2w==")); 
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		System.out.println(Base64.encodeBase64String(cipher.doFinal(
		    plainText.getBytes("UTF-8"))));*/
		
		
		//randomStringGenerator(maxLength("ARTURO", "CLARENDON"));
		
		/*String secret = "prep@#omen$@txhn";
		String cipherText =	"U2FsdGVkX19XlcT97yOLuJPInE1peP7SXGJfIcyYqZ4=";//"U2FsdGVkX19mtT8EkaZ0ALq0++16U7P75/PuApXRAe7It5A3GA+scZHNfsXHH40C4FItM2580xMae+6x5hZ3OA==";
		//String cipherText = "U2FsdGVkX1+tsmZvCEFa/iGeSA0K7gvgs9KXeZKwbCDNCs2zPo+BXjvKYLrJutMK+hxTwl/hyaQLOaD7LLIRo2I5fyeRMPnroo6k8N9uwKk=";

		byte[] cipherData = Base64.getDecoder().decode(cipherText);
		byte[] saltData = Arrays.copyOfRange(cipherData, 8, 16);

		MessageDigest md5 = MessageDigest.getInstance("MD5");
		final byte[][] keyAndIV = GenerateKeyAndIV(32, 16, 1, saltData, secret.getBytes(StandardCharsets.UTF_8), md5);
		SecretKeySpec key = new SecretKeySpec(keyAndIV[0], "AES");
		IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);

		byte[] encrypted = Arrays.copyOfRange(cipherData, 16, cipherData.length);
		Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
		aesCBC.init(Cipher.DECRYPT_MODE, key, iv);
		byte[] decryptedData = aesCBC.doFinal(encrypted);
		String decryptedText = new String(decryptedData, StandardCharsets.UTF_8);

		System.out.println(decryptedText);*/
	}
	
	/**
	 * Generates a key and an initialization vector (IV) with the given salt and password.
	 * <p>
	 * This method is equivalent to OpenSSL's EVP_BytesToKey function
	 * (see https://github.com/openssl/openssl/blob/master/crypto/evp/evp_key.c).
	 * By default, OpenSSL uses a single iteration, MD5 as the algorithm and UTF-8 encoded password data.
	 * </p>
	 * @param keyLength the length of the generated key (in bytes)
	 * @param ivLength the length of the generated IV (in bytes)
	 * @param iterations the number of digestion rounds 
	 * @param salt the salt data (8 bytes of data or <code>null</code>)
	 * @param password the password data (optional)
	 * @param md the message digest algorithm to use
	 * @return an two-element array with the generated key and IV
	 */
	public static byte[][] GenerateKeyAndIV(int keyLength, int ivLength, int iterations, byte[] salt, byte[] password, MessageDigest md) {

	    int digestLength = md.getDigestLength();
	    int requiredLength = (keyLength + ivLength + digestLength - 1) / digestLength * digestLength;
	    byte[] generatedData = new byte[requiredLength];
	    int generatedLength = 0;

	    try {
	        md.reset();

	        // Repeat process until sufficient data has been generated
	        while (generatedLength < keyLength + ivLength) {

	            // Digest data (last digest if available, password data, salt if available)
	            if (generatedLength > 0)
	                md.update(generatedData, generatedLength - digestLength, digestLength);
	            md.update(password);
	            if (salt != null)
	                md.update(salt, 0, 8);
	            md.digest(generatedData, generatedLength, digestLength);

	            // additional rounds
	            for (int i = 1; i < iterations; i++) {
	                md.update(generatedData, generatedLength, digestLength);
	                md.digest(generatedData, generatedLength, digestLength);
	            }

	            generatedLength += digestLength;
	        }

	        // Copy key and IV into separate byte arrays
	        byte[][] result = new byte[2][];
	        result[0] = Arrays.copyOfRange(generatedData, 0, keyLength);
	        if (ivLength > 0)
	            result[1] = Arrays.copyOfRange(generatedData, keyLength, keyLength + ivLength);

	        return result;

	    } catch (DigestException e) {
	        throw new RuntimeException(e);

	    } finally {
	        // Clean out temporary data
	        Arrays.fill(generatedData, (byte)0);
	    }
	}
}
