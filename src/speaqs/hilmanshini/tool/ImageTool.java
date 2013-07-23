/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package speaqs.hilmanshini.tool;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 *
 * @author webdevelop
 */
public class ImageTool {

    public static Bitmap getCroppedBitmap(Bitmap bitmap) {
         final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        final Bitmap outputBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);

        final Path path = new Path();
        path.addCircle(
                  (float)(width / 2)
                , (float)(height / 2)
                , (float) Math.min(width, (height / 2))
                , Path.Direction.CW);

        final Canvas canvas = new Canvas(outputBitmap);
        canvas.clipPath(path);
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.BLACK);
        p.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));
        p.setStrokeWidth(4);
        
        float x = ((float)(width / 2));
        float y =  ( (float)(height / 2));
        float rad = ((float) Math.min(width, (height / 2)));
        
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.drawCircle(x,y,rad  , p);
        return outputBitmap;
    }
}
