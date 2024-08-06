package com.texsmartly.LeaveProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "general_settings")
public class GeneralSettings {
    @Id
    private String id;
    private int inclusionDays;
    private boolean payrollReportEnabled;
    private boolean includeWeekends;
    private boolean includeHolidays;
    private String lofReportOption;
    private boolean enablePasswordProtection;
    private boolean leaveCancellationPastPeriod;
    private boolean leaveCancellationCurrentDay;
    private boolean allowPartialLeave;
    private boolean makeReasonMandatory;
    private String reminderEmail;
}
