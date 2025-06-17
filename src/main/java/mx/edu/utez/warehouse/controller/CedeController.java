package mx.edu.utez.warehouse.controller;

import jakarta.validation.Valid;
import mx.edu.utez.warehouse.Service.CedeService;
import mx.edu.utez.warehouse.model.Cede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cedes")
public class CedeController {

    private final CedeService cedeService;

    @Autowired
    public CedeController(CedeService cedeService) {
        this.cedeService = cedeService;
    }


    @PostMapping
    public ResponseEntity<Cede> createCede(@Valid @RequestBody Cede cede) {
        Cede createdCede = cedeService.createCede(cede);
        return new ResponseEntity<>(createdCede, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Cede>> getAllCedes() {
        List<Cede> cedes = cedeService.getAllCedes();
        return new ResponseEntity<>(cedes, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cede> getCedeById(@PathVariable Long id) {
        return cedeService.getCedeById(id)
                .map(cede -> new ResponseEntity<>(cede, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cede> updateCede(@PathVariable Long id, @Valid @RequestBody Cede cedeDetails) {
        try {
            Cede updatedCede = cedeService.updateCede(id, cedeDetails);
            return new ResponseEntity<>(updatedCede, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCede(@PathVariable Long id) {
        try {
            cedeService.deleteCede(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}