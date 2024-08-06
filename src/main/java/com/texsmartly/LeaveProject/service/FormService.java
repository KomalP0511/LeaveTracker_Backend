package com.texsmartly.LeaveProject.service;

import com.texsmartly.LeaveProject.model.Form;
import com.texsmartly.LeaveProject.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class FormService {

    @Autowired
    private FormRepository repository;


    public Form addForm(Form form) {
        form.setFormId(UUID.randomUUID().toString().split("-")[0]);
        Form savedForm = repository.save(form);
        return savedForm;
    }


    public List<Form> findAllForms() {
        return repository.findAll();
    }

    public Form updateForm(String id, Form form) {
        if (repository.existsById(id)) {
            form.setFormId(id);
            return repository.save(form);
        } else {
            return null;
        }
    }

    public boolean deleteForm(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}