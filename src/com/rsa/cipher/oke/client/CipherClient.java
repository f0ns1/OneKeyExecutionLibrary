package com.rsa.cipher.oke.client;

import java.math.BigInteger;
import java.util.Base64;

import com.rsa.cipher.RSACipher;
import com.rsa.cipher.oke.OneKeyExecution;
import com.rsa.cipher.utils.Constants;
import com.rsa.cipher.utils.Encode;
import com.rsa.cipher.utils.LoggerC;

public class CipherClient {
	private static final String HEADER = "CipherClient.";
	private String logData;
	private LoggerC log;
	private boolean debug;
	private RSACipher rsa;
	private OneKeyExecution oke;
	private Encode enc ;

	public CipherClient(boolean debug) {
		this.log = new LoggerC((debug) ? Constants.DEBUG : Constants.NOT_LOGGER, HEADER);
		this.debug = debug;
		this.enc = new Encode(debug);
	}

	public boolean createClient(String min, String sem, int it) {
		try {
			oke= new OneKeyExecution(min, debug);
			rsa = oke.create(sem, it);
		} catch (Exception e) {
			logData = "createClient() exception " + e.getMessage();
			log.log(logData);
		}
		return (rsa != null);
	}

	public String encrypt(String plainText) throws Exception {
		BigInteger message = enc.getDecimalFromAscii(plainText);
		BigInteger cipherText = rsa.encrypt(message);
		return Base64.getEncoder().encodeToString(cipherText.toString().getBytes());
	}

	public String decrypt(String cipherText) {
		String message = new String(Base64.getDecoder().decode(cipherText.getBytes()));
		logData = "decrypt from " + message;
		log.log(logData);
		BigInteger plainText = rsa.decrypt(new BigInteger(message));
		logData = "decrypt to " + plainText;
		log.log(logData);
		return enc.getAsciiFromDecimal(plainText);
	}

	
	public OneKeyExecution getOke() {
		return oke;
	}

	public void setOke(OneKeyExecution oke) {
		this.oke = oke;
	}

	

}
