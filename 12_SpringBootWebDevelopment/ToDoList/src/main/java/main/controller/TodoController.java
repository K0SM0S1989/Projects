package main.controller;

import main.Storage;
import main.EntityNotFoundException;

import main.repo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import main.models.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;


    @GetMapping("/todo/")
    public List<Todo> list() {
        Iterable<Todo> todoIterable = todoRepository.findAll();
        List<Todo> todoList = new ArrayList<>();
        for (Todo todo : todoIterable) {
            todoList.add(todo);
        }
        return todoList;
    }

    @PostMapping("/todo/")
    public long add(Todo todo) {
        Todo newTodo = todoRepository.save(todo);
        return newTodo.getId();
    }

    @GetMapping("/todo/{id}")
    public Todo get(@PathVariable long id) throws EntityNotFoundException {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (!optionalTodo.isPresent()) {
            throw EntityNotFoundException.createWith(id, "Дело №" + id + " не найдено");
        }
        return optionalTodo.get();
    }

    @DeleteMapping("/todo/{id}")
    public String delete(@PathVariable long id) throws EntityNotFoundException {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (!optionalTodo.isPresent()) {
            throw EntityNotFoundException.createWith(id, "Дело №" + id + " не найдено");
        } else todoRepository.deleteById(id);
        return "Дело №" + id + " удалено из базы";
    }

    @PutMapping("/todo/{id}")
    public String update(@PathVariable long id, Todo todo) throws EntityNotFoundException {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (!todoOptional.isPresent()) {
            throw EntityNotFoundException.createWith(id, "Дело №" + id + " не найдено");
        } else {
            todoOptional.get().setTodoString(todo.getTodoString());
            todoRepository.save(todoOptional.get());
        }
        return "Дело №" + id + " изменено";
    }

    @DeleteMapping("/todo/")
    public String deleteAll() {
        todoRepository.deleteAll();
        return "База очищена";
    }


}
