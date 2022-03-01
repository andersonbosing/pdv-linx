/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.linx.pdvlinx.controllers.dto;

import br.com.linx.pdvlinx.models.Item;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author anderson.bosing
 */
public class ItemDTO {

    private Long id;
    @NotNull
    @NotEmpty
    private String nome;
    @NotNull
    @NotEmpty
    @Size(max = 200)
    private String descricao;
    @NotNull
    @NotEmpty
    private String imagem;
    @NotNull
    private Double custoCompra;
    private Date creatAt;
    private Date upadatedAt;

    public ItemDTO() {
    }

    public ItemDTO(Long id, String nome, String descricao, String imagem, Double custoCompra, Date creatAt, Date upadatedAt) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.custoCompra = custoCompra;
        this.creatAt = creatAt;
        this.upadatedAt = upadatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public Double getCustoCompra() {
        return custoCompra;
    }


    public Date getCreatAt() {
        return creatAt;
    }

    public Date getUpadatedAt() {
        return upadatedAt;
    }

    public static ItemDTO toDTO(Item item) {

        return new ItemDTO(item.getId(), item.getNome(), item.getDescricao(),
                item.getImagem(), item.getCustoCompra(),
                item.getCreatAt(), item.getUpadatedAt());

    }

    public static List<ItemDTO> toDTOList(List<Item> listaItens) {

        List<ItemDTO> resultList = new ArrayList<>();

        for (Item item : listaItens) {
            resultList.add(toDTO(item));
        }

        return resultList;

    }

    public static Item toModel(ItemDTO itemDTO) {

        return new Item(itemDTO.getId(), itemDTO.getNome(), itemDTO.getDescricao(),
                itemDTO.getImagem(), itemDTO.getCustoCompra(), 
                itemDTO.getCreatAt(), itemDTO.getUpadatedAt());

    }

}
