/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Anderson
 * Created: 28/02/2022
 */
INSERT INTO ITENS(custo_compra, descricao, imagem, nome, created_at, updated_at) 
VALUES(2000.00, 'COMPUTADOR1', NULL, 'COMPUTADOR1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO ITENS(custo_compra, descricao, imagem, nome, created_at, updated_at) 
VALUES(2000.00, 'COMPUTADOR2', NULL, 'COMPUTADOR2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO ITENS(custo_compra, descricao, imagem, nome, created_at, updated_at) 
VALUES(2000.00, 'COMPUTADOR3', NULL, 'COMPUTADOR3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO VENDAS(id, despesas_totais, pc_margem_lucro, vl_total, created_at, updated_at) 
VALUES(1, 500, 10, 8000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO ITENS_VENDA(id, quantidade, vl_preco_venda, vl_total_venda, id_item)
VALUES(1, 1, 4000, 4000, 1);

INSERT INTO ITENS_VENDA(id, quantidade, vl_preco_venda, vl_total_venda, id_item)
VALUES(2, 1, 4000, 4000, 1);

INSERT INTO vendas_lista_itens_venda(venda_id, lista_itens_venda_id) 
VALUES(1,1);

INSERT INTO vendas_lista_itens_venda(venda_id, lista_itens_venda_id) 
VALUES(1,2);