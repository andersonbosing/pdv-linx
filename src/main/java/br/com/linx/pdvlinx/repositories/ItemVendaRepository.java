/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.linx.pdvlinx.repositories;

import br.com.linx.pdvlinx.models.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anderson.bosing
 */
@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {

    @Modifying
    @Query(value = "delete from vendas_lista_itens_venda "
            + "where venda_id = ? and "
            + "lista_itens_venda_id = ?",
            nativeQuery = true
    )
    public void deletarAssociacao(Long idVenda, Long idItem);
    

    @Query(value = "SELECT venda_id FROM VENDAS_LISTA_ITENS_VENDA "
            + "WHERE lista_itens_venda_id = ?",
            nativeQuery = true
    )
    public Integer buscaVenda(Long idItem);

}
