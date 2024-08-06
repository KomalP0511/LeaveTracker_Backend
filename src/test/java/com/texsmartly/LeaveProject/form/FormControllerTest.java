package com.texsmartly.LeaveProject.form;
import com.texsmartly.LeaveProject.api.FormController;
import com.texsmartly.LeaveProject.model.Form;
import com.texsmartly.LeaveProject.service.FormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class FormControllerTest {

    @Mock
    private FormService formService;

    @InjectMocks
    private FormController formController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetForms() {
        List<Form> forms = Arrays.asList(new Form(), new Form());
        when(formService.findAllForms()).thenReturn(forms);

        List<Form> result = formController.getForms();

        assertEquals(2, result.size());
        verify(formService, times(1)).findAllForms();
    }

    @Test
    void testAddForm() {
        Form form = new Form();
        when(formService.addForm(any(Form.class))).thenReturn(form);

        ResponseEntity<Form> response = formController.addForm(form);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(formService, times(1)).addForm(form);
    }

    @Test
    void testUpdateForm() {
        String id = "1";
        Form form = new Form();
        when(formService.updateForm(eq(id), any(Form.class))).thenReturn(form);

        ResponseEntity<Form> response = formController.updateForm(id, form);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(formService, times(1)).updateForm(id, form);
    }

    @Test
    void testUpdateFormNotFound() {
        String id = "1";
        Form form = new Form();
        when(formService.updateForm(eq(id), any(Form.class))).thenReturn(null);

        ResponseEntity<Form> response = formController.updateForm(id, form);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(formService, times(1)).updateForm(id, form);
    }

    @Test
    void testDeleteForm() {
        String id = "1";
        when(formService.deleteForm(id)).thenReturn(true);

        ResponseEntity<Void> response = formController.deleteForm(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(formService, times(1)).deleteForm(id);
    }

    @Test
    void testDeleteFormNotFound() {
        String id = "1";
        when(formService.deleteForm(id)).thenReturn(false);

        ResponseEntity<Void> response = formController.deleteForm(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(formService, times(1)).deleteForm(id);
    }
}
