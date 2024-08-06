package com.texsmartly.LeaveProject.service;

import com.texsmartly.LeaveProject.model.EmployeeLeave;
import com.texsmartly.LeaveProject.repository.EmployeeLeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeLeaveService {
    @Autowired
    private EmployeeLeaveRepository employeeLeaveRepository;

    public List<EmployeeLeave> getAllEmployeeLeaves() {
        return employeeLeaveRepository.findAll();
    }

    public Optional<EmployeeLeave> getEmployeeLeaveById(String id) {
        return employeeLeaveRepository.findById(id);
    }

    public Optional<EmployeeLeave> getEmployeeLeaveByEmpIdAndYear(String empId, String year) {
        return employeeLeaveRepository.findByEmpIdAndYear(empId, year);
    }

    public EmployeeLeave createEmployeeLeave(EmployeeLeave employeeLeave) {
        return employeeLeaveRepository.save(employeeLeave);
    }

    public Optional<EmployeeLeave> updateEmployeeLeave(String id, EmployeeLeave employeeLeave) {
        if (employeeLeaveRepository.existsById(id)) {
            employeeLeave.setId(id);
            return Optional.of(employeeLeaveRepository.save(employeeLeave));
        }
        return Optional.empty();
    }

    public Optional<EmployeeLeave> updateEmployeeLeaveByEmpIdAndYear(String empId, String year, EmployeeLeave employeeLeave) {
        Optional<EmployeeLeave> existingLeave = employeeLeaveRepository.findByEmpIdAndYear(empId, year);
        if (existingLeave.isPresent()) {
            employeeLeave.setId(existingLeave.get().getId());
            return Optional.of(employeeLeaveRepository.save(employeeLeave));
        }
        return Optional.empty();
    }

    public boolean deleteEmployeeLeave(String id) {
        if (employeeLeaveRepository.existsById(id)) {
            employeeLeaveRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
