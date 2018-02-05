package org.enoch.snark.gi;

import org.enoch.snark.AppProperties;
import org.enoch.snark.model.Planet;
import org.enoch.snark.model.SourcePlanet;

public class GIUrlBuilder {

    public static final String PAGE_OVERVIEW = "overview";
    public static final String PAGE_BASE_FLEET = "fleet1";

    private String page=PAGE_OVERVIEW;
    private SourcePlanet planet;
    private Planet target;

    public GIUrlBuilder(SourcePlanet planet) {
        this.planet = planet;
    }
    public String build() {
        final StringBuilder builder = new StringBuilder();
        builder.append(AppProperties.mainUrl).append("?")
                .append("page=").append(page)
                .append("&cp=").append(planet.planetId);
        if(target != null) {
            builder.append("&galaxy="+target.galaxy+
                    "&system="+target.system+
                    "&position="+target.position+
                    "&type=1&mission=1");
        }
        return builder.toString();
    }

    public GIUrlBuilder setPage(String page) {
        this.page = page;
        return this;
    }

    public GIUrlBuilder setTarget(Planet target) {
        this.target = target;
        return this;
    }
}
