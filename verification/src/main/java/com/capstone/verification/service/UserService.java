package com.capstone.verification.service;

import com.capstone.verification.model.User;
import com.capstone.verification.model.UserDTO;
import com.capstone.verification.model.VerificationCodes;
import com.capstone.verification.repository.UserRepository;
import com.capstone.verification.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final VerificationCodeRepository verificationCodeRepository;
    private final VerificationSender verificationSender;
    public UserDTO saveUser(String number, String password) throws Exception {
        String code =generateRandomString(8);

        VerificationCodes verificationCode = new VerificationCodes(number,code);
        verificationCode = verificationCodeRepository.save(verificationCode);

        boolean sent = verificationSender.sendVerificationCode(number, code);
        if(!sent){
            verificationCodeRepository.delete(verificationCode);
            throw new Exception("Failed to send the verification code!");
        }

        User user = new User(number, password, false);
        user = userRepository.save(user);

        return new UserDTO(user.getId().toString(), user.getNumber(), user.isVerified());
    }

    public UserDTO verifyUser(String number, String code) throws Exception {
        User user = userRepository.findUserByNumber(number).orElse(null);
        if(user == null)
            throw new Exception("failed to find user with the supported number");

        VerificationCodes verificationCode = verificationCodeRepository.findCodeByNumber(number).orElse(null);
        if(verificationCode == null)
            throw new Exception("Number has no code");
        if(!code.equals(verificationCode.getCode()))
            throw new Exception("Failed to verify the number the code is incorrect");
        user.setVerified(true);
        user = userRepository.save(user);
        return new UserDTO(user.getId().toString(), user.getNumber(), user.isVerified());
    }

    public List<UserDTO> getUsers(){
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for(User user : users)
            userDTOS.add(new UserDTO(user.getId().toString(),user.getNumber(), user.isVerified()));
        return userDTOS;
    }

    public static String generateRandomString(int length) {
        // Define the character set from which to generate the random string
        String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Create a StringBuilder to build the random string
        StringBuilder randomStringBuilder = new StringBuilder();

        // Create an instance of the Random class
        Random random = new Random();

        // Generate the random string by appending random characters from the charset
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charset.length());
            char randomChar = charset.charAt(randomIndex);
            randomStringBuilder.append(randomChar);
        }

        // Convert StringBuilder to String and return
        return randomStringBuilder.toString();
    }
}
