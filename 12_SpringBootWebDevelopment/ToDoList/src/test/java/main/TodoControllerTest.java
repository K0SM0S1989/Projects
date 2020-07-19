package main;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import response.Todo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TodoControllerTest extends LoginTest {

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
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAllTest() throws Exception {
        this.mockMvc.perform(delete("/api/todo/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getIdTest() throws Exception {
        Todo todo = new Todo(1, "some deal");
        Storage.addTodo(todo);
        this.mockMvc.perform(get("/api/todo/" + todo.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteIdTest() throws Exception {
        Todo todo = new Todo(1, "some deal");
        Storage.addTodo(todo);
        this.mockMvc.perform(delete("/api/todo/" + todo.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void putIdTest() throws Exception {
        Todo todo = new Todo(1, "some deal");
        Storage.addTodo(todo);
        this.mockMvc.perform(put("/api/todo/" + todo.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
