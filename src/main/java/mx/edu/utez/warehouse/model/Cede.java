package mx.edu.utez.warehouse.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String claveCede;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String municipio;

    @PrePersist
    public void generateClaveCede() {
        if (this.claveCede == null) {

            Random random = new Random();
            int randomDigits = 1000 + random.nextInt(9000); // 4 random digits
            String datePart = LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("ddMMyyyy"));
            this.claveCede = "C-TEMP-" + datePart + "-" + String.format("%04d", randomDigits); // TEMP placeholder
        }
    }
}