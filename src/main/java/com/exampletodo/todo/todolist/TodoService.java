package com.exampletodo.todo.todolist;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.exampletodo.todo.response.ResponseHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private final TodoRepository repository;

    @Autowired
    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Object> getAllTodoList() {
        List<Todo> todos = repository.findAll();
        return ResponseHandler.generateResponse("Successfully Retrieve The List", HttpStatus.OK, todos);
    }

    public ResponseEntity<Object> getTodoById(Long id) {
        Optional<Todo> todo = repository.findById(id);

        if (!todo.isPresent()) {
            return ResponseHandler.generateResponse("Todo with given ID not exist!", HttpStatus.BAD_REQUEST, null);

        }

        return ResponseHandler.generateResponse("Successfully Retrieve The Data", HttpStatus.OK, todo);

    }

    public ResponseEntity<Object> getTodoListByChecked(boolean checked) {
        List<Todo> todos = repository.findByChecked(checked);
        return ResponseHandler.generateResponse("Successfully Retrieve The List", HttpStatus.OK, todos);
    }

    public ResponseEntity<Object> createNewTodo(Todo todo) {
        repository.save(todo);
        return ResponseHandler.generateResponse("Successfully added new data!", HttpStatus.CREATED, todo);
    }

    @Transactional
    public ResponseEntity<Object> editTodo(Long id, Todo update) {
        Optional<Todo> todo = repository.findById(id);

        if (!todo.isPresent()) {
            return ResponseHandler.generateResponse("Todo with given ID is not exist!", HttpStatus.BAD_REQUEST, null);
        }

        Todo _todo = todo.get();

        _todo.setTitle(update.getTitle());
        _todo.setDescription(update.getDescription());
        _todo.setChecked(update.isChecked());

        return ResponseHandler.generateResponse("Successfully Update Todo", HttpStatus.OK, todo);

    }

    public ResponseEntity<Object> deleteTodo(Long id) {
        Optional<Todo> todo = repository.findById(id);

        if (!todo.isPresent()) {
            return ResponseHandler.generateResponse("Todo with given ID is not exist!", HttpStatus.BAD_REQUEST, null);
        }

        repository.deleteById(id);
        return ResponseHandler.generateResponse("Successfully Delete Todo", HttpStatus.OK, todo.get());
    }

}
