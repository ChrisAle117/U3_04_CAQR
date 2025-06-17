package mx.edu.utez.warehouse.Service;


import mx.edu.utez.warehouse.model.Cliente;
import mx.edu.utez.warehouse.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public Cliente createCliente(Cliente cliente) {

        return clienteRepository.save(cliente);
    }


    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }


    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }


    public Cliente updateCliente(Long id, Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente not found with id " + id));

        cliente.setNombreCompleto(clienteDetails.getNombreCompleto());
        cliente.setNumeroTelefono(clienteDetails.getNumeroTelefono());
        cliente.setCorreoElectronico(clienteDetails.getCorreoElectronico());

        return clienteRepository.save(cliente);
    }


    public void deleteCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente not found with id " + id);
        }
        clienteRepository.deleteById(id);
    }
}