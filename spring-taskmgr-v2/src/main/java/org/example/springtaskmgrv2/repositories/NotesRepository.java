package org.example.springtaskmgrv2.repositories;

import org.example.springtaskmgrv2.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * In general @Repository annotation requires only when
 * we have service layer or else this annotation is not required.
 */
@Repository
public interface NotesRepository extends JpaRepository<NoteEntity, Integer> {
}
