package org.enoch.snark.gi.command.impl;

import org.enoch.snark.gi.macro.GIUrlBuilder;
import org.enoch.snark.gi.command.AbstractCommand;
import org.enoch.snark.gi.command.SpyObserver;
import org.enoch.snark.instance.Universe;
import org.enoch.snark.model.Planet;
import org.enoch.snark.model.SpyInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.enoch.snark.gi.command.CommandType.INTERFACE_REQUIERED;

public class ReadSpyInfoCommand extends AbstractCommand {

    private final Universe universe;
    private Planet planet;
    private SpyObserver observer;

    public ReadSpyInfoCommand(Universe universe, Planet planet, SpyObserver observer) {
        super(universe, INTERFACE_REQUIERED);
        this.universe = universe;
        this.planet = planet;
        this.observer = observer;
    }

    @Override
    public void execute() {
        new GIUrlBuilder(universe).openMessages();
        final ChromeDriver chromeDriver = universe.session.getChromeDriver();
//        final List<WebElement> overlay = chromeDriver.findElementsByClassName("fright txt_link msg_action_link overlay");
        final List<WebElement> elements = chromeDriver.findElements(By.tagName("a"));
        final String[] links = universe.session.getSelenium().getAllLinks();
        List<String> spyReports = new ArrayList<>();
        for (WebElement element : elements) {
            final String href = element.getAttribute("href");
            if(href != null && href.contains("messageId") && href.contains("ajax=1")) {
                spyReports.add(href);
            }
        }

        SpyInfo info = new SpyInfo();
        info.planet = planet;

        if(observer!= null) {
            observer.report(info);
        }
    }
}
