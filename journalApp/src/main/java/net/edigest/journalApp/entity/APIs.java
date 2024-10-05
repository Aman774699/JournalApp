package net.edigest.journalApp.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class APIs {
    @Id
    @Column(name = "api_key", nullable = false) // Rename to avoid using "key"
    private String apiKey;

    private String value;
}
