package its.extratech.FutureTravel.controllerTests.RecordControllerTests;

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
public class ResidenzaWhateverTest {

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
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/whatever")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetAllWithStartDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/whatever")
                        .param("startDate", "2020-05")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetAllWithStartDateAnEndDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/whatever")
                        .param("startDate", "2020-05")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetAllByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/whatever/ITF33")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF33"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF33"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetAllWithStartDateByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/whatever/ITF34")
                        .param("startDate", "2020-05")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF34"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF34"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetAllWithStartDateAnEndDateByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/whatever/ITF31")
                        .param("startDate", "2020-05")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF31"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF31"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByAlloggioHotellike() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/HOTELLIKE")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByAlloggioHotellikeWithStartDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/HOTELLIKE")
                        .param("startDate", "2020-05")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByAlloggioHotellikeWithStartDateAnEndDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/HOTELLIKE")
                        .param("startDate", "2020-05")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByAlloggioHotellikeByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/HOTELLIKE/ITF33")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF33"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF33"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByAlloggioHotellikeWithStartDateByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/HOTELLIKE/ITF34")
                        .param("startDate", "2020-05")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF34"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF34"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByAlloggioHotellikeWithStartDateAnEndDateByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/HOTELLIKE/ITF31")
                        .param("startDate", "2020-05")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF31"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF31"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByAlloggioOther() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/OTHER")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByAlloggioOtherWithStartDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/OTHER")
                        .param("startDate", "2020-05")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByAlloggioOtherWithStartDateAnEndDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/OTHER")
                        .param("startDate", "2020-05")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByAlloggioOtherByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/OTHER/ITF33")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF33"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF33"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByAlloggioOtherWithStartDateByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/OTHER/ITF34")
                        .param("startDate", "2020-05")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF34"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF34"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByAlloggioOtherWithStartDateAnEndDateByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/OTHER/ITF31")
                        .param("startDate", "2020-05")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF31"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF31"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByAlloggioAll() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/ALL")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByAlloggioAllWithStartDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/ALL")
                        .param("startDate", "2020-05")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByAlloggioAllWithStartDateAnEndDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/ALL")
                        .param("startDate", "2020-05")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByAlloggioAllByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/ALL/ITF33")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF33"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF33"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByAlloggioAllWithStartDateByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/ALL/ITF34")
                        .param("startDate", "2020-05")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF34"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF34"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    @Test
    public void testGetByAlloggioAllWithStartDateAnEndDateByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/S/whatever/ALL/ITF31")
                        .param("startDate", "2020-05")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF31"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2020-05"))
                .andExpect(jsonPath("$[0].tipoDato").exists())
                .andExpect(jsonPath("$[0].tipoDato").value("S"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF31"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].tipoDato").value("S"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }
}
