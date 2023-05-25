package org.example.springtaskmgrv2.services;

import org.example.springtaskmgrv2.entities.NoteEntity;
import org.example.springtaskmgrv2.entities.TaskEntity;
import org.example.springtaskmgrv2.repositories.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    NotesRepository notesRepository;

    @Autowired
    TaskService taskService;

    public List<String> getAllTaskNotes(Integer taskId){

        TaskEntity task = taskService.getTaskById(taskId);

        List<NoteEntity> notes = notesRepository.findByTaskId(taskId);

        List<String> taskNotes = new ArrayList<>();

        for(NoteEntity note : notes)
            taskNotes.add(note.getBody());

        return taskNotes;
    }

    public NoteEntity addNote(Integer taskId, String noteText){
        TaskEntity task = taskService.getTaskById(taskId);

        NoteEntity newNote = new NoteEntity();
        newNote.setBody(noteText);
        newNote.setTask(task);

        notesRepository.save(newNote);
        return newNote;
    }

    public NoteEntity deleteNote(Integer taskId, Integer noteId){
        NoteEntity deletedNote = notesRepository.findByIdAndTaskId(taskId, noteId);

        notesRepository.deleteById(noteId);
        return deletedNote;

    }
}
