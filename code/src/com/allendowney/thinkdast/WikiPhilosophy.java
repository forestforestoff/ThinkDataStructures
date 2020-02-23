package com.allendowney.thinkdast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiPhilosophy {

    final static List<String> visited = new ArrayList<>();
    final static WikiFetcher wf = WikiFetcher.INSTANCE;

    /**
     * Tests a conjecture about Wikipedia and Philosophy.
     * <p>
     * https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
     * <p>
     * 1. Clicking on the first non-parenthesized, non-italicized link
     * 2. Ignoring external links, links to the current page, or red links
     * 3. Stopping when reaching "Philosophy", a page with no links or a page
     * that does not exist, or when a loop occurs
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String destination = "https://en.wikipedia.org/wiki/Philosophy";
        String source = "https://en.wikipedia.org/wiki/Java_(programming_language)";

        testConjecture(destination, source, 20);
    }

    /**
     * Starts from given URL and follows first link until it finds the destination or exceeds the limit.
     *
     * @param destination
     * @param source
     * @throws IOException
     */
    public static void testConjecture(String destination, String source, int limit) throws IOException {
        String url = source;
        for (int i = 0; i < limit; i++) {
            if (visited.contains(url)) {
                System.err.println("Already had such link, closed-loop.");
                return;
            }
            visited.add(url);
            Elements elements = WikiFetcher.INSTANCE.fetchWikipedia(url);
            WikiParser wikiParser = new WikiParser(elements);
            Element firstLink = wikiParser.findFirstLink();
            if (firstLink == null) {
                System.err.println("No valid links.");
                return;
            }

            System.out.println(firstLink.text() + " : " + firstLink.absUrl("href"));
            url = firstLink.absUrl("href");

            if (url.equals(destination)) {
                System.out.println("Gotcha!");
                break;
            }
        }
    }
}
