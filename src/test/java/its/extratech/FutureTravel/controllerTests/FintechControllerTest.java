package its.extratech.FutureTravel.controllerTests;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FintechControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void testGetAll() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/fintech/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                .andExpect(jsonPath("$[500].provincia").exists())
                .andExpect(jsonPath("$[500].residenzaClienti").exists())
                .andExpect(jsonPath("$[500].tipoAlloggio").exists())
                .andExpect(jsonPath("$[500].time").exists())
                .andExpect(jsonPath("$[500].presenze").exists())
                .andExpect(jsonPath("$[500].tipodato").exists());
    }

    @Test
    public void testTipoAlloggioHotellike() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/fintech/hotellike")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                .andExpect(jsonPath("$[500].provincia").exists())
                .andExpect(jsonPath("$[500].residenzaClienti").exists())
                .andExpect(jsonPath("$[500].tipoAlloggio").exists())
                .andExpect(jsonPath("$[500].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[500].time").exists())
                .andExpect(jsonPath("$[500].presenze").exists())
                .andExpect(jsonPath("$[500].tipodato").exists());
    }

    @Test
    public void testTipoAlloggioOther() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/fintech/other")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                .andExpect(jsonPath("$[500].provincia").exists())
                .andExpect(jsonPath("$[500].residenzaClienti").exists())
                .andExpect(jsonPath("$[500].tipoAlloggio").exists())
                .andExpect(jsonPath("$[500].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[500].time").exists())
                .andExpect(jsonPath("$[500].presenze").exists())
                .andExpect(jsonPath("$[500].tipodato").exists());
    }

    @Test
    public void testResidenzaClientiIt() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/fintech/it")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                .andExpect(jsonPath("$[500].provincia").exists())
                .andExpect(jsonPath("$[500].residenzaClienti").exists())
                .andExpect(jsonPath("$[500].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[500].tipoAlloggio").exists())
                .andExpect(jsonPath("$[500].time").exists())
                .andExpect(jsonPath("$[500].presenze").exists())
                .andExpect(jsonPath("$[500].tipodato").exists());
    }

    @Test
    public void testResidenzaClientiEstero() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/fintech/estero")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                .andExpect(jsonPath("$[500].provincia").exists())
                .andExpect(jsonPath("$[500].residenzaClienti").exists())
                .andExpect(jsonPath("$[500].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[500].tipoAlloggio").exists())
                .andExpect(jsonPath("$[500].time").exists())
                .andExpect(jsonPath("$[500].presenze").exists())
                .andExpect(jsonPath("$[500].tipodato").exists());
    }

    @Test
    public void testProvinciaLike() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/fintech/provincia/ITF31")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF31"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                .andExpect(jsonPath("$[500].provincia").exists())
                .andExpect(jsonPath("$[500].provincia").value("ITF31"))
                .andExpect(jsonPath("$[500].residenzaClienti").exists())
                .andExpect(jsonPath("$[500].tipoAlloggio").exists())
                .andExpect(jsonPath("$[500].time").exists())
                .andExpect(jsonPath("$[500].presenze").exists())
                .andExpect(jsonPath("$[500].tipodato").exists());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/fintech/provincia/ITF34")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF34"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[500].provincia").exists())
                .andExpect(jsonPath("$[500].provincia").value("ITF34"))
                .andExpect(jsonPath("$[500].residenzaClienti").exists())
                .andExpect(jsonPath("$[500].tipoAlloggio").exists())
                .andExpect(jsonPath("$[500].time").exists())
                .andExpect(jsonPath("$[500].presenze").exists());
    }
}
