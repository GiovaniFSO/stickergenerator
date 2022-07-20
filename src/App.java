import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception{
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI address = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilme = parser.parse(body);
        var generator = new StickerGenerator();
        for (Map<String, String> filme: listaDeFilme) {
            String imageUrl = filme.get("image");
            String title = filme.get("title");
            InputStream inputStream = new URL(imageUrl).openStream();

            String filename = title + ".png";

            generator.create(inputStream, filename);
            System.out.println(filme.get("title"));
            System.out.println();
        }
    }

}
