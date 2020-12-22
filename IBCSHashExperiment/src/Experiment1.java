/*
 	Experiment 1
 */

//imported classes
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.io.*;  

public class Experiment1 {
	
		public static void main(String args[]) throws NoSuchAlgorithmException, IOException 
	{
		String[] User_Password = new String[1000000];
		String[] MD5_Password = new String[1000000];
		String[] SHA256_Password = new String[1000000];
		String[] SHA512_Password = new String[1000000];
		long startTime = System.nanoTime();
		
		PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder().useDigits(true).useLower(true).useUpper(true).build();	
		int i=0;
		while(i<1000000)
		{
			User_Password[i]= passwordGenerator.generate(10);
			MD5_Password[i] = getMd5(User_Password[i]);
			SHA256_Password[i] = toHexString(getSHA(User_Password[i]));
			SHA512_Password[i] = getSHA512(User_Password[i]);
			i++;
		}
		//removing duplicates from User_Password to prevent duplicates produce the same hash value
		User_Password = Arrays.stream(User_Password).distinct().toArray(String[]::new);
		int size =  Arrays.toString(User_Password).length();
		System.out.println(("length of User_Password Array after removing duplicates: " + (size-6)/10));
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("running time of the program in seconds: " + (totalTime)/1000000000); // running time 
		
		for(int x = 0; x < 1000000; x++) //checking hash collisions produced
		{ 
			for (int j = x + 1 ; j < 1000000; j++)
			{
				if (MD5_Password[x].equals(MD5_Password[j])) 
				{ // got the duplicate element } } 
					System.out.println("duplicates md5 hash: "+ MD5_Password[x]);
				}
				if (SHA256_Password[x].equals(SHA256_Password[j])) 
				{ // got the duplicate element } } 
					System.out.println("duplicates SHA256 hash: "+ SHA256_Password[x]);
				}
				if (SHA512_Password[x].equals(SHA512_Password[j])) 
				{ // got the duplicate element } } 
					System.out.println("duplicates SHA512 hash: "+ SHA512_Password[x]);
				}
			}
		}
	} 
		
// the code was referenced from a website geeksforgeeks. Link: https://www.geeksforgeeks.org/md5-hash-in-java/
	public static String getMd5(String input)  //generating MD5 Hash
	{ 
		try { 

			// Static getInstance method is called with hashing MD5 
			MessageDigest md = MessageDigest.getInstance("MD5"); 

			// digest() method is called to calculate message digest 
			// of an input digest() return array of byte 
			byte[] messageDigest = md.digest(input.getBytes()); 

			// Convert byte array into signum representation 
			BigInteger no = new BigInteger(1, messageDigest); 

			// Convert message digest into hex value 
			String hashtext = no.toString(16); 
			while (hashtext.length() < 32) { 
				hashtext = "0" + hashtext; 
			} 
			return hashtext; 
		} 

		// For specifying wrong message digest algorithms 
		catch (NoSuchAlgorithmException e) { 
			throw new RuntimeException(e); 
		} 
	}
// the code was referenced from a website geeksforgeeks. Link: https://www.geeksforgeeks.org/sha-256-hash-in-java/
	public static byte[] getSHA(String input) throws NoSuchAlgorithmException // generating SHA256 Hash
    {  
        // Static getInstance method is called with hashing SHA  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
        // digest() method is called to calculate message digest of an input and return array of byte 
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
    public static String toHexString(byte[] hash) 
    { 
        // Convert byte array into signum representation  
        BigInteger number = new BigInteger(1, hash);  
        // Convert message digest into hex value  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
        // Pad with leading zeros 
        while (hexString.length() < 32)  
        {  
            hexString.insert(0, '0');  
        }  
        return hexString.toString();  // SHA256 hash = toHexString(getSHA());
    }
// the code was referenced from a website geeksforgeeks. Link: https://www.geeksforgeeks.org/sha-512-hash-in-java/
    public static String getSHA512(String input) 
	{ 
		try { 
			// getInstance() method is called with algorithm SHA-512 
			MessageDigest md = MessageDigest.getInstance("SHA-512"); 

			// digest() method is called 
			// to calculate message digest of the input string 
			// returned as array of byte 
			byte[] messageDigest = md.digest(input.getBytes()); 

			// Convert byte array into signum representation 
			BigInteger no = new BigInteger(1, messageDigest); 

			// Convert message digest into hex value 
			String hashtext = no.toString(16); 

			// Add preceding 0s to make it 32 bit 
			while (hashtext.length() < 32) { 
				hashtext = "0" + hashtext; 
			} 

			// return the HashText 
			return hashtext; 
		} 

		// For specifying wrong message digest algorithms 
		catch (NoSuchAlgorithmException e) { 
			throw new RuntimeException(e); 
		} 
	}
}


