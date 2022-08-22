package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bezkoder.springjwt.Business.CsvReader;
import com.bezkoder.springjwt.Business.CsvWriter;
import com.bezkoder.springjwt.Business.FScore;
import com.bezkoder.springjwt.models.*;
import com.bezkoder.springjwt.service.DateTimeExtractor;
import com.bezkoder.springjwt.utils.SettingsBuilder;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestRulesController extends BaseController{
    private static String TRAINING_DATA = System.getProperty("user.dir") + "/data/train.csv";
    private static String TEST_RESULTS_FILE = System.getProperty("user.dir") + "/data/results.txt";

    @GetMapping("/testRule")
    public String testRule() throws Exception {
        CsvReader reader = new CsvReader();
        String csv = TRAINING_DATA;
        CsvWriter writer = new CsvWriter();
        String fileToWrite = TEST_RESULTS_FILE;

        int tp = 0;
        int fp = 0;
        int tn = 0;
        int fn = 0;

        List<Tip> tips = reader.getTipsFromFile(csv, ",");
        for (Tip tip : tips) {
            String patternString = "<text>(.*?)</text>";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(tip.getTipText());
            HashSet<String> annotated = new HashSet<String>();

            while (matcher.find()) {
                String result = matcher.group().replace("<text>", "").replace("</text>", "").replace("?", "-").replace("�", "-");
                annotated.add(result.trim());
            }
            String text = tip.getTipText().replace("<text>", "").replace("</text>", "").replace("?", "-").replace("�", "-").trim();
            System.out.println(text);

            Settings settings = new SettingsBuilder().build();

            TreeSet<TemporalExtraction> predicted = DateTimeExtractor.extract(text,settings);

            System.out.println("predicted="+predicted);
            if (predicted.size() == 0 && annotated.size() == 0) {
                tn++;
                continue;
            }
            if (annotated.size() == 0 && predicted.size() != 0) {
                System.out.println("False positive " + predicted + " " + annotated);
                writer.writeToFile(fileToWrite, (text));
                writer.writeToFile(fileToWrite, "False positive " + predicted + " " + annotated);
                fp++;
                continue;
            }

            if (predicted.size() == 0 && annotated.size() != 0) {
                System.out.println("False negative " + predicted + " " + annotated);
                writer.writeToFile(fileToWrite, (text));
                writer.writeToFile(fileToWrite, "False negative " + predicted + " " + annotated);

                fn++;
                continue;
            }

            HashSet<String> predictions = new HashSet<String>();
            for (TemporalExtraction cm : predicted) {
                predictions.add(cm.getTemporalExpression().trim());
            }

            for (String annotation : annotated) {
                if (!predictions.contains(annotation)) {
                    System.out.println(text);
                    System.out.println("False negative " + predictions + " " + annotated);
                    writer.writeToFile(fileToWrite, (text));
                    writer.writeToFile(fileToWrite, ("False negative " + predictions + " " + annotated));
                    fn++;
                }
            }

            for (String prediction : predictions) {
                if (!annotated.contains(prediction)) {
                    System.out.println(tip.getTipText());
                    System.out.println("False positive " + predictions + " " + annotated);
                    writer.writeToFile(fileToWrite, (text));
                    writer.writeToFile(fileToWrite, ("False positive " + predictions + " " + annotated));
                    fp++;
                }
            }
            tp++;
        }

        FScore score = new FScore();

        System.out.println("True positive " + tp);
        System.out.println("False positive " + fp);
        System.out.println("True negative " + tn);
        System.out.println("False negative " + fn);

        writer.writeToFile(fileToWrite, ("True positive " + tp));
        writer.writeToFile(fileToWrite, ("False positive " + fp));
        writer.writeToFile(fileToWrite, ("True negative " + tn));
        writer.writeToFile(fileToWrite, ("False negative " + fn));

        double accuracy = score.accuracy(tp, tn, fp, fn);
        double precision = score.precision(tp, fp);
        double recall = score.recall(tp, fn);
        double fScore = score.f1Score(precision, recall);

        System.out.println("F-Score: " + fScore);
        System.out.println("Precision: " + precision);
        System.out.println("Recall: " + recall);
        System.out.println("Accuracy: " + accuracy);

        writer.writeToFile(fileToWrite, ("F-Score: " + fScore));
        writer.writeToFile(fileToWrite, ("Precision: " + precision));
        writer.writeToFile(fileToWrite, ("Recall: " + recall));
        writer.writeToFile(fileToWrite, ("Accuracy: " + accuracy));

        return "F-Score="+fScore+", Precision="+precision+", Recall="+recall+", Accuracy="+accuracy;
    }
}
