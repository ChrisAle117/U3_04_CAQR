package mx.edu.utez.warehouse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre completo no puede estar vacío")
    @Size(max = 255, message = "El nombre completo no puede exceder 255 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s.]+$", message = "El nombre completo solo puede contener letras, espacios y puntos")
    private String nombreCompleto;

    @Column(nullable = false)
    @NotBlank(message = "El número de teléfono no puede estar vacío")
    @Size(min = 10, max = 20, message = "El número de teléfono debe tener entre 10 y 20 dígitos")
    @Pattern(regexp = "^[0-9]+$", message = "El número de teléfono solo puede contener dígitos")
    private String numeroTelefono;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El formato del correo electrónico no es válido")
    @Size(max = 255, message = "El correo electrónico no puede exceder 255 caracteres")
    private String correoElectronico;
}