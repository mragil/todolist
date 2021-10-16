package com.exampletodo.todo.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/todolist")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<Object> getTodoList() {

        return todoService.getAllTodoList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSingleTodo(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    @GetMapping("/checked/{checked}")
    public ResponseEntity<Object> getTodoListByChecked(@PathVariable boolean checked) {
        return todoService.getTodoListByChecked(checked);
    }

    @PostMapping
    public ResponseEntity<Object> createNewTodo(@RequestBody Todo todo) {
        return todoService.createNewTodo(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editTodo(@PathVariable Long id, @RequestBody Todo todo) {

        return todoService.editTodo(id, todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTodo(@PathVariable Long id) {
        return todoService.deleteTodo(id);
    }
}
