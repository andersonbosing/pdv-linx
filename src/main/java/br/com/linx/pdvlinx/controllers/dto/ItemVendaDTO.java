/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.linx.pdvlinx.controllers.dto;

import br.com.linx.pdvlinx.models.ItemVenda;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author anderson.bosing
 */
public class ItemVendaDTO {

    private Long id;
    private ItemDTO item;
    private Integer quantidade;
    private Double vlPrecoVenda;
    private Double vlTotalVenda;

    public ItemVendaDTO() {
    }

    public ItemVendaDTO(Long id, ItemDTO item, Integer quantidade, Double vlPrecoVenda, Double vlTotalVenda) {
        this.id = id;
        this.item = item;
        this.quantidade = quantidade;
        this.vlPrecoVenda = vlPrecoVenda;
        this.vlTotalVenda = vlTotalVenda;
    }

    public Long getId() {
        return id;
    }

    public ItemDTO getItem() {
        return item;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Double getVlPrecoVenda() {
        return vlPrecoVenda;
    }

    public Double getVlTotalVenda() {
        return vlTotalVenda;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setVlPrecoVenda(Double vlPrecoVenda) {
        this.vlPrecoVenda = vlPrecoVenda;
    }

    public void setVlTotalVenda(Double vlTotalVenda) {
        this.vlTotalVenda = vlTotalVenda;
    }
    
    public static ItemVendaDTO toDTO(ItemVenda item) {

        return new ItemVendaDTO(
                item.getId(),
                ItemDTO.toDTO(item.getItem()),
                item.getQuantidade(),
                item.getVlPrecoVenda(),
                item.getVlTotalVenda()
        );

    }

    public static List<ItemVendaDTO> toDTOList(List<ItemVenda> listaItens) {

        List<ItemVendaDTO> resultList = new ArrayList<>();

        for (ItemVenda item : listaItens) {
            resultList.add(toDTO(item));
        }

        return resultList;

    }

    public static ItemVenda toModel(ItemVendaDTO item) {

        return new ItemVenda(
                item.getId(),
                ItemDTO.toModel(item.getItem()),
                item.getQuantidade(),
                item.getVlPrecoVenda(),
                item.getVlTotalVenda()
        );

    }

    public static List<ItemVenda> toModelList(List<ItemVendaDTO> listaItensVenda) {

        List<ItemVenda> resultList = new ArrayList<>();

        for (ItemVendaDTO item : listaItensVenda) {
            resultList.add(toModel(item));
        }

        return resultList;

    }

}
