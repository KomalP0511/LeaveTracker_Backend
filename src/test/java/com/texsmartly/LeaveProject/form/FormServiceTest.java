package com.texsmartly.LeaveProject.form;
import com.texsmartly.LeaveProject.model.Form;
import com.texsmartly.LeaveProject.repository.FormRepository;
import com.texsmartly.LeaveProject.service.FormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class FormServiceTest {

    @Mock
    private FormRepository repository;

    @InjectMocks
    private FormService formService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddForm() {
        Form form = new Form();
        when(repository.save(any(Form.class))).thenReturn(form);

        Form result = formService.addForm(form);

        assertNotNull(result);
        assertNotNull(result.getEmployeeId());
        verify(repository, times(1)).save(form);
    }

    @Test
    void testFindAllForms() {
        List<Form> forms = Arrays.asList(new Form(), new Form());
        when(repository.findAll()).thenReturn(forms);

        List<Form> result = formService.findAllForms();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testUpdateForm() {
        String id = "1";
        Form form = new Form();
        when(repository.existsById(id)).thenReturn(true);
        when(repository.save(any(Form.class))).thenReturn(form);

        Form result = formService.updateForm(id, form);

        assertNotNull(result);
        assertEquals(id, result.getEmployeeId());
        verify(repository, times(1)).existsById(id);
        verify(repository, times(1)).save(form);
    }

    @Test
    void testUpdateFormNotFound() {
        String id = "1";
        Form form = new Form();
        when(repository.existsById(id)).thenReturn(false);

        Form result = formService.updateForm(id, form);

        assertNull(result);
        verify(repository, times(1)).existsById(id);
        verify(repository, never()).save(any(Form.class));
    }

    @Test
    void testDeleteForm() {
        String id = "1";
        when(repository.existsById(id)).thenReturn(true);

        boolean result = formService.deleteForm(id);

        assertTrue(result);
        verify(repository, times(1)).existsById(id);
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteFormNotFound() {
        String id = "1";
        when(repository.existsById(id)).thenReturn(false);

        boolean result = formService.deleteForm(id);

        assertFalse(result);
        verify(repository, times(1)).existsById(id);
        verify(repository, never()).deleteById(any());
    }
}
