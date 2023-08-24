package onlineshop.example.beeshop.utils;

public class UrlHandler {
    public static String handleUrl(String url) {
        String[] urlParts = url.split("/");
        String lastPart = urlParts[urlParts.length-1];
        if (lastPart.startsWith("{")) {
            url = url.substring(0, url.length()-lastPart.length()) + "*";
        }
        return url;
    }
}
