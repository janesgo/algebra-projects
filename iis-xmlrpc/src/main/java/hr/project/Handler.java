package hr.project;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class Handler {
    public static final String URL = "https://vrijeme.hr/hrvatska_n.xml";

    public double getTemperature(String city) throws IOException {
        final Document document = Jsoup.parse(new URL(URL).openStream(), "UTF-8", "", Parser.xmlParser());
        final Elements cities = document.getElementsByTag("Grad");
        for (Element e : cities) {
            try {
                if (e.children().select("GradIme").first().text().equalsIgnoreCase(city)) {
                    return Double.parseDouble(e.children().select("Temp").first().text());
                }
            } catch (Exception ex) {
                break;
            }
        }
        return 9999;
    }
}