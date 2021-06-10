package sogne.maja.eksamen.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sogne.maja.eksamen.model.Commune;
import sogne.maja.eksamen.model.Parish;
import sogne.maja.eksamen.repository.CommuneRepository;
import sogne.maja.eksamen.repository.ParishRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ParishRestController {

    private ParishRepository parishRepository;


    public ParishRestController(ParishRepository parishRepository) {
        this.parishRepository = parishRepository;
    }


    // HTTP Get List
    @GetMapping("/parish")
    public ResponseEntity<List<Parish>> findAllParish() {
        //findAll parishes
        List<Parish> parishList = new ArrayList<>();
        for (Parish parish : parishRepository.findAll()) {
            parishList.add(parish);
        }
        return ResponseEntity.status(HttpStatus.OK).body(parishList);
    }


    // HTTP Get by ID
    @GetMapping("/parish/{id}")
    public ResponseEntity<Optional<Parish>> findById(@PathVariable Long id)
    {
        Optional<Parish> optionalParish = parishRepository.findById(id);
        if (optionalParish.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalParish);
        }
        else{
            //Not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(optionalParish);
        }
    }

    // HTTP Post, ie. create
    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping(value = "/parish", consumes = "application/json")
    public ResponseEntity<Parish> create(@RequestBody Parish parish) {
        Parish newParish = parishRepository.save(parish);
        //insert location in response header
        return ResponseEntity.ok(newParish);
    }

    //HTTP PUT, ie. update
    @PutMapping("/parish/{id}")
    public ResponseEntity<String> update(@PathVariable("id") long id, @RequestBody Parish parish) {
        Optional<Parish> optionalParish = parishRepository.findById(id);
        if (!optionalParish.isPresent()) {
            //id findes ikke
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg' : 'parish " + id + " not found'}");
        }
        parish.setId(id);
        parishRepository.save(parish);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{ 'msg' : 'updated' }");

    }

    //HTTP DELETE
    @DeleteMapping("/parish/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        Optional<Parish> optionalParish = parishRepository.findById(id);
        if (!optionalParish.isPresent()) {
            //id findes ikke
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg' : 'parish " + id + " not found'}");
        }
        parishRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{ 'msg' : 'deleted' }");
    }

}
