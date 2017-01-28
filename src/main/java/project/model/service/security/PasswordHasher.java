package project.model.service.security;

import org.apache.log4j.Logger;
import project.util.LoggerMessage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {
    private PasswordHasher(){}
    private static final Logger logger = Logger.getLogger(PasswordHasher.class);


    public static String hash(String password) {
       String  pass = password.replaceAll("[ ]","");
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte[] bytes = md.digest();
            for (byte b : bytes) {
                sb.append(Integer.toHexString(b & 0xff));
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error(LoggerMessage.ERROR_ENCRYPT);
            throw new RuntimeException(LoggerMessage.ERROR_ENCRYPT, e);
        }
        return sb.toString();
    }

}
