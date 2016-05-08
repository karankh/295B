/**
 * 
 */
package edu.sjsu.WebPortal.lab.utilServices;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
/**
 * Implementation of the encryption class
 * @author Karan
 */

public class PlayPP {

	public static String sha1(String sp) throws NoSuchAlgorithmException {
		System.out.println("input in hashing:"+sp);
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(sp.getBytes());
        System.out.println("result bytes in hashing:"+result);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        System.out.println("output in hashing:");
         System.out.println(sb);
         
        return sb.toString();
    }
	
}