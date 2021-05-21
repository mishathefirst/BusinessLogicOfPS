package com.example.projBLSS.rabbit_service.mail;

import com.example.projBLSS.main_server.repository.UserRepository;
import com.example.projBLSS.main_server.service.ShutterstockUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;


@Profile("stats")
@Service
public class MailService{

    @Autowired
    private JavaMailSender mailSender;


    @Autowired
    private UserRepository userRepository;

    @Value("${like.goal}")
    private int likeGoal;

    public void sendMessage(String pictureName, Long id){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("pochikalin@gmail.com");
        message.setTo(this.userRepository.findByID(id).getEmail());
        message.setSubject("Количество лайков");
        message.setText("Приветствуем вас! Количество лайков, которые вы установил на картинку " + pictureName + " достигло " + likeGoal + "! Поздравляем вас");
        mailSender.send(message);

    }
}
