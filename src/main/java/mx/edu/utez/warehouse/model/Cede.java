package mx.edu.utez.warehouse.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String claveCede;

    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-ZñÑ\\s]+$", message = "Solo se permiten letras y espacios")
    private String estado;

    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-ZñÑ\\s]+$", message = "Solo se permiten letras y espacios")
    private String municipio;

    @PrePersist
    public void generateClaveCede() {
        if (this.claveCede == null) {

            Random random = new Random();
            int randomDigits = 1000 + random.nextInt(9000);
            String datePart = LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("ddMMyyyy"));
            this.claveCede = "C-TEMP-" + datePart + "-" + String.format("%04d", randomDigits);
        }
    }
}