package com.texsmartly.LeaveProject.repository;

import com.texsmartly.LeaveProject.model.EmployeeLeave;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmployeeLeaveRepository extends MongoRepository<EmployeeLeave, String> {
    Optional<EmployeeLeave> findByEmpIdAndYear(String empId, String year);

}
