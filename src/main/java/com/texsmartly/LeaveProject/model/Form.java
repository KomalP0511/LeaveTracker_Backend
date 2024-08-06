package com.texsmartly.LeaveProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "forms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Form {
    @Id
    private String employeeId;
    private String absenceType;
    private String leaveType;
    private String startDate;
    private String endDate;
    private String teamEmail;
    private String reason;


    public void setFormId(String s) {
        this.employeeId = s;
    }
}
