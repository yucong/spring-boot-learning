package com.yucong.service;

import org.apache.shiro.crypto.RandomNumberGenerator;
//import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yucong.entity.User;

@Service
public class PasswordHelper {

    //private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Value("${password.algorithmName}")
    private String algorithmName = "md5";
    @Value("${password.hashIterations}")
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        //this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public void encryptPassword(User user) {

        /*user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();

        user.setPassword(newPassword);*/
    }
    
    
    public static void main(String[] args) {
		
    	
        String newPassword = new SimpleHash(
                "md5",
                "123456",
                ByteSource.Util.bytes("admin8d78869f470951332959580424d4bf4f"),
                2).toHex();
        // d3c59d25033dbf980d29554025c23a75
        System.out.println(newPassword);
    	
	}
}
