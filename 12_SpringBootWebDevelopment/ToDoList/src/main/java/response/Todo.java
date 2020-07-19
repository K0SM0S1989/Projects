package response;

import java.util.concurrent.atomic.AtomicLong;

public class Todo {
    private long id;
    private String todo;

    public Todo() {
    }

    public Todo(long id, String todo) {
        this.id = id;
        this.todo = todo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }
}
