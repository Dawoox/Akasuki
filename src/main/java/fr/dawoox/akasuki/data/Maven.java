package fr.dawoox.akasuki.data;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.FileReader;
import java.io.IOException;

public class Maven {

    private static final MavenXpp3Reader reader = new MavenXpp3Reader();
    private static Model model;

    static {
        try {
            model = reader.read(new FileReader("pom.xml"));
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public static final String PROJECT_VERSION = model.getVersion();
    public static final String DISCORD4J_VERSION = model.getDependencies().get(0).getVersion();
}
