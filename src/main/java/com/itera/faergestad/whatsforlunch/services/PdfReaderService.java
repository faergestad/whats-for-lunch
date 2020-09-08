package com.itera.faergestad.whatsforlunch.services;

import com.google.common.collect.Lists;
import com.itera.faergestad.whatsforlunch.api.domain.Dish;
import com.itera.faergestad.whatsforlunch.api.domain.Lunch;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Slf4j
@Service
public class PdfReaderService {


    public Lunch getTodaysLunch() {

        int menuNumber = findLatestMenu();

        File f = new File(String.format("files/meny%d.pdf", menuNumber));
        String parsedText = "";
        PDFParser parser = null;
        try {
            parser = new PDFParser(new RandomAccessFile(f, "r"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            parser.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }

        COSDocument cosDoc = null;
        try {
            cosDoc = parser.getDocument();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PDFTextStripper pdfStripper = null;
        try {
            pdfStripper = new PDFTextStripper();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PDDocument pdDoc = new PDDocument(cosDoc);
        try {
            parsedText = pdfStripper.getText(pdDoc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] parsedTextParts = parsedText.split("\n");

        Lunch lunch = new Lunch();
        List<Dish> dishes = Lists.newArrayList();
        for (String part : parsedTextParts) {
            if (!part.toUpperCase().equals(part)) {
                Dish dish = new Dish(part);
                dishes.add(dish);
            }
        }
        lunch.setDishes(dishes);

        return lunch;
    }

    private int findLatestMenu() {
        AtomicInteger latestNum = new AtomicInteger(0);
        try (Stream<Path> paths = Files.walk(Paths.get("files/"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(file -> determineHighestNum(file, latestNum));
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Latest menu number is {}", latestNum.get());
        return latestNum.get();
    }

    private void determineHighestNum(Path file, AtomicInteger num) {
        int menuNum = Integer.parseInt(file.getFileName().toString().substring(4, file.getFileName().toString().lastIndexOf(".")));
        if (menuNum > num.get())
            num.set(menuNum);
    }
}
