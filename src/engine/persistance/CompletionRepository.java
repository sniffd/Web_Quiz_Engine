package engine.persistance;

import engine.entity.Completion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CompletionRepository extends CrudRepository<Completion, Long> {
    Page<Completion> findByUser(String user, Pageable paging);
}
