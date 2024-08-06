package com.texsmartly.LeaveProject.repository;

import com.texsmartly.LeaveProject.model.Form;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormRepository extends MongoRepository<Form, String>  {
}
