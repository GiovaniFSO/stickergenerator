import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class StickerGenerator {

    public void create(InputStream inputStream, String nomeArquivo) throws Exception {
        //InputStream inputStream = new FileInputStream(new File("images/filme.jpg"));
        //InputStream inputStream = new URL("url-image").openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;
        var newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
        
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0,0, null);
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);
        graphics.drawString("The Best Movie", 100, newHeight - 100);
        ImageIO.write(newImage, "png", new File("output/"+nomeArquivo));
    }
}
