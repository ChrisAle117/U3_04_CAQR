package mx.edu.utez.warehouse.controller;


import jakarta.validation.Valid;
import mx.edu.utez.warehouse.Service.AlmacenService;
import mx.edu.utez.warehouse.model.Almacen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/almacenes")
public class AlmacenController {

    private final AlmacenService almacenService;

    @Autowired
    public AlmacenController(AlmacenService almacenService) {
        this.almacenService = almacenService;
    }


    @PostMapping
    public ResponseEntity<Almacen> createAlmacen(@Valid @RequestBody Almacen almacen) {
        try {
            Almacen createdAlmacen = almacenService.createAlmacen(almacen);
            return new ResponseEntity<>(createdAlmacen, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping
    public ResponseEntity<List<Almacen>> getAllAlmacenes() {
        List<Almacen> almacenes = almacenService.getAllAlmacenes();
        return new ResponseEntity<>(almacenes, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Almacen> getAlmacenById(@PathVariable Long id) {
        return almacenService.getAlmacenById(id)
                .map(almacen -> new ResponseEntity<>(almacen, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Almacen> updateAlmacen(@PathVariable Long id, @Valid @RequestBody Almacen almacenDetails) {
        try {
            Almacen updatedAlmacen = almacenService.updateAlmacen(id, almacenDetails);
            return new ResponseEntity<>(updatedAlmacen, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlmacen(@PathVariable Long id) {
        try {
            almacenService.deleteAlmacen(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
