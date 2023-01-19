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
public class TipoAlloggioAggregateTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }


    // alloggio HOTELLIKE
    @Test
    @Order(1)
    public void testGetByTipoAlloggioHotellikeAbs() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/alloggio/hotellike")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                //.andExpect(jsonPath("$[0].tipodato").value("S"))
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists())
                .andExpect(jsonPath("$[-1].tipodato").exists());
        //.andExpect(jsonPath("$[-1].tipodato").value("S"));
    }


    // alloggio HOTELLIKE + startDate
    @Test
    @Order(2)
    public void testGetByTipoAlloggioHotellikeAbsWithStartDate () throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/alloggio/hotellike")
                        .param("startDate", "2008-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("HOTELLIKE"))
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
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists())
                .andExpect(jsonPath("$[-1].tipodato").exists());
        //.andExpect(jsonPath("$[-1].tipodato").value("S"));
    }


    // alloggio HOTELLIKE + startDate & endDate
    @Test
    @Order(3)
    public void testGetByTipoAlloggioHotellikeAbsWithStartDateAnEndDate () throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/alloggio/hotellike")
                        .param("startDate", "2008-01")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("HOTELLIKE"))
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
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists())
                .andExpect(jsonPath("$[-1].tipodato").exists());
        //.andExpect(jsonPath("$[-1].tipodato").value("S"));
    }


    // alloggio HOTELLIKE + provincia
    @Test
    @Order(4)
    public void testGetByTipoAlloggioHotellikeAbsByIdProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/alloggio/hotellike/ITF31")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF31"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                //.andExpect(jsonPath("$[0].tipodato").value("S"))
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF31"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists())
                .andExpect(jsonPath("$[-1].tipodato").exists());
        //.andExpect(jsonPath("$[-1].tipodato").value("S"));
    }


    // alloggio HOTELLIKE + provincia + startDate
    @Test
    @Order(5)
    public void testGetByTipoAlloggioHotellikeAbsByIdProvinciaWithStartDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/alloggio/hotellike/ITF31")
                        .param("startDate", "2008-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF31"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2008-01"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                //.andExpect(jsonPath("$[0].tipodato").value("S"))
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF31"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists())
                .andExpect(jsonPath("$[-1].tipodato").exists());
        //.andExpect(jsonPath("$[-1].tipodato").value("S"));
    }


    // alloggio HOTELLIKE + provincia + startDate & endDate
    @Test
    @Order(6)
    public void testGetByTipoAlloggioHotellikeAbsByIdProvinciaWithStartDateAndEndDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/alloggio/hotellike/ITF31")
                        .param("startDate", "2008-01")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF31"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2008-01"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                        //.andExpect(jsonPath("$[0].tipodato").value("S"))
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF31"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists())
                .andExpect(jsonPath("$[-1].tipodato").exists());
        //.andExpect(jsonPath("$[-1].tipodato").value("S"));
    }


    // alloggio OTHER
    @Test
    @Order(7)
    public void testGetByTipoAlloggioOtherAbs() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/alloggio/other")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                //.andExpect(jsonPath("$[0].tipodato").value("S"))
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists())
                .andExpect(jsonPath("$[-1].tipodato").exists());
        //.andExpect(jsonPath("$[-1].tipodato").value("S"));
    }


    // alloggio OTHER + startDate
    @Test
    @Order(8)
    public void testGetByTipoAlloggioOtherAbsWithStartDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/alloggio/other")
                        .param("startDate", "2008-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("OTHER"))
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
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists())
                .andExpect(jsonPath("$[-1].tipodato").exists());
        //.andExpect(jsonPath("$[-1].tipodato").value("S"));
    }

    // alloggio OTHER + startDate & endDate
    @Test
    @Order(9)
    public void testGetByTipoAlloggioOtherAbsWithStartDateAndEndDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/alloggio/other")
                        .param("startDate", "2008-01")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("OTHER"))
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
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists())
                .andExpect(jsonPath("$[-1].tipodato").exists());
        //.andExpect(jsonPath("$[-1].tipodato").value("S"));
    }


    // alloggio OTHER + provincia
    @Test
    @Order(10)
    public void testGetByTipoAlloggioOtherAbsByIdProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/alloggio/other/ITF31")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF31"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                //.andExpect(jsonPath("$[0].tipodato").value("S"))
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF31"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists())
                .andExpect(jsonPath("$[-1].tipodato").exists());
        //.andExpect(jsonPath("$[-1].tipodato").value("S"));
    }


    // alloggio OTHER + provincia + startDate
    @Test
    @Order(11)
    public void testGetByTipoAlloggioOtherAbsByIdProvinciaWithStartDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/alloggio/other/ITF31")
                        .param("startDate", "2008-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF31"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2008-01"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                //.andExpect(jsonPath("$[0].tipodato").value("S"))
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF31"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists())
                .andExpect(jsonPath("$[-1].tipodato").exists());
        //.andExpect(jsonPath("$[-1].tipodato").value("S"));
    }


    // alloggio OTHER + provincia + startDate & endDate
    @Test
    @Order(12)
    public void testGetByTipoAlloggioOtherAbsByIdProvinciaWithStartDateAndEndDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/alloggio/other/ITF31")
                        .param("startDate", "2008-01")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF31"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].time").value("2008-01"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[0].tipodato").exists())
                //.andExpect(jsonPath("$[0].tipodato").value("S"))
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF31"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists())
                .andExpect(jsonPath("$[-1].tipodato").exists());
        //.andExpect(jsonPath("$[-1].tipodato").value("S"));
    }
}
