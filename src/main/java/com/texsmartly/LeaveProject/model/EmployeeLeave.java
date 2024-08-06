package com.texsmartly.LeaveProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "employeeLeaves")
@Data
@NoArgsConstructor
@AllArgsConstructor
@CompoundIndex(name = "empId_year_idx", def = "{'empId' : 1, 'year': 1}", unique = true)
public class EmployeeLeave {
    @Id
    private String id;
    private String empId;
    private String year;
    private List<EmployeeLeave.LeaveDetail> leaveDetails;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LeaveDetail {
        private String type;
        private Integer available;
        private Integer booked;
        private String startDate;
        private String endDate;
        private String status; // "upcoming" or "history"
    }

    public void updateLeaveDetail(LeaveDetail updatedDetail) {
        for (LeaveDetail detail : leaveDetails) {
            if (detail.getType().equals(updatedDetail.getType())) {
                detail.setAvailable(updatedDetail.getAvailable());
                detail.setBooked(updatedDetail.getBooked());
                detail.setStartDate(updatedDetail.getStartDate());
                detail.setEndDate(updatedDetail.getEndDate());
                detail.setStatus(updatedDetail.getStatus());
                return;
            }
        }
        leaveDetails.add(updatedDetail);
    }
}
