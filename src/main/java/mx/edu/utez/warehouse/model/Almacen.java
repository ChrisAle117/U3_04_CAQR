package mx.edu.utez.warehouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import mx.edu.utez.warehouse.model.Cede;

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
    @NotNull(message = "El almacén debe estar asociado a una cede")
    private Cede cede;

    @Column(unique = true, nullable = false)
    private String claveAlmacen;

    private LocalDate fechaRegistro;

    @Column(nullable = false)
    @NotNull(message = "El precio de venta no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio de venta debe ser mayor a 0")
    private Double precioVenta;

    @NotNull(message = "El precio de renta no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio de renta debe ser mayor a 0")
    private Double precioRenta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "El tamaño del almacén no puede ser nulo")
    private Size tamaño;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_asignado_id")
    @JsonIgnore
    private Cliente clienteAsignado;

    @Enumerated(EnumType.STRING)
    private TipoOperacion tipoOperacion;

    public enum Size {
        G, M, P
    }

    public enum TipoOperacion {
        VENTA, RENTA
    }

    @PrePersist
    @PreUpdate
    public void generateOrUpdateClaveAlmacen() {
        this.fechaRegistro = LocalDate.now();
        if (this.id != null && this.cede != null && (this.claveAlmacen == null || this.claveAlmacen.contains("TEMP"))) {
            this.claveAlmacen = this.cede.getClaveCede() + "-A" + this.id;
        } else if (this.id == null && this.cede != null && this.claveAlmacen == null) {
            this.claveAlmacen = this.cede.getClaveCede() + "-A-TEMP"; // Placeholder
        }
    }
}