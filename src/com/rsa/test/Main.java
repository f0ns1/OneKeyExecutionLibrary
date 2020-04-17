package com.rsa.test;

import com.rsa.cipher.oke.client.CipherClient;

public class Main {

	public static void main(String[] args) {
		System.out.println("Main process test");
		boolean debug = false;
		CipherClient client = new CipherClient(debug);
		String data = "data to encrypt!! f0ns1 ";
		int loop = 5;
		for (int i = 0; i < loop; i++) {
			if (client.createClient("10", "50", i)) {
				try {
					System.out.println("\t "+client.getOke().rsaToString());
					data = data+" it= "+i;
					System.out.println("\t plaintext =  " + data);
					String cipherText = client.encrypt(data);
					System.out.println("\t encrypted data " + cipherText);
					String decipher = client.decrypt(cipherText);
					System.out.println("\t decrypted data " + decipher);
				} catch (Exception e) {
					System.out.println("Encrypted data = " + e.getMessage());
				}
			}
		}
		System.out.println("End process test");
	}

}
