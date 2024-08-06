package com.texsmartly.LeaveProject.api;

import com.texsmartly.LeaveProject.model.LeaveTypeAdmin;
import com.texsmartly.LeaveProject.service.LeaveTypeAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/leavetypes")
@CrossOrigin("*")
public class LeaveTypeAdminController {
    @Autowired
    private LeaveTypeAdminService service;

    @GetMapping
    public List<LeaveTypeAdmin> getAllLeaveTypes() {
        return service.getAllLeaveTypes();
    }

    @GetMapping("/{id}")
    public LeaveTypeAdmin getLeaveTypeById(@PathVariable String id) {
        return service.getLeaveTypeById(id);
    }

    @PostMapping
    public LeaveTypeAdmin createLeaveType(@RequestBody LeaveTypeAdmin leaveType) {
        return service.createLeaveType(leaveType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveTypeAdmin> updateLeaveType(@PathVariable String id, @RequestBody LeaveTypeAdmin leaveType) {
        LeaveTypeAdmin updatedLeaveType = service.updateLeaveType(id, leaveType);
        if (updatedLeaveType == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedLeaveType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaveType(@PathVariable String id) {
        service.deleteLeaveType(id);
        return ResponseEntity.noContent().build();
    }
}
