package com.rsa.cipher.oke;

import java.math.BigInteger;
import java.security.SecureRandom;

import com.rsa.cipher.RSACipher;
import com.rsa.cipher.utils.Constants;
import com.rsa.cipher.utils.LoggerC;

public class OneKeyExecution {
	private static final String HEADER="OneKeyExecution.";
	private String logData;
	private RSACipher rsa;
	private RSABean bean = new RSABean();
	private String min;
	private LoggerC log;
	
	public OneKeyExecution(String min, boolean debug) {
		log = new LoggerC((debug)?Constants.DEBUG:Constants.NOT_LOGGER, HEADER);
		logData=HEADER+"OneKeyExecution() constructor";
		log.log(logData);
		this.min= min;
		logData=HEADER+"OneKeyExecutor() min = "+min;
		log.log(logData);
	}
	
	public RSACipher create(String sem, int iteration) {
		logData=HEADER+"create() init sem = "+sem+" iteration = "+iteration;
		log.log(logData);
		bean = generateNext(sem, iteration);
		rsa = new RSACipher(bean.getModulus(), bean.getPublicKey(), bean.getPrivateKey(), bean.getQ(), bean.getP());
		return rsa;
	}

	private RSABean generateNext(String sem, int iteration) {
		logData=HEADER+"generateNext() init sem = "+sem+" iteration = "+iteration;
		log.log(logData);
		sem = nextValue(sem, iteration);
		logData=HEADER+"generateNext() next value = "+sem;
		log.log(logData);
		SecureRandom rdn = new SecureRandom();
		//System.out.println(BigInteger.probablePrime(8980012, rdn));
		do {
			bean.setP(BigInteger.probablePrime(Integer.parseInt(sem),rdn));
			bean.setQ(BigInteger.probablePrime(Integer.parseInt(sem),rdn));
			bean.setPhi(bean.getP().subtract(BigInteger.ONE).multiply(bean.getQ().subtract(BigInteger.ONE)));
		} while (!bean.getPublicKey().gcd(bean.getPhi()).equals(BigInteger.ONE) || bean.getP().equals(bean.getQ()));
		bean.setModulus(bean.getP().multiply(bean.getQ()));
		bean.setPrivateKey(bean.getPublicKey().modInverse(bean.getPhi()));
		rsaToString();
		return bean;
	}
	
	private String nextValue(String sem, int iteration) {
		BigInteger min = new BigInteger(this.min);
		BigInteger it = new BigInteger(String.valueOf(iteration));
		return it.add(min.multiply(new BigInteger(sem))).toString();
	}

	public String rsaToString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n======================RSA DATA =========================");
		builder.append("\n\t mudulus = "+bean.getModulus());
		builder.append("\n\t publicKey = "+bean.getPublicKey());
		builder.append("\n\t privateKey = "+bean.getPrivateKey());
		builder.append("\n\t p = "+bean.getP());
		builder.append("\n\t q = "+bean.getQ());
		builder.append("\n\t mudulus = "+bean.getPhi());
		builder.append("\n\t phi = "+bean.getPhi());
		builder.append("\n======================RSA DATA =========================");
		logData=HEADER+"rsaToString() "+builder.toString();
		log.log(logData);
		return builder.toString();
	}
	
	
}
