package main;

import org.junit.Test;
import response.Todo;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TodoControllerTest extends LoginTest {
    Todo todo = new Todo();
    long id;

    public void addSomeTodo() {
        todo.setTodo("some deal");
        id = Storage.addTodo(todo);
    }

    @Test
    public void getAllTest() throws Exception {
        this.mockMvc.perform(get("/api/todo/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void postAllTest() throws Exception {

        this.mockMvc.perform(post("/api/todo/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("" + 3)));
    }

    @Test
    public void deleteAllTest() throws Exception {
        this.mockMvc.perform(delete("/api/todo/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Список дел очищен")));
    }

    @Test
    public void getIdTest() throws Exception {
        todo.setTodo("some deal");
        id = Storage.addTodo(todo);
        this.mockMvc.perform(get("/api/todo/" + todo.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(mapper.writeValueAsString(todo))));
    }

    @Test
    public void deleteIdTest() throws Exception {
        addSomeTodo();
        this.mockMvc.perform(delete("/api/todo/" + todo.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Дело № " + id + " удалено!")));
    }

    @Test
    public void putIdTest() throws Exception {
        addSomeTodo();
        this.mockMvc.perform(put("/api/todo/" + todo.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Дело № " + id + " изменено!")));
    }
}
