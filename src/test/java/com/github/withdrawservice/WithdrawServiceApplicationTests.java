package com.github.withdrawservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.withdrawservice.model.WithdrawModel;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.Duration;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(initializers = {WithdrawServiceApplicationTests.Initializer.class})
public class WithdrawServiceApplicationTests {

    private static final Long WALLET_ID = 1L;

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer =
            (PostgreSQLContainer) new PostgreSQLContainer("postgres:10.4")
                    .withDatabaseName("withdraw_service")
                    .withUsername("sampleuser")
                    .withPassword("samplepwd")
                    .withStartupTimeout(Duration.ofSeconds(600));


    @Test
    public void shouldRetrieveWalletInfo() throws Exception {
        //when
        mvc.perform(get("/wallets/" + WALLET_ID)
                .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void shouldPerformFirstWithdraw_andFailSecond() throws Exception {
        //given
        WithdrawModel withdraw = WithdrawModel.builder()
                .requestId(UUID.randomUUID())
                .withdrawAmount(10L).build();

        //when
        //happy path withdraw
        mvc.perform(post("/wallets/1/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(withdraw)))
                .andExpect(status().isNoContent());

        //when
        //second withdraw with the same id
        mvc.perform(post("/wallets/1/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(withdraw)))
                //then
                //bad request response
                .andExpect(status().isBadRequest());
    }

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

}

