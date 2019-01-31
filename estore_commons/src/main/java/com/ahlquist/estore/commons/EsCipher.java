package com.ahlquist.estore.commons;

import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;

/**
 * <code>

   <bean name="Cipher" class="com.ahlquist.csv.utils.CsvCipher">
    	<property name="pwdIterations" value=65536 />
    	<property name="keySize" value=256/>
    	<property name="keyAlgorithm" value="AES"/>
    	<property name="encryptAlgorithm" value="AES/CBC/PKCS5Padding"/>
    	<property name="secretKeyFactoryAlgorithm" value="PBKDF2WithHmacSHA1"/>
   </bean>  
   
</code>
 * 
 */

public class EsCipher {

	final static org.apache.log4j.Logger logger = Logger.getLogger(Cipher.class);

	private static final String TOKEN = "passwd";
	private String salt;

	// @Autowired(required = false)
	// @Qualifier("pwdIterations")
	private int pwdIterations = 65536;

	// @Autowired(required = false)
	// @Qualifier("keySize")
	private int keySize = 128;

	// @Autowired(required = false)
	// @Qualifier("keyAlgorithm")
	private String keyAlgorithm = "AES";

	// @Autowired(required = false)
	// @Qualifier("encryptAlgorithm")
	private String encryptAlgorithm = "AES/CBC/PKCS5Padding";

	// @Autowired(required = false)
	// @Qualifier("secretKeyFactoryAlgorithm")
	private String secretKeyFactoryAlgorithm = "PBKDF2WithHmacSHA1";

	private IvParameterSpec iv;

	public EsCipher() {
		this.salt = getSalt();
	}

	/**
	 * TODO (dahlquist) : Update this code to get the SALT from an EC2 param
	 * value... For now keeping it hard coded is barely exceptble
	 * 
	 * @return
	 */
	private String getSalt() {
		// needs to be 16 chars
		String text = "H00v3r!sFr33N0w!";
		return text;
	}

	/**
	 * 
	 * @param plainText
	 * @return encrypted text
	 * @throws Exception
	 */
	public String encyrpt(String plainText) throws Exception {
		// generate key
		byte[] saltBytes = salt.getBytes("UTF-8");

		SecretKeyFactory skf = SecretKeyFactory.getInstance(this.secretKeyFactoryAlgorithm);
		PBEKeySpec spec = new PBEKeySpec(TOKEN.toCharArray(), saltBytes, this.pwdIterations, this.keySize);
		SecretKey secretKey = skf.generateSecret(spec);
		SecretKeySpec key = new SecretKeySpec(secretKey.getEncoded(), keyAlgorithm);

		// AES initialization
		Cipher cipher = Cipher.getInstance(encryptAlgorithm);
		this.iv = new IvParameterSpec(saltBytes);
		cipher.init(Cipher.ENCRYPT_MODE, key, this.iv);

		// generate IV

		byte[] encryptedText = cipher.doFinal(plainText.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(encryptedText);
	}

	/**
	 * 
	 * @param encryptText
	 * @return decrypted text
	 * @throws Exception
	 */
	public String decrypt(String encryptText) throws Exception {
		byte[] saltBytes = salt.getBytes("UTF-8");
		byte[] encryptTextBytes = Base64.getDecoder().decode(encryptText);

		SecretKeyFactory skf = SecretKeyFactory.getInstance(this.secretKeyFactoryAlgorithm);
		PBEKeySpec spec = new PBEKeySpec(TOKEN.toCharArray(), saltBytes, this.pwdIterations, this.keySize);
		SecretKey secretKey = skf.generateSecret(spec);
		SecretKeySpec key = new SecretKeySpec(secretKey.getEncoded(), keyAlgorithm);

		// decrypt the message
		Cipher cipher = Cipher.getInstance(encryptAlgorithm);
		// this.ivBytes =
		// cipher.getParameters().getParameterSpec(IvParameterSpec.class).getIV();
		this.iv = new IvParameterSpec(saltBytes);
		cipher.init(Cipher.DECRYPT_MODE, key, this.iv);

		byte[] decyrptTextBytes = null;
		try {
			decyrptTextBytes = cipher.doFinal(encryptTextBytes);
		} catch (IllegalBlockSizeException e) {
			logger.error(e);
		} catch (BadPaddingException e) {
			logger.error(e);
		}
		return new String(decyrptTextBytes);
	}

}