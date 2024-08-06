package com.texsmartly.LeaveProject.api;

import com.texsmartly.LeaveProject.model.GeneralSettings;
import com.texsmartly.LeaveProject.service.GeneralSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/general-settings")
@CrossOrigin("*")
public class GeneralSettingsController {
    @Autowired
    private GeneralSettingsService settingsService;

    @PostMapping
    public ResponseEntity<GeneralSettings> addSettings(@RequestBody GeneralSettings settings) {
        GeneralSettings savedSettings = settingsService.saveSettings(settings);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSettings);
    }

    @GetMapping
    public List<GeneralSettings> getSettings() {
        return settingsService.getAllSettings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralSettings> getSettingsById(@PathVariable String id) {
        Optional<GeneralSettings> settings = settingsService.getSettingsById(id);
        return settings.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralSettings> updateSettings(@PathVariable String id, @RequestBody GeneralSettings settings) {
        GeneralSettings updatedSettings = settingsService.updateSettings(id, settings);
        if (updatedSettings != null) {
            return ResponseEntity.ok(updatedSettings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSettings(@PathVariable String id) {
        boolean isDeleted = settingsService.deleteSettings(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
