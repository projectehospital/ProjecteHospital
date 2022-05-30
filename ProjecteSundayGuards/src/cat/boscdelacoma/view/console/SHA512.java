 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.view.console;

import cat.boscdelacoma.controller.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author grabu
 */
    
public class SHA512 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter message: ");
        String input = in.nextLine();

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(input.getBytes());

        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest){
            sb.append(String.format("%02x", b & 0xff));
        }

        System.out.println("SHA 512 Hash: " + sb.toString());
    }
}
         
