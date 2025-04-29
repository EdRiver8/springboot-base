package com.poli.vet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "mascotas")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, length = 50)
    private String nombre;
    
    @Column(nullable = false, length = 30)
    private String tipo;
    
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    
    @ManyToOne
    @JoinColumn(name = "dueno_id", nullable = false)
    private Cliente dueno;
    
    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL)
    private List<Aplicacion> aplicaciones;
    
    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL)
    private List<HistorialEstado> historialEstados;
}
