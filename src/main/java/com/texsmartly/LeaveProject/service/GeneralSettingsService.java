package com.texsmartly.LeaveProject.service;

import com.texsmartly.LeaveProject.model.GeneralSettings;
import com.texsmartly.LeaveProject.repository.GeneralSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class GeneralSettingsService {
    @Autowired
    private GeneralSettingsRepository repository;

    public GeneralSettings saveSettings(GeneralSettings settings) {
        return repository.save(settings);
    }

    public List<GeneralSettings> getAllSettings() {
        return repository.findAll();
    }

    public Optional<GeneralSettings> getSettingsById(String id) {
        return repository.findById(id);
    }

    public GeneralSettings updateSettings(String id, GeneralSettings settings) {
        if (repository.existsById(id)) {
            settings.setId(id);
            return repository.save(settings);
        } else {
            return null;
        }
    }

    public boolean deleteSettings(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
