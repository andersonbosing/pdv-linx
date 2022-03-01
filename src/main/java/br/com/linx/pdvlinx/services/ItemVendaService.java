/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.linx.pdvlinx.services;

import br.com.linx.pdvlinx.models.ItemVenda;
import br.com.linx.pdvlinx.repositories.ItemVendaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anderson
 */
@Service
public class ItemVendaService {
    
    @Autowired
    private ItemVendaRepository itemVendaRepository;
    
    public ItemVenda save(ItemVenda item) {
        return itemVendaRepository.saveAndFlush(item);
    }
    
    public void deleteById(Long id) {
        
        Long idVenda = new Long(itemVendaRepository.buscaVenda(id));
        itemVendaRepository.deletarAssociacao(idVenda, id);
        itemVendaRepository.deleteById(id);
    }

    public Optional<ItemVenda> findById(Long id) {
        return itemVendaRepository.findById(id);
    }
    
}
