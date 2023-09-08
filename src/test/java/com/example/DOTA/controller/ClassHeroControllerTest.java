package com.example.DOTA.controller;

import com.example.DOTA.services.ClassHeroService;
import com.example.DOTA.services.ViewsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.example.DOTA.config.CustomAccessDeniedHandler.LOG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ClassHeroController.class)
@AutoConfigureMockMvc(addFilters = false)
class ClassHeroControllerTest {

    @MockBean
    private  ClassHeroService classHeroService;
    @MockBean
    private  ViewsService viewsService;

    @Autowired
    private MockMvc mockMvc;


   @Test
   void  displayTestAdmin() throws Exception {
       mockMvc.perform(get("/admin/display/class"))
               .andExpect(status().isOk());

    }
    @Test
    void  displayTestAdminNOT_FOUND() throws Exception{

       mockMvc.perform(get("/home/display/classs"))
               .andExpect(status().isNotFound());

    }


    @Test
    void addTestGet() throws Exception{
        mockMvc.perform(get("/admin/add/class"))
                .andExpect(status().isOk());
    }




    @Test
    void detailsClassAdmin() throws Exception{
        mockMvc.perform(get("/admin/detals/class/10"))
                .andExpect(status().isOk());
    }



    @Test
    void update() throws Exception {
        mockMvc.perform(get("/admin/display/class"))
                .andExpect(status().isOk());
    }
}