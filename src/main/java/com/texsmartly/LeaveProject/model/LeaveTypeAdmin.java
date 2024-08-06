package com.texsmartly.LeaveProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "leave_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveTypeAdmin {
    @Id
    private String id;

    private String name;
    private String image;
    private String code;
    private String type;
    private String unit;
    private int days;
    private int bookedDays;
    private String balanceBased;
    private String description;
    private String validityFrom;
    private String validityTo;
    private int effectiveAfter;
    private String effectiveAfterUnit;
    private String effectiveAfterFrom;
    private boolean accrual;
    private String accrualFrequency;
    private String accrualDay;
    private String accrualMonth;
    private int accrualDays;
    private String accrualType;
    private boolean reset;
    private String resetFrequency;
    private String resetDay;
    private String resetMonth;
    private int carryForward;
    private String carryForwardUnit;
    private int encashment;
    private String encashmentUnit;
    private boolean prorateAccrual;
    private String applicableFor;
    private String weekendsBetweenLeavePeriod;
    private String holidaysBetweenLeavePeriod;
}
