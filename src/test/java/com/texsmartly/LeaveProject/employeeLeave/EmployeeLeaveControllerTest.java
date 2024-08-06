package com.texsmartly.LeaveProject.employeeLeave;
import com.texsmartly.LeaveProject.api.EmployeeLeaveController;
import com.texsmartly.LeaveProject.model.EmployeeLeave;
import com.texsmartly.LeaveProject.service.EmployeeLeaveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class EmployeeLeaveControllerTest {
    @Mock
    private EmployeeLeaveService employeeLeaveService;

    @InjectMocks
    private EmployeeLeaveController employeeLeaveController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployeeLeaves() {
        List<EmployeeLeave> leaves = Arrays.asList(new EmployeeLeave(), new EmployeeLeave());
        when(employeeLeaveService.getAllEmployeeLeaves()).thenReturn(leaves);

        ResponseEntity<List<EmployeeLeave>> response = employeeLeaveController.getAllEmployeeLeaves();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetEmployeeLeaveById() {
        String id = "1";
        EmployeeLeave leave = new EmployeeLeave();
        when(employeeLeaveService.getEmployeeLeaveById(id)).thenReturn(Optional.of(leave));

        ResponseEntity<EmployeeLeave> response = employeeLeaveController.getEmployeeLeaveById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetEmployeeLeaveByEmpIdAndYear() {
        String empId = "E001";
        String year = "2023";
        EmployeeLeave leave = new EmployeeLeave();
        when(employeeLeaveService.getEmployeeLeaveByEmpIdAndYear(empId, year)).thenReturn(Optional.of(leave));

        ResponseEntity<EmployeeLeave> response = employeeLeaveController.getEmployeeLeaveByEmpIdAndYear(empId, year);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testCreateEmployeeLeave() {
        EmployeeLeave leave = new EmployeeLeave();
        when(employeeLeaveService.createEmployeeLeave(any(EmployeeLeave.class))).thenReturn(leave);

        ResponseEntity<EmployeeLeave> response = employeeLeaveController.createEmployeeLeave(leave);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testUpdateEmployeeLeave() {
        String id = "1";
        EmployeeLeave leave = new EmployeeLeave();
        when(employeeLeaveService.updateEmployeeLeave(eq(id), any(EmployeeLeave.class))).thenReturn(Optional.of(leave));

        ResponseEntity<EmployeeLeave> response = employeeLeaveController.updateEmployeeLeave(id, leave);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testUpdateEmployeeLeaveByEmpIdAndYear() {
        String empId = "E001";
        String year = "2023";
        EmployeeLeave leave = new EmployeeLeave();
        when(employeeLeaveService.updateEmployeeLeaveByEmpIdAndYear(eq(empId), eq(year), any(EmployeeLeave.class)))
                .thenReturn(Optional.of(leave));

        ResponseEntity<EmployeeLeave> response = employeeLeaveController.updateEmployeeLeaveByEmpIdAndYear(empId, year, leave);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testDeleteEmployeeLeave() {
        String id = "1";
        when(employeeLeaveService.deleteEmployeeLeave(id)).thenReturn(true);

        ResponseEntity<Void> response = employeeLeaveController.deleteEmployeeLeave(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
