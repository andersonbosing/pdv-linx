package br.com.linx.pdvlinx;

import br.com.linx.pdvlinx.controllers.dto.ItemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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

}
