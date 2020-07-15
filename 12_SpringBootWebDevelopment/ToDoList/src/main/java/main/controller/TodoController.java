package main.controller;

import main.Storage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.Todo;

import java.util.List;

@RestController
public class TodoController {

    @GetMapping("/todo/")
    public List<Todo> list() {
        return Storage.allTodo();
    }

    @PostMapping("/todo/")
    public int add(Todo todo) {
        return Storage.addTodo(todo);
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Todo todo = Storage.getTodo(id);
        if (todo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else return new ResponseEntity(todo, HttpStatus.OK);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Todo todo = Storage.getTodo(id);
        if (todo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else return new ResponseEntity(Storage.deleteTodo(id), HttpStatus.OK);
    }

    @PutMapping("/todo/{id}")
    public ResponseEntity update(@PathVariable int id, Todo todo) {
        if (Storage.updateTodo(id, todo) != null){
            return new ResponseEntity(Storage.updateTodo(id, todo),HttpStatus.OK);
        }else return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @DeleteMapping("/todo/")
    public String deleteAll() {
        return Storage.deleteAllTodo();
    }
}
