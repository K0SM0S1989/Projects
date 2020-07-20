package main.controller;

import main.ApiError;
import main.Storage;
import main.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.Todo;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class TodoController {


    @GetMapping("/todo/")
    public List<Todo> list() {
        return Storage.allTodo();
    }

    @PostMapping("/todo/")
    public long add(Todo todo) {
        return Storage.addTodo(todo);
    }

    @GetMapping("/todo/{id}")
    public Todo get(@PathVariable long id) throws EntityNotFoundException {
        Todo todo = Storage.getTodo(id);
        if (todo == null) {
            throw EntityNotFoundException.createWith(id,"");
        }
        return todo;
    }

    @DeleteMapping("/todo/{id}")
    public String delete(@PathVariable long id) throws EntityNotFoundException {
        Todo todo = Storage.getTodo(id);
        if (todo == null) {
            throw EntityNotFoundException.createWith(id, "Дело №" + id + " не найдено");
        }
        return Storage.deleteTodo(id);
    }

    @PutMapping("/todo/{id}")
    public String update(@PathVariable long id, Todo todo) throws EntityNotFoundException {
        if (Storage.updateTodo(id, todo) == null) {
            throw EntityNotFoundException.createWith(id, "");
        }
        return Storage.updateTodo(id, todo);
    }

    @DeleteMapping("/todo/")
    public String deleteAll() {
        return Storage.deleteAllTodo();
    }


}
