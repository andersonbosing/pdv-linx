/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.linx.pdvlinx.controllers;

import br.com.linx.pdvlinx.controllers.dto.ItemDTO;
import br.com.linx.pdvlinx.models.Item;
import br.com.linx.pdvlinx.services.ItemService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author anderson.bosing
 */
@RestController
@RequestMapping("/itens")
public class ItemController {
    
    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<ItemDTO> listAll(@RequestParam Optional<Boolean> toVenda) {
        if (toVenda.isPresent()){
            if (toVenda.get()) {
                return itemService.findAllVenda();
            } else {
                return itemService.findAll();
            }
            
        } else { 
            return itemService.findAll();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> detalhar(@PathVariable Long id) {
        Optional<Item> item = itemService.findById(id);
        if (item.isPresent()) {
            return ResponseEntity.ok(ItemDTO.toDTO(item.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ItemDTO> inserir(@RequestBody @Valid ItemDTO itemDTO,
            UriComponentsBuilder uriBuilder) {

        Item item = itemService.save(itemDTO);

        URI uri = uriBuilder.path("/itens/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(itemDTO.toDTO(item));

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ItemDTO> atualizar(@PathVariable Long id,
            @RequestBody @Valid ItemDTO itemDTO) {

        Optional<Item> optional = itemService.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(ItemDTO.toDTO(itemService.save(itemDTO)));
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        Optional<Item> optional = itemService.findById(id);
        if (optional.isPresent()) {
            itemService.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }

}
