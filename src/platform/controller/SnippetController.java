package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import platform.domain.Snippet;
import platform.service.DateConverter;
import platform.service.HeaderCreator;
import platform.service.SnippetService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class SnippetController {

    private final SnippetService snippetService;

    @Autowired
    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @GetMapping("/api/code/{uuid}")
    public ResponseEntity<Map<String, Object>> apiGetSnippet(@PathVariable String uuid) {
        Snippet snippet = snippetService.findByUuid(uuid);

        snippetService.processSnippet(snippet);

        return ResponseEntity.ok()
                .headers(HeaderCreator.create("application/json"))
                .body(Map.of("time", snippet.getTime(), "views", snippet.getViews(),"date", snippet.getDate(), "code", snippet.getCode()));
    }

    @GetMapping("/code/{uuid}")
    public String getSnippet(@PathVariable String uuid, Model model) {
        Snippet snippet = snippetService.findByUuid(uuid);

        snippetService.processSnippet(snippet);

        model.addAttribute("snippet", snippet);
        return "snippet";
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<Map<String, String>> createSnippet(@RequestBody Map<String, String> codeMap) {

        String dateTime = DateConverter.convertDateToString(LocalDateTime.now());
        String code = codeMap.get("code");
        String uuid = UUID.randomUUID().toString();
        int time = Integer.parseInt(codeMap.get("time"));
        boolean isTimeRestricted = time != 0;
        int views = Integer.parseInt(codeMap.get("views"));
        boolean isViewsRestricted = views != 0;
        Snippet snippet = new Snippet(dateTime, code, uuid, isTimeRestricted, time, isViewsRestricted, views);
        snippetService.save(snippet);
        System.err.println("Create snippet");
        System.err.println(snippet);

        return ResponseEntity.ok()
                .headers(HeaderCreator.create("application/json"))
                .body(Map.of("id", uuid));
    }

    @GetMapping("/code/new")
    public String snippetCreationForm() {
        return "create_snippet";
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<List<Map<String, Object>>> apiGetTenLatestSnippets() {
        return ResponseEntity.ok()
                .headers(HeaderCreator.create("application/json"))
                .body(snippetService.find10Latest());
    }

    @GetMapping("/code/latest")
    public String getTenLatestSnippets(Model model) {
        model.addAttribute("latest", snippetService.find10Latest());

        return "latest";
    }
}
