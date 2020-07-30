package main;

import org.junit.Test;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-todo-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-todo-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class TodoControllerTest extends LoginTest {


    @Test
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
    public void deleteAllTest() throws Exception {
        this.mockMvc.perform(delete("/api/todo/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("База очищена")));
    }

    @Test
    public void getIdTest() throws Exception {

        this.mockMvc.perform(get("/api/todo/" + 2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":2,\"todoString\":\"some deal\"}")));
    }

    @Test
    public void deleteIdTest() throws Exception {
        this.mockMvc.perform(delete("/api/todo/" + 2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Дело №" + 2 + " удалено из базы")));
    }

    @Test
    public void putIdTest() throws Exception {
        this.mockMvc.perform(put("/api/todo/" + 3).param("todoString", "Изменение"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Дело №" + 3 + " изменено")));
    }
}
