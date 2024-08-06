package com.texsmartly.LeaveProject.api;

import com.texsmartly.LeaveProject.model.EmployeeLeave;
import com.texsmartly.LeaveProject.service.EmployeeLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee-leaves")
@CrossOrigin("*")
public class EmployeeLeaveController {
    @Autowired
    private EmployeeLeaveService employeeLeaveService;

    @GetMapping
    public ResponseEntity<List<EmployeeLeave>> getAllEmployeeLeaves() {
        return ResponseEntity.ok(employeeLeaveService.getAllEmployeeLeaves());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeLeave> getEmployeeLeaveById(@PathVariable String id) {
        return employeeLeaveService.getEmployeeLeaveById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/employee/{empId}/year/{year}")
    public ResponseEntity<EmployeeLeave> getEmployeeLeaveByEmpIdAndYear(
            @PathVariable String empId, @PathVariable String year) {
        Optional<EmployeeLeave> leave = employeeLeaveService.getEmployeeLeaveByEmpIdAndYear(empId, year);
        if (leave.isPresent()) {
            EmployeeLeave employeeLeave = leave.get();
            return ResponseEntity.ok(employeeLeave);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeLeave> createEmployeeLeave(@RequestBody EmployeeLeave employeeLeave) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeLeaveService.createEmployeeLeave(employeeLeave));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeLeave> updateEmployeeLeave(
            @PathVariable String id, @RequestBody EmployeeLeave employeeLeave) {
        return employeeLeaveService.updateEmployeeLeave(id, employeeLeave)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/employee/{empId}/year/{year}")
    public ResponseEntity<EmployeeLeave> updateEmployeeLeaveByEmpIdAndYear(
            @PathVariable String empId, @PathVariable String year, @RequestBody EmployeeLeave employeeLeave) {
        return employeeLeaveService.updateEmployeeLeaveByEmpIdAndYear(empId, year, employeeLeave)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeLeave(@PathVariable String id) {
        return employeeLeaveService.deleteEmployeeLeave(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
