package org.xfs.scm.common.utils;

import org.apache.commons.net.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AESUtil {
	public static String Encrypt(String sSrc, String sKey) throws Exception {    
        if (sKey == null) {    
            System.out.print("Key为空null");    
            return null;    
        }    
        // 判断Key是否为16位    
        if (sKey.length() != 16) {    
            System.out.print("Key长度不是16位");    
            return null;    
        }    
        byte[] raw = sKey.getBytes();    
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");    
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"    
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度    
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);    
        byte[] encrypted = cipher.doFinal(sSrc.getBytes()); 
        return  Base64.encodeBase64String(encrypted);
    }    
	
	 public static String Decrypt(String sSrc, String sKey) throws Exception {    
	        try {    
	            // 判断Key是否正确    
	            if (sKey == null) {    
	                System.out.print("Key为空null");    
	                return null;    
	            }    
	            // 判断Key是否为16位    
	            if (sKey.length() != 16) {    
	                System.out.print("Key长度不是16位");    
	                return null;    
	            }    
	            byte[] raw = sKey.getBytes("ASCII");    
	            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");    
	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");    
	            IvParameterSpec iv = new IvParameterSpec("0102030405060708"    
	                    .getBytes());    
	            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
	            byte[] encrypted1 = Base64.decodeBase64(sSrc);
	            try {     
	                byte[] original = cipher.doFinal(encrypted1);
	                String originalString = new String(original); 
	                return originalString;    
	            } catch (Exception e) {    
	                System.out.println(e.toString());    
	                return null;    
	            }   
	        } catch (Exception ex) {    
	            System.out.println(ex.toString());    
	            return null;    
	        }    
	    }
}
