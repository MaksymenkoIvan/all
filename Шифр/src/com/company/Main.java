package com.company;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class Main {

    public static void main(String[] args) {
	    Main main = new Main();
	    main.start();
    }
    public void start(){
        try {
            String text = "Dantist loh";
            String key = "Bar12345Bar12345";
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte [] enncrypted = cipher.doFinal(text.getBytes());
            // out enc
            System.out.println(new String(enncrypted));


            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            String decrypted = new String(cipher.doFinal(enncrypted));
            // out dec
            System.out.println(decrypted);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
