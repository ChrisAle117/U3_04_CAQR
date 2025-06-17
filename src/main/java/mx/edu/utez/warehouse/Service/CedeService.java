package mx.edu.utez.warehouse.Service;

import mx.edu.utez.warehouse.model.Cede;
import mx.edu.utez.warehouse.repository.CedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CedeService {

    private final CedeRepository cedeRepository;

    @Autowired
    public CedeService(CedeRepository cedeRepository) {
        this.cedeRepository = cedeRepository;
    }




    public Cede createCede(Cede cede) {
        Cede savedCede = cedeRepository.save(cede);
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        Random random = new Random();
        int randomDigits = 1000 + random.nextInt(9000);
        savedCede.setClaveCede("C" + savedCede.getId() + "-" + datePart + "-" + String.format("%04d", randomDigits));
        return cedeRepository.save(savedCede);
    }


    public List<Cede> getAllCedes() {
        return cedeRepository.findAll();
    }


    public Optional<Cede> getCedeById(Long id) {
        return cedeRepository.findById(id);
    }


    public Cede updateCede(Long id, Cede cedeDetails) {
        Cede cede = cedeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cede not found with id " + id));

        cede.setEstado(cedeDetails.getEstado());
        cede.setMunicipio(cedeDetails.getMunicipio());


        return cedeRepository.save(cede);
    }


    public void deleteCede(Long id) {
        if (!cedeRepository.existsById(id)) {
            throw new RuntimeException("Cede not found with id " + id);
        }
        cedeRepository.deleteById(id);
    }
}