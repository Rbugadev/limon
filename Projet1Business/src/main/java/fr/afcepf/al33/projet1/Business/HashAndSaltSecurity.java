package fr.afcepf.al33.projet1.Business;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;

public class HashAndSaltSecurity {


	private static String algoHash = "SHA-256"; 
	//private static byte [] salt; 
	//	private static String salt = "123";


		
		public static byte[] generateSalt() {
	        SecureRandom random = new SecureRandom();
	        byte bytes[] = new byte[20];
	        random.nextBytes(bytes);
	        return bytes;
	    }
		
		public static String bytetoString(byte[] input) {
	        return org.apache.commons.codec.binary.Base64.encodeBase64String(input);
	    }
		
		public static byte[] stringToByte(String input) {
	        if ( org.apache.commons.codec.binary.Base64.isBase64(input)) {
	            return  org.apache.commons.codec.binary.Base64.decodeBase64(input);

	        } else {
	            return  org.apache.commons.codec.binary.Base64.encodeBase64(input.getBytes());
	        }
	    }
		
		public static byte[] getHashWithSalt(String passwordToHash, byte[] salt) throws NoSuchAlgorithmException {
	        MessageDigest digest = MessageDigest.getInstance(algoHash);
	        digest.reset();
	        digest.update(salt);
	        byte[] hashedBytes = digest.digest(stringToByte(passwordToHash));
	        return hashedBytes;
	        
	    }
		
//		public static byte[] genererSalt()
//		{
//			SecureRandom sr = new SecureRandom();
//			byte [] salt = new byte[3];
//			sr.nextBytes(salt);
//			
//			String string = new String(salt);
//			System.out.println(string);
//			
//			return salt;
//		}
//	public static byte[] genererSalt()
//	{
//		SecureRandom sr = new SecureRandom();
//		byte[] salt = new byte[1];
//		sr.nextBytes(salt);
//		return salt;
//	}

//	private static String genererHash(String passwordToHash) 
//
//	{
//		salt = genererSalt();
//		String string = new String(salt);
//		System.out.println(string);
//		String passwordCree = null;
//		MessageDigest md;
//		try 
//		{
//			md = MessageDigest.getInstance(algoHash);
//	
//			md.update(salt);
//			System.out.println(salt);
//			byte [] hash = md.digest(passwordToHash.getBytes());
//			StringBuilder sb = new StringBuilder();
//			for(int i=0; i< hash.length ;i++)
//			{
//				sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
//			}
//			//Get complete hashed password in hex format
//			passwordCree = sb.toString();
//			
//			StringBuilder sbSalt = new StringBuilder();
//			for(int i=0; i< salt.length ;i++)
//			{
//				sbSalt.append(Integer.toString((salt[i] & 0xff) + 0x100, 16).substring(1));
//			}
//			//Get complete hashed password in hex format
//			String saltString = sbSalt.toString();
//			System.out.println("salt client : " + saltString);
//
//		} 
//		catch (NoSuchAlgorithmException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return passwordCree;
//
//	}
//	
//	public boolean verifierPassword(String passwordEnregistre,String  passwordAValide, String salt)
//	{
//		boolean passwordVerifie = false;
//		
//		MessageDigest md;
//		try 
//		{
//			md = MessageDigest.getInstance(algoHash);
//	
//			md.update(salt.getBytes());
//			System.out.println("salt client : " + salt);
//			byte [] hash = md.digest(passwordAValide.getBytes());
//			StringBuilder sb = new StringBuilder();
//			for(int i=0; i< hash.length ;i++)
//			{
//				sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
//			}
//			//Get complete hashed password in hex format
//			passwordAValide = sb.toString();
//			System.out.println("passwordAValide :" + passwordAValide);
//		} 
//		catch (NoSuchAlgorithmException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if ( passwordAValide.equals(passwordEnregistre))
//		{
//			passwordVerifie = true;
//		}
//		
//		return passwordVerifie;
//	}

//	TEST EN CONSOLE DU HASH AND SALT 
	
	public static void main(String[] args) {

//		String s = "123";
//		byte[] b = s.getBytes();
//		String s2 = new String(b);
//		System.out.println("s:" + s);
//		System.out.println("s2:" + s2);
		
//		genererSalt();
//		
//		System.out.println("entrez un mot de passe");
//		
//		Scanner sc = new Scanner(System.in);
//		
//		String mdp = sc.nextLine();
//
//		
//		System.out.println(genererHash(mdp));
//		
//		String saltFabrique = new String(salt);
//		
//		System.out.println(saltFabrique);

		byte [] salt = generateSalt();
		
		String saltString = bytetoString(salt);
		
		System.out.println(saltString);
		
		System.out.println("entrez un mot de passe");
		
		Scanner sc = new Scanner(System.in);
		
		String mdp = sc.nextLine();
		
		try {
			
			byte [] hashSaltPassword = getHashWithSalt(mdp, salt);
			
			String passwordHashSalt = bytetoString(hashSaltPassword);
			
			System.out.println(passwordHashSalt);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		sc.close();
		
	}
}
