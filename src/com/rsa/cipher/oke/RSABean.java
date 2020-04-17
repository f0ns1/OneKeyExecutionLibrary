package com.rsa.cipher.oke;

import java.math.BigInteger;

public class RSABean {
	
	public BigInteger modulus;
	public BigInteger publicKey = new BigInteger("65537");
	private BigInteger privateKey;
	private  BigInteger phi;
	private BigInteger p;
	private BigInteger q;
	
	
	//getters && setters
	public BigInteger getModulus() {
		return modulus;
	}
	public void setModulus(BigInteger modulus) {
		this.modulus = modulus;
	}
	public BigInteger getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(BigInteger publicKey) {
		this.publicKey = publicKey;
	}
	public BigInteger getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(BigInteger privateKey) {
		this.privateKey = privateKey;
	}
	public BigInteger getPhi() {
		return phi;
	}
	public void setPhi(BigInteger phi) {
		this.phi = phi;
	}
	public BigInteger getP() {
		return p;
	}
	public void setP(BigInteger p) {
		this.p = p;
	}
	public BigInteger getQ() {
		return q;
	}
	public void setQ(BigInteger q) {
		this.q = q;
	}

	
	
	
}
