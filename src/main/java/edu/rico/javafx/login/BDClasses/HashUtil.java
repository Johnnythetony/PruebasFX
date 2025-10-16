package edu.rico.javafx.login.BDClasses;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil
{
    private final static String salt = "e3a15c9fb2473d886f2ac4d9107b6e552fd08a3bc74e91f26d58aa339fc01172";

    public static String hashPassword(String password)
    {
        String saltedPassword = password + salt;
        MessageDigest md;

        try
        {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e)
        {
            return null;
        }

        return bytesToHex(md.digest(saltedPassword.getBytes()));
    }

    private static String bytesToHex(byte[] bytes)
    {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
        {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
