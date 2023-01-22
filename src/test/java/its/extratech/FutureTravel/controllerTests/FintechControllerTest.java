package its.extratech.FutureTravel.controllerTests;

import its.extratech.FutureTravel.controllers.FintechController;
import its.extratech.FutureTravel.dtos.request.FintechRequest;
import its.extratech.FutureTravel.servicies.implementations.FintechServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FintechControllerTest {

    private MockMvc mockMvc;

    @Autowired
    FintechServiceImpl fintechService;

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


    @Test
    public void testUpload() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.post("/fintech/upload/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"provincia\":\"Caserta\", \"residenzaClienti\":\"Italia\", \"tipoAlloggio\":\"HOTELLIKE\", \"time\":\"2022-01\", \"presenze\":9000}, {\"provincia\":\"'Caserta\", \"residenzaClienti\":\"Italia\", \"tipoAlloggio\":\"HOTELLIKE\", \"time\":\"2022-02\", \"presenze\":10000}]"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"))
                .andExpect(MockMvcResultMatchers.header().string("Location", Matchers.containsString("Caserta")));


        verify(fintechService).saveListUpload(Collections.singletonList(any(FintechRequest.class)));
    }

}
