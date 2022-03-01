/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.linx.pdvlinx.controllers.dto;

import br.com.linx.pdvlinx.models.Venda;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
/**
 *
 * @author anderson.bosing
 */
public class VendaDTO {

    private Long id;
    private Date createdAt;
    private Date updatedAt;
    @Min(value = 0)
    private Double despesasTotais;
    @Min(value = 0)
    private Double pcMargemLucro;
    @Min(value = 0)
    private Double vlTotal;
    @NotNull @NotEmpty
    private List<ItemVendaDTO> listaItensVenda;

    public VendaDTO() {
    }

    public VendaDTO(Long id, Date createdAt, Date updatedAt, Double despesasTotais, Double pcMargemLucro, Double vlTotal, List<ItemVendaDTO> listaItensVenda) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.despesasTotais = despesasTotais;
        this.pcMargemLucro = pcMargemLucro;
        this.vlTotal = vlTotal;
        this.listaItensVenda = listaItensVenda;
    }

    public Long getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public List<ItemVendaDTO> getListaItensVenda() {
        return listaItensVenda;
    }

    public Double getDespesasTotais() {
        return despesasTotais;
    }

    public Double getPcMargemLucro() {
        return pcMargemLucro;
    }

    public Double getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(Double vlTotal) {
        this.vlTotal = vlTotal;
    }

    public void setDespesasTotais(Double despesasTotais) {
        this.despesasTotais = despesasTotais;
    }

    public void setPcMargemLucro(Double pcMargemLucro) {
        this.pcMargemLucro = pcMargemLucro;
    }
    
    public static VendaDTO toDTO(Venda venda) {
        
        return new VendaDTO(
                venda.getId(),
                venda.getCreatedAt(),
                venda.getUpdatedAt(),
                venda.getDespesasTotais(),
                venda.getPcMargemLucro(),
                venda.getVlTotal(),
                ItemVendaDTO.toDTOList(venda.getListaItensVenda())
        );
        
    }
  
    public static Venda toModel(VendaDTO venda) {
        
        return new Venda(
                venda.getId(),
                venda.getCreatedAt(),
                venda.getUpdatedAt(),
                venda.getDespesasTotais(),
                venda.getPcMargemLucro(),
                venda.getVlTotal(),
                ItemVendaDTO.toModelList(venda.getListaItensVenda())
        );
        
    }
    
    public static List<VendaDTO> toDTOList(List<Venda> listaVendas) {
    
        List<VendaDTO> resultList = new ArrayList<>();

        for (Venda venda : listaVendas) {
            resultList.add(toDTO(venda));
        }
        
        return resultList;
    
    }
    
    
}
