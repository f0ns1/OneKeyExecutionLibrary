package com.rsa.cipher.utils;

import java.util.logging.Logger;

public class LoggerC {
	private Logger log ;
	private String header;
	private String mode;
	
	public LoggerC( String mode, String className) {
		log = Logger.getLogger(className);
		this.mode=mode;
		this.header=className;
	}
	
	public void log(String data) {
		if(this.mode.equals(Constants.DEBUG)) {
			log.fine(header+data);
			System.out.println(header+data);
		}
	}
	
}
