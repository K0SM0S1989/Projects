package main.serv;

import main.EntityNotFoundException;
import main.models.Todo;

import java.util.List;

public interface TaskService {

    long addTodo(Todo todo);

    Todo getTodo(long todoId) throws EntityNotFoundException;

    String deleteTodo(long todoId) throws EntityNotFoundException;

    String updateTodo(long todoId, Todo todo) throws EntityNotFoundException;

    String deleteAllTodo();

    List<Todo> getListOfItems();
}
