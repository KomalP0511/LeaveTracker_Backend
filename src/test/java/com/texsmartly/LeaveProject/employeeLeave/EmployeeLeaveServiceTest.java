package com.texsmartly.LeaveProject.employeeLeave;
import com.texsmartly.LeaveProject.model.EmployeeLeave;
import com.texsmartly.LeaveProject.repository.EmployeeLeaveRepository;
import com.texsmartly.LeaveProject.service.EmployeeLeaveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class EmployeeLeaveServiceTest {
    @Mock
    private EmployeeLeaveRepository employeeLeaveRepository;

    @InjectMocks
    private EmployeeLeaveService employeeLeaveService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployeeLeaves() {
        List<EmployeeLeave> leaves = Arrays.asList(new EmployeeLeave(), new EmployeeLeave());
        when(employeeLeaveRepository.findAll()).thenReturn(leaves);

        List<EmployeeLeave> result = employeeLeaveService.getAllEmployeeLeaves();

        assertEquals(2, result.size());
    }

    @Test
    void testGetEmployeeLeaveById() {
        String id = "1";
        EmployeeLeave leave = new EmployeeLeave();
        when(employeeLeaveRepository.findById(id)).thenReturn(Optional.of(leave));

        Optional<EmployeeLeave> result = employeeLeaveService.getEmployeeLeaveById(id);

        assertTrue(result.isPresent());
    }

    @Test
    void testGetEmployeeLeaveByEmpIdAndYear() {
        String empId = "E001";
        String year = "2023";
        EmployeeLeave leave = new EmployeeLeave();
        when(employeeLeaveRepository.findByEmpIdAndYear(empId, year)).thenReturn(Optional.of(leave));

        Optional<EmployeeLeave> result = employeeLeaveService.getEmployeeLeaveByEmpIdAndYear(empId, year);

        assertTrue(result.isPresent());
    }

    @Test
    void testCreateEmployeeLeave() {
        EmployeeLeave leave = new EmployeeLeave();
        when(employeeLeaveRepository.save(any(EmployeeLeave.class))).thenReturn(leave);

        EmployeeLeave result = employeeLeaveService.createEmployeeLeave(leave);

        assertNotNull(result);
    }

    @Test
    void testUpdateEmployeeLeave() {
        String id = "1";
        EmployeeLeave leave = new EmployeeLeave();
        when(employeeLeaveRepository.existsById(id)).thenReturn(true);
        when(employeeLeaveRepository.save(any(EmployeeLeave.class))).thenReturn(leave);

        Optional<EmployeeLeave> result = employeeLeaveService.updateEmployeeLeave(id, leave);

        assertTrue(result.isPresent());
    }

    @Test
    void testUpdateEmployeeLeaveByEmpIdAndYear() {
        String empId = "E001";
        String year = "2023";
        EmployeeLeave leave = new EmployeeLeave();
        when(employeeLeaveRepository.findByEmpIdAndYear(empId, year)).thenReturn(Optional.of(leave));
        when(employeeLeaveRepository.save(any(EmployeeLeave.class))).thenReturn(leave);

        Optional<EmployeeLeave> result = employeeLeaveService.updateEmployeeLeaveByEmpIdAndYear(empId, year, leave);

        assertTrue(result.isPresent());
    }

    @Test
    void testDeleteEmployeeLeave() {
        String id = "1";
        when(employeeLeaveRepository.existsById(id)).thenReturn(true);

        boolean result = employeeLeaveService.deleteEmployeeLeave(id);

        assertTrue(result);
        verify(employeeLeaveRepository, times(1)).deleteById(id);
    }
}
