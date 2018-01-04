package com.example.arunfiddler.myapplication;

/**
 * Created by Arun Fiddler on 1/2/2018.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class TicketView extends LinearLayout {
    private Bitmap bm;
    private Canvas cv;
    private Paint eraser;
    private int holeRadius = 40;
    private int holesBottomMargin = 70;

    public TicketView(Context context) {
        super(context);
        Init();
    }

    public TicketView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init();
    }

    public TicketView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init();
    }

    private void Init() {
        this.eraser = new Paint();
        this.eraser.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        this.eraser.setAntiAlias(true);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (!(w == oldw && h == oldh)) {
            this.bm = Bitmap.createBitmap(w, h, Config.ARGB_8888);
            this.cv = new Canvas(this.bm);
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }

    protected void onDraw(Canvas canvas) {
        int w = getWidth();
        int h = getHeight();
        this.bm.eraseColor(0);
        this.cv.drawColor(-1);
        Paint paint = new Paint();
        paint.setARGB(255, 250, 250, 250);
        paint.setStrokeWidth(0.0f);
        paint.setStyle(Style.FILL);
        this.cv.drawRect(0.0f, (float) h, (float) w, ((float) h) - pxFromDp(getContext(), (float) this.holesBottomMargin), paint);
        this.cv.drawCircle(0.0f, 0.0f, (float) this.holeRadius, this.eraser);
        this.cv.drawCircle((float) (w / 2), 0.0f, (float) this.holeRadius, this.eraser);
        this.cv.drawCircle((float) w, 0.0f, (float) this.holeRadius, this.eraser);
        this.cv.drawCircle(0.0f, ((float) h) - pxFromDp(getContext(), (float) this.holesBottomMargin), (float) this.holeRadius, this.eraser);
        this.cv.drawCircle((float) w, ((float) h) - pxFromDp(getContext(), (float) this.holesBottomMargin), (float) this.holeRadius, this.eraser);
        canvas.drawBitmap(this.bm, 0.0f, 0.0f, null);
        Path mPath = new Path();
        mPath.moveTo((float) this.holeRadius, ((float) h) - pxFromDp(getContext(), (float) this.holesBottomMargin));
        mPath.quadTo((float) (w - this.holeRadius), ((float) h) - pxFromDp(getContext(), (float) this.holesBottomMargin), (float) (w - this.holeRadius), ((float) h) - pxFromDp(getContext(), (float) this.holesBottomMargin));
        Paint dashed = new Paint();
        dashed.setARGB(255, 200, 200, 200);
        dashed.setStyle(Style.STROKE);
        dashed.setStrokeWidth(2.0f);
        dashed.setPathEffect(new DashPathEffect(new float[]{10.0f, 5.0f}, 0.0f));
        canvas.drawPath(mPath, dashed);
        super.onDraw(canvas);
    }

    public static float pxFromDp(Context context, float dp) {
        return context.getResources().getDisplayMetrics().density * dp;
    }
}
