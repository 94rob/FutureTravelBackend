package its.extratech.FutureTravel.controllerTests.FEControllerTests;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AllAggregateTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    // all
    @Test
    @Order(1)
    public void testGetAbsoluteData() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                //.andExpect(jsonPath("$[0].tipodato").value("S"))
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists())
                .andExpect(jsonPath("$[-1].tipodato").exists());
                //.andExpect(jsonPath("$[-1].tipodato").value("S"));
    }


    // all + startDate
    @Test
    @Order(2)
    public void testGetAbsoluteDataWithStartDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs")
                        .param("startDate", "2008-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2008-01"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                //.andExpect(jsonPath("$[0].tipodato").value("S"))
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists())
                .andExpect(jsonPath("$[-1].tipodato").exists());
                //.andExpect(jsonPath("$[-1].tipodato").value("S"));
    }


    // all + startDate & endDate
    @Test
    @Order(3)
    public void testGetAbsoluteDataWithStartDateAndEndDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs")
                        .param("startDate", "2008-01")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2008-01"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                //.andExpect(jsonPath("$[0].tipodato").value("S"))
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists())
                .andExpect(jsonPath("$[-1].tipodato").exists());
                //.andExpect(jsonPath("$[-1].tipodato").value("S"));
    }


    // all + provincia
    @Test
    @Order(4)
    public void testGetAbsoluteDataByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/ITF32")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF32"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                //.andExpect(jsonPath("$[0].tipodato").value("S"))
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF32"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists())
                .andExpect(jsonPath("$[-1].tipodato").exists());
                //.andExpect(jsonPath("$[-1].tipodato").value("S"));
    }


    // all + provincia + startDate
    @Test
    @Order(5)
    public void testGetAbsoluteDataByProvinciaWithStartDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/ITF32")
                        .param("startDate", "2008-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF32"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2008-01"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                //.andExpect(jsonPath("$[0].tipodato").value("S"))
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF32"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists())
                .andExpect(jsonPath("$[-1].tipodato").exists());
                //.andExpect(jsonPath("$[-1].tipodato").value("S"));
    }


    // all + provincia + startDate & endDate
    @Test
    @Order(6)
    public void testGetAbsoluteDataByProvinciaWithStartDateAndEndDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/ITF32")
                        .param("startDate", "2008-01")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF32"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2008-01"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                //.andExpect(jsonPath("$[0].tipodato").value("S"))
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF32"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists())
                .andExpect(jsonPath("$[-1].tipodato").exists());
                //.andExpect(jsonPath("$[-1].tipodato").value("S"));
    }
}
