package raquel.calculadora.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import raquel.calculadora.CalculadoraApplication;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = CalculadoraApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "test", password = "pass", roles = "USER")
public class CalculadoraControllerMockMvcTest {

    @Autowired
    private MockMvc mvc;


    @Test
    public void sumarTestOK() throws Exception {

        final ObjectMapper mapper = new ObjectMapper();
        final String peticionJson = mapper.writeValueAsString(Arrays.asList(1.0, 2.7));

        mvc.perform(post("/calculadora/sumar").contentType(MediaType.APPLICATION_JSON).content(peticionJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.operador", is("SUMA")))
                .andExpect(jsonPath("$.resultado", is(3.7)));

    }

    @Test
    public void restarTestOK() throws Exception {

        final ObjectMapper mapper = new ObjectMapper();
        final String peticionJson = mapper.writeValueAsString(Arrays.asList(10.0, 1.6));

        mvc.perform(post("/calculadora/restar").contentType(MediaType.APPLICATION_JSON).content(peticionJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.operador", is("RESTA")))
                .andExpect(jsonPath("$.resultado", is( 8.4)));

    }


    @Test
    public void sumarTestKO() throws Exception {

        final ObjectMapper mapper = new ObjectMapper();
        final String peticionJson = mapper.writeValueAsString(Arrays.asList(null, 2.7));

        mvc.perform(post("/calculadora/sumar").contentType(MediaType.APPLICATION_JSON).content(peticionJson))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Algún operando es nulo. La operación no soporta nulos"));

    }

    @Test
    public void restarTestKO() throws Exception {

        final ObjectMapper mapper = new ObjectMapper();
        final String peticionJson = mapper.writeValueAsString(Arrays.asList());

        mvc.perform(post("/calculadora/restar").contentType(MediaType.APPLICATION_JSON).content(peticionJson))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("La lista de operandos no puede estar vacía"));

    }

}
