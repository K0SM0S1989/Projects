package main.mappers;


import main.dto.TodoDTO;
import main.models.Todo;


public class TodoMapper {

    public static TodoDTO map(Todo todo) {
        return new TodoDTO(todo.getId(), todo.getTodoString());
    }

    public static Todo convert(TodoDTO todoDTO) {
        Todo todo = new Todo();
        todo.setTodoString(todoDTO.getTodoString());
        return todo;
    }

}
