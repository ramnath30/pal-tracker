package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class TimeEntryController {
    private TimeEntryRepository repository;

    @Autowired
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.repository = timeEntryRepository;
    }

    @PostMapping("time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        return ResponseEntity.created(null).body(repository.create(timeEntryToCreate));
    }

    @GetMapping("time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry entry = repository.find(timeEntryId);
        if(entry != null) {
            return ResponseEntity.ok(entry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
            return ResponseEntity.ok().body(repository.list());
    }

    @PutMapping("time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry entry = repository.update(timeEntryId, timeEntryToUpdate);
        if(entry != null) {
            return ResponseEntity.ok().body(entry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("time-entries/{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
        repository.delete(timeEntryId);
        return ResponseEntity.noContent().build();
    }
}
