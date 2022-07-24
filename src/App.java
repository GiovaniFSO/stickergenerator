import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class  App {
    public static void main(String[] args) throws Exception{
        String url = "https://api.mocki.io/v2/549a5d8b";
        var extractor = new ContentExtractorIMDB();
        //String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
        //var extractor = new ContentExtractorNasa();

        var http = new ClientHttp();
        String json = http.searchData(url);


        List<Content> contentList =  extractor.extractContents(json);

        var generator = new StickerGenerator();
        for (int i = 0; i < 3; i++){
            Content content = contentList.get(i);

            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            String filename = "output/"+ content.getTitle() + ".png";

            generator.create(inputStream, filename);
            System.out.println(content.getTitle());
        }
    }

}
