package com.github.arif043.mathematicus.graph;

import static com.github.arif043.mathematicus.ApplicationManager.dp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import com.github.arif043.mathematicus.R;
import ertugrul.arif.rechner.Evaluator;
import ertugrul.arif.rechner.Function;
import ertugrul.arif.rechner.SyntaxException;

// Hier werden die Funktionen gezeichnet
public class Koordinatensystem extends View {

    /**
     * Speichert die Funktionen in einem Array
     */
    private Function[] functions;
    /**
     * Speichert die Segmente in einem Array
     */
    private ArrayList<Segment> segments, newSegmentsBuffer;
    private Segment center;
    private Paint p;
    /**
     * minX und maxX definieren die Definitionsmenge auf der X-Achse
     * minY und maxY definieren die Wertemenge auf der Y-Achse
     * dX und dY sind die Deltawerte, die die Nachbarn annehmen
     * originX und originY speichern den Koordinatenursprung
     */
    private int minX, maxX, minY, maxY, dX, dY, originX, originY;
    /**
     * x und y speichern die Position
     * cmToPxWidth und cmToPxHeight speichern die Anzahl an physikalischen Pixel pro cm
     */
    private float x, y, cmToPxWidth, cmToPxHeight;

    public Koordinatensystem(Context context, AttributeSet set) {
        super(context, set);
        segments = new ArrayList<>(15);
        newSegmentsBuffer = new ArrayList<>();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        cmToPxWidth = metrics.xdpi / 2.54f;
        cmToPxHeight = metrics.ydpi / 2.54f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (p == null) {
            Log.d("GTR1", "" + getWidth());
            Log.d("GTR1", "" + getWidth() / 2);
            Log.d("GTR1", "" + getWidth() / 2 / cmToPxWidth);
            //Initialisierung einiger Instanzvariablen
            maxX = (int) (getWidth() / 2 / cmToPxWidth) * 10 + 10;
            minX = -maxX;
            Log.d("GTR1", minX + "");
            maxY = (int) (getHeight() / 2 / cmToPxHeight) + 1;
            minY = -maxY;
            dX = maxX * 2 - 10;
            dY = maxY * 2 + 1;
            originX = getWidth() / 2;
            originY = getHeight() / 2;
            // Paint instantiieren
            p = new Paint(Paint.ANTI_ALIAS_FLAG);
            p.setStrokeWidth(dp(1));
            p.setTextSize(dp(12));
            // Rootsegment und dessen Nachbarn instantiieren
            segments.add(center = new Segment(minX, maxX, minY, maxY, 0, 0));
            segments.get(0).createNeigbours();
        }
        for (Segment seg : segments) canvas.drawBitmap(seg.bitmap, x - seg.oriDeltaX, y - seg.oriDeltaY, p);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        // width und height setzen
        setMeasuredDimension(metrics.widthPixels, (int) (metrics.heightPixels * 0.7));
    }

    // Gettter
    public Function[] getFunctions() {
        return functions;
    }

    // Setter
    public void setFunctions(Function[] functions) {
        this.functions = functions;
    }

    public void setDeltaPosition(float deltaX, float deltaY) {
        x += deltaX;
        y += deltaY;
        float centerHypo = center.calcHypo();
        for (Segment seg : segments)
            if (seg != center && seg.calcHypo() < centerHypo) {
                center = seg;
                Log.d("GTR", "min " + seg.minX + "  max " + seg.maxX);
                new Thread(new Runnable() {
                    public void run() {
                        center.createNeigbours();
                    }
                }).start();
                break;
            }
    }

    /**
     * Segment repräsentiert einen Teilbereich des Koordinatensystems
     */
    private class Segment {

        /**
         * bitmap speichert das Bild
         */
        Bitmap bitmap;
        /**
         * @see Koordinatensystem
         */
        int minX, maxX, minY, maxY, oriDeltaX, oriDeltaY;

        /**
         *
         * @param minX Untere Grenze
         * @param maxX Obere Grenze
         * @param minY Minimaler Wert auf der Y-Achse
         * @param maxY Maximaler Wert auf der Y-Achse
         * @param oriDeltaX Zusätzliche Änderung auf der X-Achse zum Koordinatenursprung
         * @param oriDeltaY Zusätzliche Änderung auf der Y-Achse zum Koordinatenursprung
         * @see Koordinatensystem
         */
        public Segment(int minX, int maxX, int minY, int maxY, int oriDeltaX, int oriDeltaY) {
            this.minX = minX;
            this.maxX = maxX;
            this.minY = minY;
            this.maxY = maxY;
            this.oriDeltaX = oriDeltaX;
            this.oriDeltaY = oriDeltaY;
            bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas1 = new Canvas(bitmap);
            canvas1.translate(getAbsoluteOriginX(), getAbsoluteOriginY());
            drawAchsen(canvas1, p);
            drawGraphen(canvas1, p);
        }

