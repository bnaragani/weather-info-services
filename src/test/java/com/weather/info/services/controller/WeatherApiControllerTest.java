package com.weather.info.services.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.weather.info.services.WeatherInfoServicesTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

class WeatherApiControllerTest extends WeatherInfoServicesTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testTW() throws Exception {
        testWeather( "London", "a11a2e24cfb3e9e4af5e5e7acf471967");
        testWeather("london", "a11a2e24cfb3e9e4af5e5e7acf471967");
        testWeather("New york", "f497c169876ac888bc377a0b11820413");
        testWeather("Mumbai", "f497c169876ac888bc377a0b11820413");
        testWeather("LISBON", "f497c169876ac888bc377a0b11820413");
        testWeather("London", "a11a2e24cfb3e9e4af5e5e7acf471967");
        testWeather("London", "f497c169876ac888bc377a0b11820413");
        testWeather("london", "f497c169876ac888bc377a0b11820413");
        testWeather("Mumbai", "9fbd1a30db0523198a5fd64a77d80e05");
        testWeather("lisbon", "9fbd1a30db0523198a5fd64a77d80e05");
    }

    private void testWeather(String city, String key) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/")
                        .param("city", city)
                        .param("key", key)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resultWT = result.getResponse().getContentAsString();
        assertNotNull(resultWT);

    }

}