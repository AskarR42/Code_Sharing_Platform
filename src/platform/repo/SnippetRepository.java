package platform.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.domain.Snippet;

import java.util.List;

@Repository
public interface SnippetRepository extends CrudRepository<Snippet, Long> {

    public Snippet findByUUID(String uuid);

    public List<Snippet> findTop10ByTimeAndViewsOrderByIdDesc(int time, int views);
}

