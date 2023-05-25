package org.example.springtaskmgrv2.controllers;

import org.apache.coyote.Response;
import org.example.springtaskmgrv2.entities.NoteEntity;
import org.example.springtaskmgrv2.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks/{id}/notes")
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping("")
    public ResponseEntity<List<String>> getNotes(@PathVariable("id") Integer taskId){
        List<String> taskNotes = noteService.getAllTaskNotes(taskId);

        return ResponseEntity.ok(taskNotes);
    }

    @PostMapping("")
    public ResponseEntity<NoteEntity> addNotes(@PathVariable("id") Integer taskId, @RequestBody NoteEntity note){

        NoteEntity newNote = noteService.addNote(taskId, note.getBody());

        return ResponseEntity.created(URI.create("/tasks/" + taskId + "/notes/" + newNote.getId())).body(newNote);

    }

    @DeleteMapping("/{noteid}")
    public ResponseEntity<NoteEntity> addNotes(@PathVariable("id") Integer taskId, @PathVariable("noteid") Integer noteId){

        NoteEntity deletedNote = noteService.deleteNote(taskId, noteId);

        return ResponseEntity.accepted().body(deletedNote);
    }


}
