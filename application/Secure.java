package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import application.createPW.Chars;;
public class Secure
{

	private static String strKey;

	/*
	public static String Encrypt(String strClearText) throws Exception{
		String strData="";
		strKey = getKey();
		System.out.println("KeEnc "+strKey);

		try {
			SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
			System.out.println("ClearText "+strClearText);
			byte[] encrypted=cipher.doFinal(strClearText.getBytes());
			strData=new String(encrypted);
			System.out.println("Encrypted "+strData);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return strData;
	}
	public static String Decrypt(String strEncrypted) throws Exception{
		String strData="";
		strKey = getKey();
		System.out.println("Key "+strKey);

		try {
			SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, skeyspec);
			System.out.println("hereEn "+strEncrypted);
			byte[] decrypted=cipher.doFinal(strEncrypted.getBytes());
			strData=new String(decrypted);
			System.out.println("hereDE "+strData);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return strData;
	}

	public static String createKey() throws IOException{
		ArrayList<Chars> arraylist = new ArrayList<Chars>();
		arraylist.add(Chars.A);	arraylist.add(Chars.L);	arraylist.add(Chars.N);	arraylist.add(Chars.U);
		createPW pwObject = new createPW(30,arraylist);
		pwObject.setpwd();
		strKey = pwObject.getpwd();

		//Save key to file
	    String str = strKey;
	    String file = "DB/.gen";
	    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    writer.write(str);

	    writer.close();

		return strKey;

	}

	private static String getKey() {
		String file = "DB/.gen";
		String out = "";

		 try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader =
	                new FileReader(file);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader =
	                new BufferedReader(fileReader);

	           out = bufferedReader.readLine();

	            // Always close files.
	            bufferedReader.close();
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" +
	                file + "'");
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error reading file '"
	                + file + "'");
	        }
		 return out;
	}*/

	/*private static byte[] string;
	private static String xform;
	private static KeyGenerator keyGen;
	private static SecretKey key;

	private static byte[] iv =
		{ 0x0a, 0x01, 0x02, 0x03, 0x04, 0x0b, 0x0c, 0x0d };

	static public void init(String str) throws NoSuchAlgorithmException {
		string = str.getBytes();

		xform = "DES/ECB/PKCS5Padding";
		keyGen = KeyGenerator.getInstance("DES");
		keyGen.init(56);
		key = keyGen.generateKey();
	}

	  public static void main(String[] unused) throws Exception {
		  	String q = "TEst!";
		  	init(q);

		    byte[] encBytes = Encrypt();
		    byte[] decBytes = Decrypt();

		    boolean expected = java.util.Arrays.equals(q.getBytes(), decBytes);
		    System.out.println("Test " + (expected ? "SUCCEEDED!" : "FAILED!"));
		  }


	static public byte[] Encrypt() throws Exception {
	    	    Cipher cipher = Cipher.getInstance(xform);
	    	    IvParameterSpec ips = new IvParameterSpec(iv);
	    	    cipher.init(Cipher.ENCRYPT_MODE, key, ips);
	    	    return cipher.doFinal(string);
	}

	static public byte[] Decrypt() throws Exception{
	    Cipher cipher = Cipher.getInstance(xform);
	    IvParameterSpec ips = new IvParameterSpec(iv);
	    cipher.init(Cipher.DECRYPT_MODE, key, ips);
	    return cipher.doFinal(string);
	}*/

    static public String Encrypt(String str)
    {
        StringBuffer buffer = new StringBuffer(str);
        int len = buffer.length();
        char[] outBytes = new char[len];//Var para iterar por cada letra y cambiar el valor
        								//ascii en el for
        for(int x = 0; x < outBytes.length; x++){
            int ascii = (int)buffer.charAt(x) + 30;
            outBytes[x] = (char) ascii;
        }
        String out = new String(outBytes);//Convertir de char[] a String
        return out;
    }

    static public String Decrypt(String str)
    {

        StringBuffer buffer = new StringBuffer(str);
        int len = buffer.length();
        char[] outBytes = new char[len];//Var para iterar por cada letra y cambiar el valor
        								//ascii en el for
        for(int x = 0; x < outBytes.length; x++){
            int ascii = (int)buffer.charAt(x) - 30;
            outBytes[x] = (char) ascii;
        }
        String out = new String(outBytes);
        return out;
    }
}
