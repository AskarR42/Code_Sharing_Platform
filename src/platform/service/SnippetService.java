package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.domain.Snippet;
import platform.exception.SnippetNotFoundException;
import platform.repo.SnippetRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SnippetService {

    private SnippetRepository snippetRepository;

    @Autowired
    public SnippetService(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    public Snippet findByUuid(String UUID) {
        return snippetRepository.findByUUID(UUID);
    }

    public Long findLastId() {
        return snippetRepository.count();
    }

    public List<Map<String, Object>> find10Latest() {
        List<Snippet> snippets = snippetRepository.findTop10ByTimeAndViewsOrderByIdDesc(0, 0);
        List<Map<String, Object>> answer = new ArrayList<>();
        for (Snippet snippet : snippets) {
            answer.add(Map.of("time", snippet.getTime(), "views", snippet.getViews(),"date", snippet.getDate(), "code", snippet.getCode()));
        }

        return answer;
    }

    public void save(Snippet snippet) {
        snippetRepository.save(snippet);
    }

    public void processSnippet(Snippet snippet) {
        if (snippet == null) {
            throw new SnippetNotFoundException();
        }

        LocalDateTime now = LocalDateTime.now();
        if (snippet.isTimeRestricted()) {
            int timeDelta = (int) ChronoUnit.SECONDS.between(DateConverter.convertStringToDate(snippet.getLastReachDate()), now);
            snippet.setLastReachDate(DateConverter.convertDateToString(now));

            snippet.setTime(Math.max(snippet.getTime() - timeDelta, 0));
            if (snippet.getTime() == 0) {
                snippetRepository.delete(snippet);
                throw new SnippetNotFoundException();
            }
        }

        if (snippet.isViewsRestricted()) {
            if (snippet.getViews() == 0) {
                snippetRepository.delete(snippet);
                throw new SnippetNotFoundException();
            }
            snippet.setViews(Math.max(snippet.getViews() - 1, 0));
        }

        snippetRepository.save(snippet);
    }
}
