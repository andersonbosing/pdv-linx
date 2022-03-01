package br.com.linx.pdvlinx;

import br.com.linx.pdvlinx.controllers.dto.ItemDTO;
import br.com.linx.pdvlinx.controllers.dto.ItemVendaDTO;
import br.com.linx.pdvlinx.controllers.dto.VendaDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PdvLinxApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void validaEndPointListarItens() throws Exception {
        //Testa a manutenção dos endpoints para evitar crashes no front end
        mockMvc.perform(MockMvcRequestBuilders.
                get("/itens")).
                andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void validaEndPointListarVendas() throws Exception {
        //Testa a manutenção dos endpoints para evitar crashes no front end
        mockMvc.perform(MockMvcRequestBuilders.
                get("/vendas")).
                andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void validaInsercaoItem() throws Exception {

        ItemDTO itemDTO = new ItemDTO(null,
                "Teste", "Teste", "Teste",
                600.00, null, null);

        mockMvc.perform(post("/itens")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(itemDTO)))
                .andExpect(status().isCreated());

    }

    @Test
    void validaNaoInsercaoItem() throws Exception {
        //testa BeanValidation
        ItemDTO itemDTO = new ItemDTO(null,
                "", "", "",
                600.00, null, null);

        mockMvc.perform(post("/itens")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(itemDTO)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void validaCalculoVenda() throws Exception {
        //testa Metodo de Calculo
        ItemDTO itemA = new ItemDTO(new Long(1), null, null, null, null, null, null);
        ItemDTO itemB = new ItemDTO(new Long(2), null, null, null, null, null, null);

        List<ItemVendaDTO> listaItensVenda = new ArrayList<>();
        listaItensVenda.add(
                new ItemVendaDTO(null, itemA, 1, null, null)
        );
        listaItensVenda.add(
                new ItemVendaDTO(null, itemB, 2, null, null)
        );

        VendaDTO vendaDTO = new VendaDTO(null,
                null, null, 600.00, 20.00, null, listaItensVenda);

        MvcResult result = mockMvc.perform(get("/vendas/calcular")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(vendaDTO))).andReturn();

        String json = result.getResponse().getContentAsString();
        VendaDTO vendaCalculada = new ObjectMapper().readValue(json, VendaDTO.class);
        
        Assertions.assertTrue(vendaCalculada.getVlTotal() == 8280.00);
        Assertions.assertTrue(vendaCalculada.getListaItensVenda().get(0).getVlPrecoVenda() == 2760.00);
        Assertions.assertTrue(vendaCalculada.getListaItensVenda().get(0).getVlTotalVenda() == 2760.00);
        Assertions.assertTrue(vendaCalculada.getListaItensVenda().get(1).getVlPrecoVenda() == 2760.00);
        Assertions.assertTrue(vendaCalculada.getListaItensVenda().get(1).getVlTotalVenda() == 5520.00);

    }

}
