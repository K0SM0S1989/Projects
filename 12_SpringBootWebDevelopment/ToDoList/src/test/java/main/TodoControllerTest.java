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

@TestPropertySource("/application-test.properties")
public class TodoControllerTest extends LoginTest {

    @Autowired
    private TodoRepository todoRepository;

    Todo todo = new Todo(1, "some deal");
    Todo todo1 = new Todo(2, "some deal again");
    Todo newTodo1;
    Todo newTodo2;
    long id1;
    long id2;

    @Before
    public void setup() throws Exception {
        super.setup();
       todoRepository.deleteAll();
        newTodo1 = todoRepository.save(todo);
        newTodo2 = todoRepository.save(todo1);
        id1 = newTodo1.getId();
        id2 = newTodo2.getId();
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
        this.mockMvc.perform(get("/api/todo/" + id1))
                .andDo(print())
                .andExpect(status().isOk())
   .andExpect(content().string(containsString(mapper.writeValueAsString(newTodo1))));
    }

    @Test
    public void deleteIdTest() throws Exception {
        this.mockMvc.perform(delete("/api/todo/" + id1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Дело №" + id1 + " удалено из базы")));
    }

    @Test
    public void putIdTest() throws Exception {
        this.mockMvc.perform(put("/api/todo/" + id2).param("todoString", "Изменение"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Дело №" + id2 + " изменено")));
    }
}
