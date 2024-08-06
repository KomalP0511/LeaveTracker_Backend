package com.texsmartly.LeaveProject.form;
import com.texsmartly.LeaveProject.model.Form;
import com.texsmartly.LeaveProject.repository.FormRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class FormRepositoryTest {
    @Autowired
    private FormRepository formRepository;

    @Test
    void testSaveForm() {
        Form form = new Form();
        form.setEmployeeId("1");
        form.setLeaveType("Vacation");

        Form savedForm = formRepository.save(form);

        assertNotNull(savedForm);
        assertNotNull(savedForm.getEmployeeId());
        assertEquals("Vacation", savedForm.getLeaveType());
    }

    @Test
    void testFindAllForms() {
        Form form1 = new Form();
        form1.setEmployeeId("1");
        Form form2 = new Form();
        form2.setEmployeeId("2");

        formRepository.save(form1);
        formRepository.save(form2);

        List<Form> forms = formRepository.findAll();

        assertFalse(forms.isEmpty());
        assertTrue(forms.size() >= 2);
    }

    @Test
    void testUpdateForm() {
        Form form = new Form();
        form.setEmployeeId("1");
        form.setLeaveType("Vacation");

        Form savedForm = formRepository.save(form);

        savedForm.setLeaveType("Sick Leave");
        Form updatedForm = formRepository.save(savedForm);

        assertEquals("Sick Leave", updatedForm.getLeaveType());
    }

    @Test
    void testDeleteForm() {
        Form form = new Form();
        form.setEmployeeId("1");

        Form savedForm = formRepository.save(form);
        formRepository.deleteById(savedForm.getEmployeeId());

        Optional<Form> deletedForm = formRepository.findById(savedForm.getEmployeeId());

        assertFalse(deletedForm.isPresent());
    }
}
