package com.texsmartly.LeaveProject.repository;

import com.texsmartly.LeaveProject.model.LeaveTypeAdmin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LeaveTypeAdminRepository extends MongoRepository<LeaveTypeAdmin, String> {
    LeaveTypeAdmin findByName(String name);
}
