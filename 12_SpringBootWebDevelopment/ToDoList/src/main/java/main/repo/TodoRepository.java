package main.repo;

import main.models.Todo;
import org.springframework.data.repository.CrudRepository;
public interface TodoRepository extends CrudRepository<Todo, Long> {
}
