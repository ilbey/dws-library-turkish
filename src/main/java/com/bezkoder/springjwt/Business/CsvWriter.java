package com.bezkoder.springjwt.Business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.bezkoder.springjwt.models.Tip;

public class CsvWriter {

    public void writeCSVToFile(String file, List<Tip> tips) {
        for (Tip tip : tips) {
            String line = getLine(tip.getVenueId(), String.valueOf(tip.getCreatedAt()),
                    tip.getTipText());
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)))) {
                out.println(line);
            } catch (IOException e) {

            }
        }
    }

    public void writeToFile(String file, String text) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)))) {
            out.println(text);
        } catch (IOException e) {

        }
    }

    private String getLine(String... args) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < args.length - 1; i++) {
            string.append(args[i]);
            string.append(";");
        }
        string.append(args[args.length - 1]);
        return string.toString();
    }

}