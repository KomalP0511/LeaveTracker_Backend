package com.texsmartly.LeaveProject.api;

import com.texsmartly.LeaveProject.model.Form;
import com.texsmartly.LeaveProject.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forms")
@CrossOrigin("*")
public class FormController {

    @Autowired
    private FormService formService;


    @GetMapping
    public List<Form> getForms() {
        return formService.findAllForms();
    }

    @PostMapping
    public ResponseEntity<Form> addForm(@RequestBody Form form) {
        Form savedForm = formService.addForm(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedForm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Form> updateForm(@PathVariable String id, @RequestBody Form form) {
        Form updatedForm = formService.updateForm(id, form);
        if (updatedForm != null) {
            return ResponseEntity.ok(updatedForm);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForm(@PathVariable String id) {
        boolean isDeleted = formService.deleteForm(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Exception handling for handling any exceptions thrown by this controller
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}