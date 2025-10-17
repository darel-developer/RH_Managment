package com.example.darelo.RH.service;

import com.example.darelo.RH.model.Otp;
import com.example.darelo.RH.repository.OtpRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {
    private  final OtpRepository otpRepo;
    private final JavaMailSender mailSender;

    public OtpService(OtpRepository otpRepo, JavaMailSender mailSender) {
        this.otpRepo = otpRepo;
        this.mailSender = mailSender;
    }

    public String generateOtp(String email){
        String code = String.format("%06d", new Random().nextInt(999999));
        Otp otp = new Otp(null, email, code, LocalDateTime.now().plusMinutes(5));
        otpRepo.save(otp);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("code OTP - HR Managment");
        message.setText("Votre code de connexion est :" + code);
        mailSender.send(message);

        return code;
    }

    public boolean verifyotp(String email, String code){
        return otpRepo.findByEmail(email).filter(o -> o.getCode().equals(code) && o.getExpirationTime().isAfter(LocalDateTime.now())).isPresent();
    }
}
