/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.linx.pdvlinx.controllers;

import br.com.linx.pdvlinx.controllers.dto.VendaDTO;
import br.com.linx.pdvlinx.models.Venda;
import br.com.linx.pdvlinx.services.VendaService;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author anderson.bosing
 */
@RestController
@RequestMapping("vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;
    
    @GetMapping
    public List<VendaDTO> listar() {
        return vendaService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> detalhar(@PathVariable Long id) {
        Optional<Venda> venda = vendaService.findById(id);
        if (venda.isPresent()) {
            return ResponseEntity.ok(VendaDTO.toDTO(venda.get()));
        }

        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/calcular")
    public ResponseEntity<VendaDTO> calcularVenda(@RequestBody @Valid VendaDTO vendaDTO) throws Exception {
        
        return ResponseEntity.ok(vendaService.calcularVenda(vendaDTO));

    }
    
    @PostMapping
    @Transactional
    public ResponseEntity<VendaDTO> inserir(@RequestBody @Valid VendaDTO vendaDTO,
            UriComponentsBuilder uriBuilder) {

        Venda venda = vendaService.save(vendaDTO);

        URI uri = uriBuilder.path("/vendas/{id}").buildAndExpand(venda.getId()).toUri();
        return ResponseEntity.created(uri).body(vendaDTO.toDTO(venda));

    }
    
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VendaDTO> atualizar(@PathVariable Long id, 
            @RequestBody @Valid VendaDTO vendaDTO) {

        Optional<Venda> optional = vendaService.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(VendaDTO.toDTO(vendaService.save(vendaDTO)));
        }

        return ResponseEntity.notFound().build();

    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        Optional<Venda> optional = vendaService.findById(id);
        if (optional.isPresent()) {
            vendaService.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }
}
