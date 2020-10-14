package fr.dawoox.akasuki.utils;

import com.apptastic.rssreader.Item;
import com.apptastic.rssreader.RssReader;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Wrapper for rss flux.
 * @author Dawoox
 * @version 1.0.0
 */
public class RssUtils {

    /**
     *Return a items from rss flux.
     * @param url
     * Any Url into a String
     * @param keyWord
     * keyWord to find into the title of items.
     * @return
     * List of Item from the rss provided.
     * @since 1.0.0
     */
    public static List<Item> getItemsFromRss(String url, String keyWord){
        RssReader reader = new RssReader();
        Stream<Item> rssFeed = null;
        try {
            rssFeed = reader.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Item> articles = rssFeed.filter(i -> i.getTitle().equals(Optional.of(keyWord)))
                .collect(Collectors.toList());
        return articles;
    }
}