        private void createNeigbours() {
            //topRight
            createNeibour(minX + dX,maxX + dX,minY + dY,maxY + dY, oriDeltaX -getWidth(), oriDeltaY +getHeight());
            //right
            createNeibour(minX + dX,maxX + dX, minY, maxY,oriDeltaX -getWidth(), oriDeltaY);
            //topLeft
            createNeibour(minX - dX,maxX - dX,minY + dY,maxY + dY,oriDeltaX +getWidth(),oriDeltaY +getHeight());
            //left
            createNeibour(minX - dX,maxX - dX, minY, maxY,oriDeltaX +getWidth(), oriDeltaY);
            //bottomLeft
            createNeibour(minX - dX,maxX - dX,minY - dY, maxY -dY, oriDeltaX +getWidth(), oriDeltaY -getHeight());
            //top
            createNeibour(minX, maxX,minY + dY,maxY + dY, oriDeltaX, oriDeltaY +getHeight());
            //bottomRight
            createNeibour(minX + dX,maxX + dX, minY - dY,maxY - dY,oriDeltaX -getWidth(),oriDeltaY -getHeight());
            //bottom
            createNeibour(minX, maxX,minY - dY,maxY - dY, oriDeltaX, oriDeltaY -getHeight());

            //Entferne alle nicht benötigten Segmente
            segments.clear();
            segments.add(this);
            segments.addAll(newSegmentsBuffer);
            newSegmentsBuffer.clear();
        }

        private void createNeibour(int minX, int maxX, int minY, int maxY, int neighOriDeltaX, int neighOriDeltaY) {
            // Neues Segment wird nur erzeugt wenn es nicht existiert
            boolean exists = false;
            for (Segment seg : segments) {
                if (seg.oriDeltaX == neighOriDeltaX && seg.oriDeltaY == neighOriDeltaY) {
                    exists = true;
                    newSegmentsBuffer.add(seg);
                    break;
                }
            }
            if (!exists)
                newSegmentsBuffer.add(new Segment(minX, maxX, minY, maxY, neighOriDeltaX, neighOriDeltaY));
        }

        // Zeichnet die Funktionen
        private void drawGraphen(Canvas canvas, Paint p) {
            int[] colors = getResources().getIntArray(R.array.functions_color);
            for (int i = 0; i < functions.length; i++) {
                p.setColor(colors[i % colors.length]);
                try {
                    float previousFunctionValue = cmToPxHeight * -functions[i].y(Float.toString(minX/10)).floatValue();
                    for (float xCm = minX +1; xCm <= maxX; xCm += 1) {
                        float divXCm = xCm / 10;
                        canvas.drawLine((xCm - 1) / 10 * cmToPxWidth, previousFunctionValue, divXCm * cmToPxWidth,
                                previousFunctionValue = cmToPxHeight * -functions[i].y(divXCm < 0 ? "(" + Evaluator.NEG + (-divXCm) + ")" : Float.toString(divXCm)).floatValue(), p);
                        Log.d("GTR1", "x " + xCm + "  " + previousFunctionValue + "");
                    }
                } catch (SyntaxException e) {
                    Log.e("GTR", e.getMessage());
                }
            }
        }
        // Zeichnet die Achsen
        private void drawAchsen(Canvas canvas, Paint p) {
            canvas.drawColor(Color.WHITE);

            p.setColor(Color.BLACK);

            int halfWidth = getWidth()/2, halfHeigth = getHeight()/2;
            // Achsen werden gezeichnet
            if (getAbsoluteOriginY() <= halfHeigth && getAbsoluteOriginY() >= -halfHeigth)
                canvas.drawLine(-originX - oriDeltaX, 0, originX - oriDeltaX, 0, p);
            if (getAbsoluteOriginX() <= halfWidth && getAbsoluteOriginX() >= -halfWidth)
                canvas.drawLine(0, -originY - oriDeltaY, 0, originY - oriDeltaY, p);

            Rect bounds = new Rect();
            for (int xPoint = minX; xPoint <= maxX; xPoint += 10)
                if (xPoint != 0)
                    drawXPoint(canvas, Integer.toString(xPoint/10), bounds, xPoint / 10 * cmToPxWidth);

            for (int yPoint = minY; yPoint <= maxY; yPoint++)
                if (yPoint != 0)
                    drawYPoint(canvas, Integer.toString(yPoint), bounds, -yPoint * cmToPxWidth);
        }

        private void drawYPoint(Canvas canvas, String text, Rect bounds, float yInCm) {
            p.getTextBounds(text, 0, text.length(), bounds);
            canvas.drawLine(0, yInCm, dp(3), yInCm, p);
            canvas.drawText(text, bounds.width() + dp(2), yInCm + bounds.height()/2, p);
        }

        private void drawXPoint(Canvas canvas, String text, Rect bounds, float xInCm) {
            p.getTextBounds(text, 0, text.length(), bounds);
            canvas.drawLine(xInCm, 0, xInCm, dp(3), p);
            canvas.drawText(text, xInCm - bounds.width()/2,bounds.height() + dp(5), p);
        }

        float calcHypo() {
            float deltaX = x - oriDeltaX;
            float deltaY = y - oriDeltaY;
            return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        }
        @Override
        public int hashCode() {
            return bitmap.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Segment) return bitmap.equals(((Segment) obj).bitmap);
            return false;
        }

        private int getAbsoluteOriginX() {
            return originX + oriDeltaX;
        }

        private int getAbsoluteOriginY() {
            return originY + oriDeltaY;
        }
    }
}