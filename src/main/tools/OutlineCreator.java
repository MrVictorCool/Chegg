package main.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class OutlineCreator {
    public static BufferedImage createOutline(BufferedImage src, Color outlineColor) {
        int w = src.getWidth();
        int h = src.getHeight();

        BufferedImage resultBufferedImage = new BufferedImage(w + 4, h + 4, BufferedImage.TYPE_INT_ARGB);

        int outlineRGB = outlineColor.getRGB();

        for (int x = 1; x < w - 1; x++) {
            for (int y = 1; y < h - 1; y++) {

                int alpha = (src.getRGB(x, y) >> 24) & 0xff;

                if (alpha == 0) {
                    boolean neighborOpaque = isOpaque(src, x + 1, y) || isOpaque(src, x - 1, y) || isOpaque(src, x, y + 1) || isOpaque(src, x, y - 1);

                    if (neighborOpaque) {
                        resultBufferedImage.setRGB(x + 2, y + 2, outlineRGB);
                    }
                }
                
            }
        }

        Graphics2D g2 = resultBufferedImage.createGraphics();
        g2.drawImage(src, 2, 2, null);
        g2.dispose();

        return resultBufferedImage;
    }

    private static boolean isOpaque(BufferedImage img, int x, int y) {
        return ((img.getRGB(x, y) >> 24) & 0xff) > 0;
    }
}
