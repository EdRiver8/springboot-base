package com.poli.vet.service;

import com.poli.vet.dto.ClienteDTORS;
import com.poli.vet.entity.Cliente;
import com.poli.vet.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IClienteService {
    void eliminarCliente(Integer id);
    Cliente guardarCliente(Cliente cliente);
    Cliente encontrarPorId(Integer id);
    Cliente obtenerClientePorIdConMascotaAsociada(Integer id);
    List<ClienteDTORS> obtenerClientes();
    List<ClienteDTORS> obtenerClientesPaginados(Integer page, Integer size);
}
