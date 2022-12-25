package its.extratech.FutureTravel.controllerTests.FEControllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ResidenzaClientiNotAggregateTest {

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
    public void testGetByResidenzaClientiIt() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/residenza/it")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByResidenzaClientiItWithStartDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/residenza/it")
                        .param("startDate", "2020-05")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByResidenzaClientiItWithStartDateAnEndDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/residenza/it")
                        .param("startDate", "2020-05")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByResidenzaClientiEstero() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/residenza/estero")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByResidenzaClientiEsteroWithStartDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/residenza/estero")
                        .param("startDate", "2020-05")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByResidenzaClientiEsteroWithStartDateAnEndDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/residenza/estero")
                        .param("startDate", "2020-05")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByResidenzaClientiItByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/residenza/it/ITF33")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF33"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF33"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByResidenzaClientiItWithStartDateByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/residenza/it/ITF34")
                        .param("startDate", "2020-05")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF34"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF34"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByResidenzaClientiItWithStartDateAnEndDateByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/residenza/it/ITF31")
                        .param("startDate", "2020-05")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF31"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF31"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByResidenzaClientiEsteroByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/residenza/estero/ITF32")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF32"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF32"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByResidenzaClientiEsteroWithStartDateByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/residenza/estero/ITF34")
                        .param("startDate", "2020-05")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF34"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF34"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByResidenzaClientiEsteroWithStartDateAnEndDateByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/residenza/estero/ITF33")
                        .param("startDate", "2020-05")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF33"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF33"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }
}
