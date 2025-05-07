package com.poli.vet.controller;

import com.poli.vet.dto.ClienteDTORQ;
import com.poli.vet.dto.ClienteDTORS;
import com.poli.vet.entity.Cliente;
import com.poli.vet.service.IClienteService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final IClienteService clienteService;

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/guardar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente guardado"),
            @ApiResponse(responseCode = "400", description = "Error al guardar el cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public void guardarCliente(@Valid @RequestBody ClienteDTORQ clienteDto){
        Cliente cliente = convertirClienteDTORQACliente(clienteDto);
        clienteService.guardarCliente(cliente);
    }

    @GetMapping("/salud")
    public String salud(){
        return "Estoy OK!";
    }

    @GetMapping("/motrar-todos")
    public List<ClienteDTORS> listarClientes(){
        return clienteService.obtenerClientes();
    }

    @GetMapping("/motrar-todos-paginado")
    public List<ClienteDTORS> listarClientesPaginados(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size){
        List<ClienteDTORS> clienteDTORS = clienteService.obtenerClientesPaginados(page, size);
        return clienteDTORS;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Integer id){
        Cliente cliente = clienteService.encontrarPorId(id);
        if(cliente == null){
            return ResponseEntity.notFound().build();
        }
         return ResponseEntity.ok(cliente);
    }

    @GetMapping("/mascota/{id}")
    public Cliente obtenerClientePorIdConMascotas(@PathVariable Integer id){
        return clienteService.obtenerClientePorIdConMascotaAsociada(id);
    }

    private Cliente convertirClienteDTORQACliente(ClienteDTORQ clienteDTORQ){
        return Cliente.builder()
                .nombre(clienteDTORQ.getNombre())
                .email(clienteDTORQ.getEmail())
                .telefono(clienteDTORQ.getTelefono())
                .direccion(clienteDTORQ.getDireccion())
                .build();
    }
}
