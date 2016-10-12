//package com.example.icogn.mshb.utils;
//
//import android.content.res.ColorStateList;
//import android.graphics.Bitmap;
//import android.graphics.BitmapShader;
//import android.graphics.Canvas;
//import android.graphics.ColorFilter;
//import android.graphics.Matrix;
//import android.graphics.Paint;
//import android.graphics.PixelFormat;
//import android.graphics.Rect;
//import android.graphics.Shader;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.ColorDrawable;
//import android.graphics.drawable.Drawable;
//import android.graphics.drawable.GradientDrawable;
//
///**
// * Created by ICOGN on 2016/9/20.
// */
//
//public class CircleDrawable extends Drawable {
//    protected final Paint paint;
//
//    protected final int          margin;
//    protected final BitmapShader bitmapShader;
//    protected       float        radius;
//    protected       Bitmap       oBitmap;//原图
//
//    public CircleDrawable(Bitmap bitmap) {
//        this(bitmap, 0);
//    }
//
//    public CircleDrawable(Bitmap bitmap, int margin) {
//        this.margin = margin;
//        this.oBitmap = bitmap;
//        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        paint = new Paint();
//        paint.setAntiAlias(true);
//        paint.setShader(bitmapShader);
//    }
//
//    @Override
//    protected void onBoundsChange(Rect bounds) {
//        super.onBoundsChange(bounds);
//        computeBitmapShaderSize();
//        computeRadius();
//    }
//
//
//    /**
//     * Draw in its bounds (set via setBounds) respecting optional effects such
//     * as alpha (set via setAlpha) and color filter (set via setColorFilter).
//     *
//     * @param canvas The canvas to draw into
//     */
//    @Override
//    public void draw(Canvas canvas) {
//        Rect bounds = getBounds();//画一个圆圈
//        canvas.drawCircle(bounds.width() / 2F, bounds.height() / 2F, radius, paint);
//    }
//
//    /**
//     * Specify an alpha value for the drawable. 0 means fully transparent, and
//     * 255 means fully opaque.
//     *
//     * @param alpha
//     */
//    @Override
//    public void setAlpha(int alpha) {
//        paint.setAlpha(alpha);
//    }
//
//    /**
//     * Specify an optional color filter for the drawable.
//     * <p>
//     * If a Drawable has a ColorFilter, each output pixel of the Drawable's
//     * drawing contents will be modified by the color filter before it is
//     * blended onto the render target of a Canvas.
//     * </p>
//     * <p>
//     * Pass {@code null} to remove any existing color filter.
//     * </p>
//     * <p class="note"><strong>Note:</strong> Setting a non-{@code null} color
//     * filter disables {@link #setTintList(ColorStateList) tint}.
//     * </p>
//     *
//     * @param colorFilter The color filter to apply, or {@code null} to remove the
//     *                    existing color filter
//     */
//    @Override
//    public void setColorFilter(ColorFilter colorFilter) {
//        paint.setColorFilter(colorFilter);
//    }
//
//    /**
//     * Return the opacity/transparency of this Drawable.  The returned value is
//     * one of the abstract format constants in
//     * {@link PixelFormat}:
//     * {@link PixelFormat#UNKNOWN},
//     * {@link PixelFormat#TRANSLUCENT},
//     * {@link PixelFormat#TRANSPARENT}, or
//     * {@link PixelFormat#OPAQUE}.
//     * <p>
//     * <p>An OPAQUE drawable is one that draws all all content within its bounds, completely
//     * covering anything behind the drawable. A TRANSPARENT drawable is one that draws nothing
//     * within its bounds, allowing everything behind it to show through. A TRANSLUCENT drawable
//     * is a drawable in any other state, where the drawable will draw some, but not all,
//     * of the content within its bounds and at least some content behind the drawable will
//     * be visible. If the visibility of the drawable's contents cannot be determined, the
//     * safest/best return value is TRANSLUCENT.
//     * <p>
//     * <p>Generally a Drawable should be as conservative as possible with the
//     * value it returns.  For example, if it contains multiple child drawables
//     * and only shows one of them at a time, if only one of the children is
//     * TRANSLUCENT and the others are OPAQUE then TRANSLUCENT should be
//     * returned.  You can use the method {@link #resolveOpacity} to perform a
//     * standard reduction of two opacities to the appropriate single output.
//     * <p>
//     * <p>Note that the returned value does not necessarily take into account a
//     * custom alpha or color filter that has been applied by the client through
//     * the {@link #setAlpha} or {@link #setColorFilter} methods. Some subclasses,
//     * such as {@link BitmapDrawable}, {@link ColorDrawable}, and {@link GradientDrawable},
//     * do account for the value of {@link #setAlpha}, but the general behavior is dependent
//     * upon the implementation of the subclass.
//     *
//     * @return int The opacity class of the Drawable.
//     * @see PixelFormat
//     */
//    @Override
//    public int getOpacity() {
//        return PixelFormat.TRANSLUCENT;
//    }
//
//    /**
//     * 计算Bitmap shader 大小
//     */
//    public void computeBitmapShaderSize() {
//        Rect bounds = getBounds();
//        if (bounds == null) return;
//        //选择缩放比较多的缩放，这样图片就不会有图片拉伸失衡
//        Matrix matrix = new Matrix();
//        float  scaleX = bounds.width() / (float) oBitmap.getWidth();
//        float  scaleY = bounds.height() / (float) oBitmap.getHeight();
//        float  scale  = scaleX > scaleY ? scaleX : scaleY;
//        matrix.postScale(scale, scale);
//        bitmapShader.setLocalMatrix(matrix);
//    }
//
//    /**
//     * 计算半径的大小
//     */
//    public void computeRadius() {
//        Rect bounds = getBounds();
//        radius = bounds.width() < bounds.height() ?
//                bounds.width() / 2F - margin :
//                bounds.height() / 2F - margin;
//    }
//
//}
