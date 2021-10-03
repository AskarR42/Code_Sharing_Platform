package platform.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class HeaderCreator {

    public static HttpHeaders create(String contentType) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", contentType);
        return headers;
    }
}
