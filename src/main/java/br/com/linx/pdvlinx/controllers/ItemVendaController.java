/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.linx.pdvlinx.controllers;

import br.com.linx.pdvlinx.models.ItemVenda;
import br.com.linx.pdvlinx.services.ItemVendaService;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Anderson
 */
@RestController
@RequestMapping("/itensvenda")
public class ItemVendaController {
    
    @Autowired
    private ItemVendaService itemVendaService;
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        Optional<ItemVenda> optional = itemVendaService.findById(id);
        if (optional.isPresent()) {
            itemVendaService.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }
    
}
