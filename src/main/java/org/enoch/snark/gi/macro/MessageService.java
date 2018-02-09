package org.enoch.snark.gi.macro;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import org.enoch.snark.instance.Universe;
import org.enoch.snark.model.Planet;
import org.enoch.snark.model.SpyInfo;

import java.io.File;


public class MessageService {
    public static final String PATH_TO_MESSAGE = "message/";
    public static final String SPY_DIRECTORY = "spy/";
    public static final String WAR_DIRECTORY = "war/";

    private String mainPath;
    private File spyDir;
    private File warDir;

    private ListMultimap<Planet, SpyInfo> spyMessageMap = ArrayListMultimap.create();
    private ListMultimap<Planet, SpyInfo> warMessageMap = ArrayListMultimap.create();

    MessageService(Universe universe) {
        mainPath = universe.appProperties.server; // TODO: 2018-02-09 add correct path to main directory

        String spyPath = mainPath+PATH_TO_MESSAGE+SPY_DIRECTORY;
        spyDir = new File(spyPath);
        if(!spyDir.exists()) {
            spyDir.mkdirs();
        }

        String warPath = mainPath+PATH_TO_MESSAGE+WAR_DIRECTORY;
        warDir = new File(warPath);
        if(!warDir.exists()) {
            warDir.mkdirs();
        }

        loadSpyMesseges();
        loadWarMesseges();
    }

    private void loadSpyMesseges() {
        // TODO: 2018-02-09 load to map

    }

    private void loadWarMesseges() {
    }

    public boolean isNewInLastMinute(Planet planet) {
        return false;
    }

    public SpyInfo getLastSpyInfo(Planet planet) {
        return new SpyInfo();
    }
}
