package com.rsa.cipher.utils;

import java.math.BigInteger;

public class Encode {
	private static final String HEADER="Encode.";
	private String logData;
	private LoggerC log ;
	
	public Encode(boolean debug) {
		log = new LoggerC((debug)?Constants.DEBUG:Constants.NOT_LOGGER,HEADER);		
	}
	
	public BigInteger getDecimalFromAscii(String plainText) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < plainText.length(); i++) {
			builder.append(max((int) plainText.charAt(i)));
		}
		logData = "getDecimalFromAscii() plaintext = " + plainText + " decimal = " + builder.toString();
		log.log(logData);
		return new BigInteger(builder.toString());
	}

	private String max(int data) {
		int i = 0;
		StringBuilder builder = new StringBuilder();
		int iterations = 3 - String.valueOf(data).length();
		for (i = 0; i < iterations; i++) {
			builder.append(0);
		}
		builder.append(data);
		return builder.toString();
	}

	public String getAsciiFromDecimal(BigInteger plainText) {
		String data = plainText.toString();
		int i = 0;
		int j = 0;
		StringBuilder builder = new StringBuilder();
		for (i = 0; i < data.length(); i += 3) {
			j = i + 3;
			if (j <= data.length()) {
				builder.append((char) Integer.parseInt((String) data.subSequence(i, j)));

			}
		}
		logData = HEADER + "getAsciiFromDecimal() builder = " + builder.toString();
		log.log(logData);
		return builder.toString();
	}

}
