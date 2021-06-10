package sogne.maja.eksamen.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sogne.maja.eksamen.model.Commune;
import sogne.maja.eksamen.model.Parish;
import sogne.maja.eksamen.repository.CommuneRepository;
import sogne.maja.eksamen.repository.ParishRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CommuneRestController {

    private CommuneRepository communeRepository;


    public CommuneRestController(CommuneRepository communeRepository) {
        this.communeRepository = communeRepository;
    }


    // HTTP Get List
    @GetMapping("/commune")
    public ResponseEntity<List<Commune>> findAll() {
        //findAll communes and return
        List<Commune> communeList = new ArrayList<>();
        for (Commune commune : communeRepository.findAll()) {
            communeList.add(commune);
        }
        return ResponseEntity.status(HttpStatus.OK).body(communeList);
    }


    // HTTP Get by ID
    @GetMapping("/commune/{id}")
    public ResponseEntity<Optional<Commune>> findById(@PathVariable Long id)
    {
        Optional<Commune> optionalCommune = communeRepository.findById(id);
        if (optionalCommune.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalCommune);
        }
        else{
            //Not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(optionalCommune);
        }
    }

    // HTTP Post, ie. create
    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping(value = "/commune", consumes = "application/json")
    public ResponseEntity<Commune> create(@RequestBody Commune commune) {
        Commune newCommune = communeRepository.save(commune);
        //insert location in response header
        return ResponseEntity.ok(newCommune);
    }


    //HTTP PUT, ie. update
    @PutMapping("/commune/{id}")
    public ResponseEntity<String> update(@PathVariable("id") long id, @RequestBody Commune commune) {
        Optional<Commune> optionalCommune = communeRepository.findById(id);
        if (!optionalCommune.isPresent()) {
            //id findes ikke
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg' : 'commune " + id + " not found'}");
        }
        commune.setId(id);
        communeRepository.save(commune);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{ 'msg' : 'updated' }");

    }

    //HTTP DELETE
    @DeleteMapping("/commune/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        Optional<Commune> optionalCommune = communeRepository.findById(id);
        if (!optionalCommune.isPresent()) {
            //id findes ikke
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg' : 'commune " + id + " not found'}");
        }
        communeRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{ 'msg' : 'deleted' }");
    }

}
