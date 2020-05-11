import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer extends Thread {
    private String dstFolder;
    private File[] files;

    ImageResizer(String dstFolder, File[] files) {
        this.dstFolder = dstFolder;
        this.files = files;
    }

    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                int newWidth = 300;
                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth));


                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(scale(image, newWidth, newHeight), "jpg", newFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private BufferedImage scale(BufferedImage image, int newWidth, int newHeight) {

        int type = (image.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage ret = image;
        BufferedImage newImage = null;
        Graphics2D g2 = null;

        int w = image.getWidth();
        int h = image.getHeight();

        int prevW = w;
        int prevH = h;

        do {
            if (w > newWidth) {
                w /= 2;
                w = (w < newWidth) ? newWidth : w;
            }

            if (h > newHeight) {
                h /= 2;
                h = (h < newHeight) ? newHeight : h;
            }

            if (newImage == null) {
                newImage = new BufferedImage(w, h, type);
                g2 = newImage.createGraphics();
            }

            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(ret, 0, 0, w, h, 0, 0, prevW, prevH, null);

            prevW = w;
            prevH = h;
            ret = newImage;
        } while (w != newWidth || h != newHeight);

        if (g2 != null) {
            g2.dispose();
        }

        if (newWidth != ret.getWidth() || newHeight != ret.getHeight()) {
            newImage = new BufferedImage(newWidth, newHeight, type);
            g2 = newImage.createGraphics();
            g2.drawImage(ret, 0, 0, null);
            g2.dispose();
            ret = newImage;
        }

        return ret;

    }
}
