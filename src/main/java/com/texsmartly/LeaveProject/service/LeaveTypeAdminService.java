package com.texsmartly.LeaveProject.service;

import com.texsmartly.LeaveProject.model.LeaveTypeAdmin;
import com.texsmartly.LeaveProject.repository.LeaveTypeAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LeaveTypeAdminService {
    @Autowired
    private LeaveTypeAdminRepository repository;

    public List<LeaveTypeAdmin> getAllLeaveTypes() {
        return repository.findAll();
    }

    public LeaveTypeAdmin getLeaveTypeById(String id) {
        return repository.findById(id).orElse(null);
    }

    public LeaveTypeAdmin createLeaveType(LeaveTypeAdmin leaveType) {
        return repository.save(leaveType);
    }

    public LeaveTypeAdmin updateLeaveType(String id, LeaveTypeAdmin leaveType) {
        if (repository.existsById(id)) {
            leaveType.setId(id);
            return repository.save(leaveType);
        }
        return null;
    }

    public void deleteLeaveType(String id) {
        repository.deleteById(id);
    }

    public void updateLeaveDays(String leaveTypeName, int bookedDays) {
        LeaveTypeAdmin leaveType = repository.findByName(leaveTypeName);
        if (leaveType != null) {
            leaveType.setBookedDays(leaveType.getBookedDays() + bookedDays);
            leaveType.setDays(leaveType.getDays() - bookedDays);
            repository.save(leaveType);
        }
    }
}
