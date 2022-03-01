/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.linx.pdvlinx.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
/**
 *
 * @author anderson.bosing
 */
@Entity
@Table(name = "itens")
@EntityListeners(AuditingEntityListener.class)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(length = 200)
    private String descricao;
    private String imagem;
    private Double custoCompra;
    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(nullable = false)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Item() {
    }

    public Item(Long id, String nome, String descricao, String imagem, Double custoCompra, Date creatAt, Date upadatedAt) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.custoCompra = custoCompra;
        this.createdAt = creatAt;
        this.updatedAt = upadatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Double getCustoCompra() {
        return custoCompra;
    }

    public void setCustoCompra(Double custoCompra) {
        this.custoCompra = custoCompra;
    }

    public Date getCreatAt() {
        return createdAt;
    }

    public void setCreatAt(Date creatAt) {
        this.createdAt = creatAt;
    }

    public Date getUpadatedAt() {
        return updatedAt;
    }

    public void setUpadatedAt(Date upadatedAt) {
        this.updatedAt = upadatedAt;
    }

}
