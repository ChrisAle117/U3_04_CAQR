package mx.edu.utez.warehouse.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cede_id", nullable = false)
    private Cede cede;

    @Column(unique = true, nullable = false)
    private String claveAlmacen;

    private LocalDate fechaRegistro;

    @Column(nullable = false)
    private Double precioVenta;

    private Double precioRenta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Size tamaño;

    public enum Size {
        G, M, P
    }

    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDate.now();
       if (this.claveAlmacen == null && this.cede != null) {
            this.claveAlmacen = this.cede.getClaveCede() + "-A-TEMP";
        }
    }
}