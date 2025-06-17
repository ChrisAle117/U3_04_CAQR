package mx.edu.utez.warehouse.Service;


import mx.edu.utez.warehouse.model.Almacen;
import mx.edu.utez.warehouse.model.Cede;
import mx.edu.utez.warehouse.repository.AlmacenRepository;
import mx.edu.utez.warehouse.repository.CedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlmacenService {

    private final AlmacenRepository almacenRepository;
    private final CedeRepository cedeRepository;

    @Autowired
    public AlmacenService(AlmacenRepository almacenRepository, CedeRepository cedeRepository) {
        this.almacenRepository = almacenRepository;
        this.cedeRepository = cedeRepository;
    }


    public Almacen createAlmacen(Almacen almacen) {

        Cede cede = cedeRepository.findById(almacen.getCede().getId())
                .orElseThrow(() -> new RuntimeException("Cede not found with id " + almacen.getCede().getId()));
        almacen.setCede(cede);

        Almacen savedAlmacen = almacenRepository.save(almacen);
        savedAlmacen.setClaveAlmacen(savedAlmacen.getCede().getClaveCede() + "-A" + savedAlmacen.getId());
        return almacenRepository.save(savedAlmacen);
    }


    public List<Almacen> getAllAlmacenes() {
        return almacenRepository.findAll();
    }


    public Optional<Almacen> getAlmacenById(Long id) {
        return almacenRepository.findById(id);
    }


    public Almacen updateAlmacen(Long id, Almacen almacenDetails) {
        Almacen almacen = almacenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Almacen not found with id " + id));


        almacen.setPrecioVenta(almacenDetails.getPrecioVenta());
        almacen.setPrecioRenta(almacenDetails.getPrecioRenta());
        almacen.setTamaño(almacenDetails.getTamaño());


        if (almacenDetails.getCede() != null && !almacen.getCede().getId().equals(almacenDetails.getCede().getId())) {
            Cede newCede = cedeRepository.findById(almacenDetails.getCede().getId())
                    .orElseThrow(() -> new RuntimeException("New Cede not found with id " + almacenDetails.getCede().getId()));
            almacen.setCede(newCede);
        }



        return almacenRepository.save(almacen);
    }


    public void deleteAlmacen(Long id) {
        if (!almacenRepository.existsById(id)) {
            throw new RuntimeException("Almacen not found with id " + id);
        }
        almacenRepository.deleteById(id);
    }
}