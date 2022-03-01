/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.linx.pdvlinx.services;

import br.com.linx.pdvlinx.controllers.dto.ItemDTO;
import br.com.linx.pdvlinx.models.Item;
import br.com.linx.pdvlinx.repositories.ItemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anderson.bosing
 */
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<ItemDTO> findAll() {

        return ItemDTO.toDTOList(itemRepository.findAll());

    }

    public List<ItemDTO> findAllVenda() {

        return ItemDTO.toDTOList(itemRepository.findAllVenda());

    }

    public List<Item> findAllInternalItens() {

        return itemRepository.findAll();

    }

    public Item save(ItemDTO itemDTO) {

        return itemRepository.saveAndFlush(itemDTO.toModel(itemDTO));

    }

    public Optional<Item> findById(Long id) {

        return itemRepository.findById(id);

    }

    public void deleteById(Long id) {

        itemRepository.deleteById(id);

    }

    public Item validaItem(ItemDTO i) throws Exception {

        Optional<Item> item = findById(i.getId());

        if (!item.isPresent()) {
            throw new Exception("Item "+i.getId()+" Não Encontrado");
        }

        return item.get();
    }

}
