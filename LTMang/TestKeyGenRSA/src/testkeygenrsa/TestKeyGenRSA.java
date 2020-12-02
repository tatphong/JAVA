/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testkeygenrsa;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author tattr
 */
public class TestKeyGenRSA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(2048);
            KeyPair pair = keyPairGen.generateKeyPair();
            PublicKey publicKey = pair.getPublic();
            Cipher cipher;
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] input = "Welcome to RSA".getBytes();	  
            cipher.update(input);
            byte[] cipherText = cipher.doFinal();
            System.out.println(new String(cipherText, "UTF8"));
            
//descryption
            Cipher des_cipher;
            des_cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            des_cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
            des_cipher.update(cipherText);
            System.out.println("Descrypt: \n" + new String(des_cipher.doFinal(), "UTF8"));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(TestKeyGenRSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(TestKeyGenRSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(TestKeyGenRSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TestKeyGenRSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(TestKeyGenRSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(TestKeyGenRSA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
