package com.itera.faergestad.whatsforlunch.services;

import com.itera.faergestad.whatsforlunch.api.domain.Lunch;
import com.itera.faergestad.whatsforlunch.repository.CafeteriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LunchService {

    private final PdfReaderService pdfReaderService;
    private final CafeteriaRepository cafeteriaRepository;

    public Lunch getTodaysLunch() {
        String menuUrl = cafeteriaRepository.fetchMenuLink();
        cafeteriaRepository.retrievePdfMenu(menuUrl);
        return pdfReaderService.getTodaysLunch();
    }
}
