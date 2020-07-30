package main.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String todoString;

    public Todo() {
    }

    public Todo(long id, String todo) {
        this.id = id;
        this.todoString = todo;
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
