/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.linx.pdvlinx.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author anderson.bosing
 */
@Entity
@Table(name = "itens_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item item;

    private Integer quantidade;
    private Double vlPrecoVenda;
    private Double vlTotalVenda;

    public ItemVenda() {
    }

    public ItemVenda(Long id, Item item, Integer quantidade, Double vlPrecoVenda, Double vlTotalVenda) {
        this.id = id;
        this.item = item;
        this.quantidade = quantidade;
        this.vlPrecoVenda = vlPrecoVenda;
        this.vlTotalVenda = vlTotalVenda;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getVlPrecoVenda() {
        return vlPrecoVenda;
    }

    public void setVlPrecoVenda(Double vlPrecoVenda) {
        this.vlPrecoVenda = vlPrecoVenda;
    }

    public Double getVlTotalVenda() {
        return vlTotalVenda;
    }

    public void setVlTotalVenda(Double vlTotalVenda) {
        this.vlTotalVenda = vlTotalVenda;
    }
    
}