package com.yarris.cordova.consol.watermarking;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import com.vinaygaba.rubberstamp.PositionCalculator;
import com.vinaygaba.rubberstamp.RubberStamp;
import com.vinaygaba.rubberstamp.RubberStampConfig;
import com.vinaygaba.rubberstamp.RubberStampPosition;

import static com.vinaygaba.rubberstamp.RubberStampPosition.CUSTOM;
import static com.vinaygaba.rubberstamp.RubberStampPosition.TILE;

/**
 * Created by gota on 8/03/18.
 */

public class Watermarker {
    private static final String LOG_TAG = "Watermarker";
    final Photo photo;
    final Rect header;
    final Rect footer;
    final WatermarkLines lines;
    final Context context;
    final WatermarkConfig config;
    private Bitmap marking;
    public static final int BACKGROUND_MARGIN = 10;
    private Canvas canvas;

    public Watermarker(Context context, Photo photo, WatermarkLines lines) {
        Log.d(LOG_TAG, "photo: " + photo);
        this.photo = photo;

        marking = photo.src;

        header = photo.header();
        footer = photo.footer();

        Log.d(LOG_TAG, "header: " + header);
        Log.d(LOG_TAG, "footer: " + footer);

        config = new WatermarkConfig(photo);

        this.context = context;
        this.lines = lines;
    }

    Photo mark() {
        return new Photo(
                markHeaderFooter(marking)
                //top left
                .mark(marking, lines.id, RubberStampPosition.TOP_LEFT, config.topLeftLine1)
                .mark(marking, lines.address, RubberStampPosition.TOP_LEFT, config.topLeftLine2)
                .mark(marking, lines.name, RubberStampPosition.TOP_LEFT, config.topLeftLine3)
                //top right
                .mark(marking, lines.name, RubberStampPosition.TOP_RIGHT, config.topRightLine1)
                //bottom left
                .mark(marking, lines.lat, RubberStampPosition.BOTTOM_LEFT, config.bottomLeftLine2)
                .mark(marking, lines.lng, RubberStampPosition.BOTTOM_LEFT, config.bottomLeftLine1)
                //bottom right
                .mark(marking, lines.date, RubberStampPosition.BOTTOM_RIGHT, config.bottomRightLine2)
                .mark(marking, lines.time, RubberStampPosition.BOTTOM_RIGHT, config.bottomRightLine1)
                .marking, photo.exif);
    }

    private Watermarker mark(Bitmap base, String watermark, RubberStampPosition position, Margin margin) {
        Log.d(LOG_TAG, "mark watermark: " + watermark);
        RubberStampConfig config = new RubberStampConfig.RubberStampConfigBuilder()
                .base(base)
                .rubberStamp(watermark)
                .rubberStampPosition(position)
                .alpha(255)
                .margin(margin.x, margin.y)
                .textColor(Color.WHITE)
                .textSize(this.config.textSize)
                .build();
        //RubberStamp rubberStamp = new RubberStamp(context);
        addTextToBitmap(config, base.getWidth(), base.getHeight());
        return this;
    }

    private Watermarker markHeaderFooter(Bitmap src) {
        Log.d(LOG_TAG, "markHeaderFooter: " + src );
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap result = Bitmap.createBitmap(w, h, src.getConfig());
        canvas = new Canvas(result);
        canvas.drawBitmap(src, 0, 0, null);
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setAlpha(120);
        canvas.drawRect(header, paint);
        canvas.drawRect(footer, paint);

        return updateMarking(result);
    }


    private void addTextToBitmap(RubberStampConfig config, int baseBitmapWidth, int baseBitmapHeight) {
        Rect bounds = new Rect();

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setUnderlineText(false);
        paint.setTextSize(config.getTextSize());

        String typeFacePath = config.getTypeFacePath();
        if(!TextUtils.isEmpty(typeFacePath)) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), typeFacePath);
            paint.setTypeface(typeface);
        }

//        Shader shader = config.getTextShader();
//        if (shader != null) {
//            paint.setShader(shader);
//        }

//        if (config.getTextShadowXOffset() != 0 || config.getTextShadowYOffset() != 0
//                || config.getTextShadowBlurRadius() != 0) {
//            paint.setShadowLayer(config.getTextShadowBlurRadius(),
//                    config.getTextShadowXOffset(),
//                    config.getTextShadowYOffset(),
//                    config.getTextShadowColor());
//        }

        String rubberStampString = config.getRubberStampString();
        paint.getTextBounds(rubberStampString,0,rubberStampString.length(),bounds);

        int rubberStampWidth = bounds.width();
        float rubberStampMeasuredWidth = paint.measureText(rubberStampString);
        int rubberStampHeight = bounds.height();

        int positionX = config.getPositionX();
        int positionY = config.getPositionY();

//        if (config.getRubberStampPosition() != CUSTOM) {
            Pair<Integer, Integer> pair = PositionCalculator
                    .getCoordinates(config.getRubberStampPosition(),
                            baseBitmapWidth, baseBitmapHeight,
                            rubberStampWidth, rubberStampHeight);
            positionX = pair.first;
            positionY = pair.second;
//        }

        positionX += config.getXMargin();
        positionY += config.getYMargin();

//        float rotation = config.getRotation();
//        if (rotation != 0.0f) {
//            canvas.rotate(rotation, positionX + bounds.exactCenterX(),
//                    positionY - bounds.exactCenterY());
//        }

        paint.setColor(config.getTextColor());
//        int alpha = config.getAplha();
//        if (alpha >= 0 && alpha <= 255) {
//            paint.setAlpha(alpha);
//        }

        //if (config.getRubberStampPosition() != TILE) {
//        int backgroundColor = config.getTextBackgroundColor();
//        if (backgroundColor != 0) {
//            Paint backgroundPaint = new Paint();
//            backgroundPaint.setColor(backgroundColor);
//            canvas.drawRect(positionX - BACKGROUND_MARGIN,
//                    positionY - bounds.height() - paint.getFontMetrics().descent - BACKGROUND_MARGIN,
//                    (positionX + rubberStampMeasuredWidth + config.getTextShadowXOffset() + BACKGROUND_MARGIN),
//                    positionY + config.getTextShadowYOffset() + paint.getFontMetrics().descent + BACKGROUND_MARGIN,
//                    backgroundPaint);
//        }
        canvas.drawText(rubberStampString, positionX , positionY, paint);
//        } else {
//            // TODO(vinaygaba): Improve this logic. There has to be something more intuitive
//            Bitmap textImage = Bitmap.createBitmap((int)rubberStampMeasuredWidth,
//                    rubberStampHeight,
//                    Bitmap.Config.ARGB_8888);
//            Canvas textCanvas = new Canvas(textImage);
//            textCanvas.drawText(config.getRubberStampString(), 0, rubberStampHeight, paint);
//            paint.setShader(new BitmapShader(textImage,
//                    Shader.TileMode.REPEAT,
//                    Shader.TileMode.REPEAT));
//            Rect bitmapShaderRect = canvas.getClipBounds();
//            canvas.drawRect(bitmapShaderRect, paint);
//        }
    }
    private Watermarker updateMarking(Bitmap toUpdate) {
        marking = toUpdate;
        return this;
    }
}

