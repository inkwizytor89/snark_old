package org.enoch.snark.model;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SpyInfo {
    public final File file;
    public String messageId;
    public Planet planet;
    public Long metal = 0L;
    public Long crystal = 0L;
    public Long deuterium = 0L;
    public Long power = 0L;
    public LocalDateTime date;

    public SpyInfo( File file) {
        this.file = file;
        String content= StringUtils.EMPTY;
        try {
            content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadResources(content);
    }
    private void loadResources(String content) {
        final String[] reportLines = content.split("\\R+");
        this.messageId = reportLines[0];
        for (int i = 1; i < reportLines.length; i++) {
            if(reportLines[i].equals("Raport szpiegowski z")) {
                final String reportInfo = reportLines[i + 1];
                final String[] split = reportInfo.split("\\s+");
                String time = split[split.length-1];
                String date = split[split.length-2];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
                this.date = LocalDateTime.parse(date +" " + time, formatter);
                planet = new Planet(split[split.length-3]);
                i++;
            }
            if(reportLines[i].equals("Surowce")) {
                metal = Long.parseLong(reportLines[i+1].replace(".", ""));
                crystal = Long.parseLong(reportLines[i+2].replace(".", ""));
                deuterium = Long.parseLong(reportLines[i+3].replace(".", ""));
                power = Long.parseLong(reportLines[i+4].replace(".", ""));
                i+=4;
            }
        }
    }

    public boolean isStillAvailable(long minutesOfAvailable) {
        return date.plusMinutes(minutesOfAvailable).isAfter(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return file.getName()+" m="+metal+" c="+crystal+" d="+deuterium+" p="+power+" date="+date;
    }
}
