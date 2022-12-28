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
public class ResidenzaClienteAggragateTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }


    //Test Alloggio HotelLike
    @Test
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
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Alloggio HotelLike + startDate
    @Test
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
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Alloggio HotelLike + startDate e endDate
    @Test
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
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Alloggio HotelLike + Id_Provincia
    @Test
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
                .andExpect(jsonPath("$[0].time").value("2008-01"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF31"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Alloggio HotelLike + Id_Provincia + startDate
    @Test
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
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF31"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Alloggio HotelLike + Id_Provincia + startDate e endDate
    @Test
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
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF31"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("HOTELLIKE"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Alloggio Other
    @Test
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
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }



    //Test Alloggio Other + startDate
    @Test
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
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

    //Test Alloggio Other + startDate e endDate
    @Test
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
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Alloggio Other + Id_Provincia + startDate e endDate
    @Test
    public void testGetByTipoAlloggioOtherAbsByIdProvincia() throws Exception {
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
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF31"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Alloggio Other + Id_Provincia + startDate
    @Test
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
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF31"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Alloggio Other + Id_Provincia + startDate e endDate
    @Test
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
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF31"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[-1].tipoAlloggio").value("OTHER"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Residenza_Clienti Italia
    @Test
    public void testGetByResidenzaItAbs() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/residenza/it")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Residenza_Clienti Italia + startDate
    @Test
    public void testGetByResidenzaItAbsWithStartDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/residenza/it")
                        .param("startDate", "2008-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Residenza_Clienti Italia + startDate e endDate
    @Test
    public void testGetByResidenzaItAbsWithStartDateAndEndDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/residenza/it")
                        .param("startDate", "2008-01")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Residenza_Clienti Italia + Id_Provincia + startDate e endDate
    @Test
    public void testGetByResidenzaItAbsByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/residenza/it/ITF32")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF32"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF32"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Residenza_Clienti Italia + Id_Provincia + startDate
    @Test
    public void testGetByResidenzaItAbsByProvinciaWithStartDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/residenza/it/ITF32")
                        .param("startDate", "2008-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF32"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2008-01"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF32"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Residenza_Clienti Italia + Id_Provincia + startDate e endDate
    @Test
    public void testGetByResidenzaItAbsByProvinciaWithStartDateAndEndDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/residenza/it/ITF32")
                        .param("startDate", "2008-01")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF32"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2008-01"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF32"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Residenza_Clienti Estera
    @Test
    public void testGetByResidenzaEsteroAbs() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/residenza/estero")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Residenza_Clienti Estera + startDate
    @Test
    public void testGetByResidenzaEsteroAbsWithStartDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/residenza/estero")
                        .param("startDate", "2008-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Residenza_Clienti Estera + startDate e endDate
    @Test
    public void testGetByResidenzaEsteroAbsWithStartDateAndEndDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/residenza/estero")
                        .param("startDate", "2008-01")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("IT"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Residenza_Clienti Estera + Id_Provincia + startDate e endDate
    @Test
    public void testGetByResidenzaEsteroAbsByProvincia() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/residenza/estero/ITF32")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF32"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF32"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Residenza_Clienti Estera + Id_Provincia + startDate
    @Test
    public void testGetByResidenzaEsteroAbsByProvinciaWithStartDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/residenza/estero/ITF32")
                        .param("startDate", "2008-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF32"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2008-01"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF32"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test Residenza_Clienti Estera + Id_Provincia + startDate e endDate
    @Test
    public void testGetByResidenzaEsteroAbsByProvinciaWithStartDateAndEndDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/front/abs/residenza/estero/ITF32")
                        .param("startDate", "2008-01")
                        .param("endDate", "2020-07")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].provincia").exists())
                .andExpect(jsonPath("$[0].provincia").value("ITF32"))
                .andExpect(jsonPath("$[0].residenzaClienti").exists())
                .andExpect(jsonPath("$[0].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[0].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[0].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2008-01"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF32"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("WRL_X_ITA"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test All + Id_Provincia + startDate e endDate
    @Test
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
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test All + startDate
    @Test
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
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test All + startDate e endDate
    @Test
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
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test All + Id_Provincia
    @Test
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
                .andExpect(jsonPath("$[-1].time").value("2008-01"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF32"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test All + Id_Provincia + startDate
    @Test
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
                .andExpect(jsonPath("$[-1].time").value("2008-01"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF32"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }


    //Test All + Id_Provincia + startDate e endDate
    @Test
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
                .andExpect(jsonPath("$[-1].time").value("2008-01"))
                .andExpect(jsonPath("$[0].presenze").exists())
                .andExpect(jsonPath("$[0].arrivi").exists())
                .andExpect(jsonPath("$[-1].provincia").exists())
                .andExpect(jsonPath("$[-1].provincia").value("ITF32"))
                .andExpect(jsonPath("$[-1].residenzaClienti").exists())
                .andExpect(jsonPath("$[-1].residenzaClienti").value("ALL"))
                .andExpect(jsonPath("$[-1].tipoAlloggio").exists())
                .andExpect(jsonPath("$[0].tipoAlloggio").value("ALL"))
                .andExpect(jsonPath("$[-1].time").exists())
                .andExpect(jsonPath("$[-1].time").value("2020-07"))
                .andExpect(jsonPath("$[-1].presenze").exists())
                .andExpect(jsonPath("$[-1].arrivi").exists());
    }

}
