package net.app.base.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.app.base.UserApplication;
import net.app.base.mock.ObjectsMocks;
import net.app.base.model.User;
import net.app.base.service.UserService;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = UserApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService service;


    @Test
    public void findUsers_thenReturnJsonArray() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(ObjectsMocks.getUser());

        given(service.findUsers()).willReturn(users);

        mvc.perform(get("/users/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(ObjectsMocks.getUser().getName())));
    }

    @Test
    public void get_thenReturnUserObject() throws Exception {
        given(service.get(1)).willReturn(ObjectsMocks.getUser());

        mvc.perform(get("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(ObjectsMocks.getUser().getName())));
    }

    @Test
    public void findByEmail_thenReturnNull() throws Exception {
        given(service.findByEmail("ssss@ssss.com")).willReturn(null);

        mvc.perform(get("/users/check/ssss@ssss.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").doesNotExist());
    }

    @Test
    public void create_thenReturnObjectUser() throws Exception {
        User u = ObjectsMocks.getUser();
        u.setId(null);
        given(service.create(u)).willReturn(ObjectsMocks.getUser());

        String json = mapper.writeValueAsString(u);
        mvc.perform(post("/users")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
