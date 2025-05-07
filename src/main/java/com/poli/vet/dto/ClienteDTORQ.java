package com.poli.vet.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTORQ {

    @NotBlank(message = "El campo nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @Pattern(regexp = "^[0-9]{10}$", message = "Debe tener 10 digitos")
    private String telefono;

    @Email(message = "Debe ser en formato correo usando @")
    private String email;

    @Size(max = 150, message = "La direccion no puede tener mas de 150 caracteres")
    private String direccion;

}
