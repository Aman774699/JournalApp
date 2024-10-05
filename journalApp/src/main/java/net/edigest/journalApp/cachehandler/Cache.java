package net.edigest.journalApp.cachehandler;
import jakarta.annotation.PostConstruct;
import net.edigest.journalApp.entity.APIs;
import net.edigest.journalApp.service.ApiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Cache {
@Autowired
ApiServices apiServices;
 public Map<String ,String> apis=new HashMap<>();

    @PostConstruct
    @Scheduled(cron = "*/5 * * * * *")
    public void init()
 {
     List<APIs>result=apiServices.getApis();
     for(APIs ap:result)
     {
         apis.put(ap.getApiKey(),ap.getValue());
     }
     System.out.println(apis);
  }
}
