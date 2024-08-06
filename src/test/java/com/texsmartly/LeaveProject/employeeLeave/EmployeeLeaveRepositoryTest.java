package com.texsmartly.LeaveProject.employeeLeave;
import com.texsmartly.LeaveProject.model.EmployeeLeave;
import com.texsmartly.LeaveProject.repository.EmployeeLeaveRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class EmployeeLeaveRepositoryTest {
    @Autowired
    private EmployeeLeaveRepository employeeLeaveRepository;

    @Test
    void testSaveEmployeeLeave() {
        EmployeeLeave leave = new EmployeeLeave();
        leave.setEmpId("E001");
        leave.setYear("2023");

        EmployeeLeave savedLeave = employeeLeaveRepository.save(leave);

        assertNotNull(savedLeave.getId());
        assertEquals("E001", savedLeave.getEmpId());
        assertEquals("2023", savedLeave.getYear());
    }

    @Test
    void testFindByEmpIdAndYear() {
        EmployeeLeave leave = new EmployeeLeave();
        leave.setEmpId("E001");
        leave.setYear("2023");
        employeeLeaveRepository.save(leave);

        Optional<EmployeeLeave> foundLeave = employeeLeaveRepository.findByEmpIdAndYear("E001", "2023");

        assertTrue(foundLeave.isPresent());
        assertEquals("E001", foundLeave.get().getEmpId());
        assertEquals("2023", foundLeave.get().getYear());
    }

    @Test
    void testUpdateEmployeeLeave() {
        EmployeeLeave leave = new EmployeeLeave();
        leave.setEmpId("E001");
        leave.setYear("2023");
        List<EmployeeLeave.LeaveDetail> leaveDetails = new ArrayList<>();
        leaveDetails.add(new EmployeeLeave.LeaveDetail("Vacation", 10, 0, null, null, "upcoming"));
        leave.setLeaveDetails(leaveDetails);

        EmployeeLeave savedLeave = employeeLeaveRepository.save(leave);

        // Update a leave detail
        EmployeeLeave.LeaveDetail updatedDetail = new EmployeeLeave.LeaveDetail("Vacation", 9, 1, "2023-07-01", "2023-07-02", "upcoming");
        savedLeave.updateLeaveDetail(updatedDetail);

        EmployeeLeave updatedLeave = employeeLeaveRepository.save(savedLeave);

        assertEquals(1, updatedLeave.getLeaveDetails().size());
        EmployeeLeave.LeaveDetail resultDetail = updatedLeave.getLeaveDetails().get(0);
        assertEquals("Vacation", resultDetail.getType());
        assertEquals(Integer.valueOf(9), resultDetail.getAvailable());
        assertEquals(Integer.valueOf(1), resultDetail.getBooked());
        assertEquals("2023-07-01", resultDetail.getStartDate());
        assertEquals("2023-07-02", resultDetail.getEndDate());
        assertEquals("upcoming", resultDetail.getStatus());
    }

    @Test
    void testDeleteEmployeeLeave() {
        EmployeeLeave leave = new EmployeeLeave();
        leave.setEmpId("E001");
        leave.setYear("2023");
        EmployeeLeave savedLeave = employeeLeaveRepository.save(leave);

        employeeLeaveRepository.deleteById(savedLeave.getId());

        Optional<EmployeeLeave> deletedLeave = employeeLeaveRepository.findById(savedLeave.getId());
        assertFalse(deletedLeave.isPresent());
    }
}
