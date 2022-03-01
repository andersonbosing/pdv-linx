/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.linx.pdvlinx.services;

import br.com.linx.pdvlinx.controllers.dto.ItemVendaDTO;
import br.com.linx.pdvlinx.controllers.dto.VendaDTO;
import br.com.linx.pdvlinx.models.Item;
import br.com.linx.pdvlinx.models.ItemVenda;
import br.com.linx.pdvlinx.models.Venda;
import br.com.linx.pdvlinx.repositories.VendaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anderson.bosing
 */
@Service
public class VendaService {
    
@Autowired
    private VendaRepository vendaRepository;
    
    @Autowired
    private ItemVendaService itemVendaService;
    
    @Autowired
    private ItemService itemService;
    
    public List<VendaDTO> findAll() {
        return VendaDTO.toDTOList(vendaRepository.findAll());
    }
    
    public Venda save(VendaDTO vendaDTO) {

        if (vendaDTO.getListaItensVenda() != null) {
            for (ItemVendaDTO itemVenda : vendaDTO.getListaItensVenda()) {
                if (itemVenda.getId() != null) {
                    itemVenda.toDTO(itemVendaService.save(itemVenda.toModel(itemVenda)));
                }
            }
        }
        
        return vendaRepository.saveAndFlush(vendaDTO.toModel(vendaDTO));

    }

    public Optional<Venda> findById(Long id) {
        return vendaRepository.findById(id);
    }
    
    public void deleteById(Long id) {
        //força delecao dos itens
        Optional<Venda> venda = findById(id);
        
        for(ItemVenda itemVenda : venda.get().getListaItensVenda()) {
            itemVendaService.deleteById(itemVenda.getId());
        }
        
        vendaRepository.deleteById(id);
    }

    public VendaDTO calcularVenda(VendaDTO vendaDTO) throws Exception {
        Double vlTotalVenda = 0.00;
        List<ItemVendaDTO> listaItensRecalculo = vendaDTO.getListaItensVenda();

        if (!listaItensRecalculo.isEmpty()) {

            if (vendaDTO.getDespesasTotais() == null) {
                vendaDTO.setDespesasTotais(400.00);
            }
            
            if (vendaDTO.getPcMargemLucro()== null) {
                vendaDTO.setPcMargemLucro(0.00);
            }

            Double valorDespesaUnitario = vendaDTO.getDespesasTotais() / listaItensRecalculo.size();

            for (ItemVendaDTO itemRecalc : listaItensRecalculo) {
                
                if (itemRecalc.getItem() == null) {
                    throw new Exception("O Item deve ser Informado para Calculo da Venda");
                }
                Item item = itemService.validaItem(itemRecalc.getItem());

                if (itemRecalc.getQuantidade() == null) {
                    throw new Exception("A Quantidade do Item "+item.getNome()+" "
                            + "deve ser Informado para Calculo da Venda");
                }
                
                itemRecalc.setVlPrecoVenda(
                        item.getCustoCompra()
                        + valorDespesaUnitario
                );

                itemRecalc.setVlPrecoVenda(
                        itemRecalc.getVlPrecoVenda()
                        * (1 + (vendaDTO.getPcMargemLucro() / 100))
                );
                
                itemRecalc.setVlTotalVenda(
                        itemRecalc.getQuantidade() * itemRecalc.getVlPrecoVenda()
                );
                
                vlTotalVenda = vlTotalVenda + itemRecalc.getVlTotalVenda();

            }
            
            vendaDTO.setVlTotal(vlTotalVenda);

        }
        
        return vendaDTO;
    
    }
    
}