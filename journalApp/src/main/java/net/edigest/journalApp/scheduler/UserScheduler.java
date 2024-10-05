package net.edigest.journalApp.scheduler;

import net.edigest.journalApp.entity.JournalEntity;
import net.edigest.journalApp.entity.UserEntity;
import net.edigest.journalApp.repository.UserImplRepository;
import net.edigest.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import  java.util.List;
import java.util.stream.Collectors;

@Service
public class UserScheduler {
    @Autowired
    UserImplRepository userImplRepository;

    @Autowired
    EmailService emailService;

    @Scheduled(cron = "* * * * *")
    public  List<UserEntity>getSentimantaluser()
    {
        List<UserEntity>sentimantaluser=userImplRepository.getUserForSA();
        for (UserEntity user:sentimantaluser)
        {
//            List<String>journalEntityList=user.getJournalEntityList().stream().filter(x->x.getDate().isAfter(LocalDate.now().minus(7, ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());
//           List<JournalEntity> journalEntityList=user.getJournalEntityList();
//            String join=String.join(" ",journalEntityList);
           emailService.sendMail(user.getEmail(),"Sentimants for last 7 days","Positive");
        }
        return  sentimantaluser;
    }
}
