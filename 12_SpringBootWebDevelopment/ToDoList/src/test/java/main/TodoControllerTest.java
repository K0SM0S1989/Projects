package main;

import main.models.Todo;
import main.repo.TodoRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;



import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestPropertySource("/application-test.yml")
public class TodoControllerTest extends LoginTest {

    @Autowired
    private TodoRepository todoRepository;

    @Before
    public void setup() throws Exception {
        super.setup();
         todoRepository.deleteAll();
         todoRepository.save(new Todo(1,"some deal"));
         todoRepository.save(new Todo(2, "some deal again"));
    }

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
                .andExpect(content().string(containsString("" + 9)));
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
        this.mockMvc.perform(get("/api/todo/" + 12))
                .andDo(print())
                .andExpect(status().isOk())
   .andExpect(content().string(containsString("{\"id\":12,\"todoString\":\"some deal\"}")));
    }

    @Test
    public void deleteIdTest() throws Exception {
        this.mockMvc.perform(delete("/api/todo/" + 3))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Дело №" + 3 + " удалено из базы")));
    }

    @Test
    public void putIdTest() throws Exception {
        this.mockMvc.perform(put("/api/todo/" + 6).param("todoString", "Изменение"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Дело №" + 6 + " изменено")));
    }
}
