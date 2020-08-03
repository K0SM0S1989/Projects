package main.dto;


import javax.validation.constraints.NotNull;

public class TodoDTO {

    private long id;

    @NotNull
    private String todoString;

    public TodoDTO(long id, String todoString) {
        this.id = id;
        this.todoString = todoString;
    }

    public TodoDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTodoString() {
        return todoString;
    }

    public void setTodoString(String todoString) {
        this.todoString = todoString;
    }
}
