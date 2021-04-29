package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void im() {

        Color white = new Color(255, 255, 255);
        Color blue = new Color(0, 0, 255);
        ImageWriter Image = new ImageWriter("Pic", 800, 500);
        double reshetX = Image.getNx()/16;
        double reshetY = Image.getNy()/10;
        for(int j = 0; j < 500 ; j++) {
            for(int i = 0; i < 800 ; i++){
                if((i % reshetX == 0)|| (j % reshetY == 0)){
                    Image.writePixel(i, j, white);
                }
                else{
                    Image.writePixel(i, j, blue);
                }
            }
        }

        Image.writeToImage();
    }
}