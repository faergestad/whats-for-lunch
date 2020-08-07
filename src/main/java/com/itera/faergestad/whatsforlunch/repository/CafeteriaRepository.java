package com.itera.faergestad.whatsforlunch.repository;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

@Slf4j
@Repository
public class CafeteriaRepository {

    private final String CAFETERIA_URL = "http://portal.ny28.no/";

    public void retrievePdfMenu(String menuUrl) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<byte[]> response = restTemplate.exchange(menuUrl, HttpMethod.GET, entity, byte[].class, "1");

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            byte[] scenarioBytesFile = response.getBody();
            String fileName = menuUrl.substring(menuUrl.lastIndexOf('/') + 1);
            try {
                FileUtils.writeByteArrayToFile(new File("files/" + fileName), scenarioBytesFile);
                log.info("Saved file: {}", fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String fetchMenuLink() {
        String trimmedUrl = "";
        try {
            // Here we create a document object and use JSoup to fetch the website
            Document doc = Jsoup.connect(CAFETERIA_URL).get();

            String localizedHtml = doc.childNodes().get(1).toString().substring(doc.childNodes().get(1).toString().lastIndexOf("DAGENS MENY") - 100,
                    doc.childNodes().get(1).toString().lastIndexOf("DAGENS MENY"));
            // TODO legg til sjekk her for om localizedHtml inneholder http:// og .pdf
            log.info("Localized url: {}", localizedHtml);
            trimmedUrl = localizedHtml.substring(localizedHtml.lastIndexOf("http"), localizedHtml.lastIndexOf(".pdf") + 4);
            log.info("Prepared url: {}", trimmedUrl);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return trimmedUrl;
    }

}
