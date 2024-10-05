package net.edigest.journalApp.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JournalEntity {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    @NotNull
    private String title;
    @NotNull
    private String content;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "user_id" ,nullable = false)
    @JsonBackReference
    private UserEntity userEntity;

}
