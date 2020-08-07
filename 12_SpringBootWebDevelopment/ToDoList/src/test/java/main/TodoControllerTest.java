package main;


import main.models.Todo;

import main.repo.TodoRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestPropertySource(locations = "/application-test.properties")
public class TodoControllerTest extends LoginTest {

    @Autowired
    TodoRepository todoRepository;


//    @Before
//    public void setup() throws Exception {
//        super.setup();
//        todoRepository.deleteAll();
//        todoRepository.save(new Todo(1, "some deal"));
//        todoRepository.save(new Todo(2, "some deal again"));
//    }

    @Test
    @Sql(value = {"/create-todo-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/create-todo-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getAllTest() throws Exception {
        this.mockMvc.perform(get("/api/todo/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void postAllTest() throws Exception {
        this.mockMvc.perform(post("/api/todo/").param("todoString", "tratata"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("" + 1)));
    }

    @Test
    @Sql(value = {"/create-todo-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/create-todo-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void deleteAllTest() throws Exception {
        this.mockMvc.perform(delete("/api/todo/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("База очищена")));
    }

    @Test
    @Sql(value = {"/create-todo-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/create-todo-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getIdTest() throws Exception {
        this.mockMvc.perform(get("/api/todo/" + 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(mapper.writeValueAsString(todoRepository.findById((long) 1)))));
    }

    @Test
    @Sql(value = {"/create-todo-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/create-todo-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void deleteIdTest() throws Exception {
        this.mockMvc.perform(delete("/api/todo/" + 2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Дело №" + 2 + " удалено из базы")));
    }

    @Test
    @Sql(value = {"/create-todo-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/create-todo-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void putIdTest() throws Exception {
        this.mockMvc.perform(put("/api/todo/" + 1).param("todoString", "Changed"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Дело №" + 1 + " изменено")));
    }
}
