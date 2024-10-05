package net.edigest.journalApp.service;

import net.edigest.journalApp.entity.APIs;
import net.edigest.journalApp.entity.UserEntity;
import net.edigest.journalApp.repository.APIsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.HashMap;

@Service
public class ApiServices {
    @Autowired
    APIsRepository apIsRepository;

    public  List<APIs> getApis()
    {
      List<APIs> api=apIsRepository.findAll();
      return api;
    }
}
