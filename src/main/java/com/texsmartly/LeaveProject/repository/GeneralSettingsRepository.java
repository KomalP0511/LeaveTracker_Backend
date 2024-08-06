package com.texsmartly.LeaveProject.repository;

import com.texsmartly.LeaveProject.model.GeneralSettings;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GeneralSettingsRepository extends MongoRepository<GeneralSettings, String> {
}
