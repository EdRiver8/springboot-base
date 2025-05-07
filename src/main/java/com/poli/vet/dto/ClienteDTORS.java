package com.poli.vet.dto;

import com.poli.vet.entity.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTORS {
    private String nombre;
    private String telefono;
    private String email;

    public static ClienteDTORS convertirClienteAClienteDTORS(Cliente cliente) {
        return ClienteDTORS.builder()
                .nombre(cliente.getNombre())
                .telefono(cliente.getTelefono())
                .email(cliente.getEmail())
                .build();
    }

}
