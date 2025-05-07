package com.poli.vet.service;

import com.poli.vet.dto.ClienteDTORS;
import com.poli.vet.entity.Cliente;
import com.poli.vet.entity.Mascota;
import com.poli.vet.repository.IClienteRepository;
import com.poli.vet.repository.IMascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {
    // usamos inyeccion de dependencias
    @Autowired
    private IClienteRepository clienteRepository;
    @Autowired
    private IMascotaRepository mascotaRepository;

    @Override
    public void eliminarCliente(Integer id) {

    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente encontrarPorId(Integer id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if(cliente != null){
            System.out.println("Cliente encontrado: " + cliente.getNombre());
            System.out.println("Primera mascota del cliente: " + cliente.getMascotas().get(0).getNombre());
            return cliente;
        }
        return null;
    }

    @Override
    public Cliente obtenerClientePorIdConMascotaAsociada(Integer id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if(cliente == null){
            return null;
        }
        // buscamos en mascotas los clientes cuyo cliente_id = id
        List<Mascota> mascotas = mascotaRepository.findMascotaByClienteId(id);
        cliente.setMascotas(mascotas);
        return cliente;
    }

    @Override
    public List<ClienteDTORS> obtenerClientes() {
        List<Cliente> clientes = clienteRepository.findAll();

        List<ClienteDTORS> clientesDTORS = new ArrayList<>();
        clientes.forEach(cliente -> {
            clientesDTORS.add(ClienteDTORS.convertirClienteAClienteDTORS(cliente));
        });
        return clientesDTORS;
    }

    @Override
    public List<ClienteDTORS> obtenerClientesPaginados(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
//        return clienteRepository.findAll(pageable).map(ClienteDTORS::convertirClienteAClienteDTORS).getContent();
        return clienteRepository.findAll(pageable).getContent().stream().map(ClienteDTORS::convertirClienteAClienteDTORS).toList();
    }
}
