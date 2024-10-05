package net.edigest.journalApp.controller;

import net.edigest.journalApp.entity.JournalEntity;
import net.edigest.journalApp.entity.UserEntity;
import net.edigest.journalApp.service.JournalServices;
import net.edigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalController {
    @Autowired
    private JournalServices journalServices;
    @Autowired
    private UserService userService;

    @GetMapping("/getall")
    public ResponseEntity getall() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userEntity = userService.findByUsername(username);
        List<List> journalentry = Collections.singletonList(userEntity.getJournalEntityList());
        if (userEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.of(Optional.of(journalentry));
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getdata(@PathVariable("id") Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userEntity = userService.findByUsername(username);
        List<JournalEntity> journalEntities = userEntity.getJournalEntityList().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if (journalEntities.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.of(Optional.of(journalEntities));
        }
    }

    @PostMapping("/save")
    public ResponseEntity postdata(@RequestBody JournalEntity journalEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Date date = new Date();
        journalEntity.setDate(LocalDate.now());
        System.out.println(journalEntity.toString());
        if (journalServices.post(journalEntity, username)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody JournalEntity journalEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userEntity = userService.findByUsername(username);
        List<JournalEntity> journalEntityList = userEntity.getJournalEntityList().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if (!journalEntityList.isEmpty()) {
            Optional<JournalEntity> journalentry = journalServices.findById(id);
            if (journalentry.isPresent()) {
                JournalEntity old = journalentry.get();
                old.setTitle(journalEntity.getTitle());
                old.setContent(journalEntity.getContent());
                journalServices.post(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity user = userService.findByUsername(username);
        boolean removed = user.getJournalEntityList().removeIf(x -> x.getId().equals(id));
        if (removed) {
            userService.save(user);
            journalServices.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
