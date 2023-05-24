package org.example.springtaskmgrv2.repositories;

import org.example.springtaskmgrv2.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * In general @Repository annotation requires only when
 * we have service layer or else this annotation is not required.
 */
@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Integer> {

    List<TaskEntity> findAllByCompleted(boolean completed);


    //Ideally this is 'business logic' terminology (i.e. 'overdue') so shouldn't be here
    @Query("" +
            "SELECT t from tasks t" +
               "WHERE t.completed=false "+
               " AND t.dueDate < CURRENT_DATE"
    )
    List<TaskEntity> findAllOverdue();

    //this would give same result as overdue
    List<TaskEntity> findAllByCompletedAndDueDateBefore(boolean completed, Date dueDate);

    /**
     * below query is just showcase how we pass dynamic input.
     * ?1 is for 1st parameter similary we can use ?2.. and so on
     * @param title
     * @return
     */
    @Query("SELECT t from tasks t where title like %?1%")
    List<TaskEntity> findAllByTitle(String title);

    /**
     * Below query would give the same result as above one.
     * @param titleFragment
     * @return
     */
    List<TaskEntity> findAllByTitleIsContainingIgnoreCase(String titleFragment);

}
