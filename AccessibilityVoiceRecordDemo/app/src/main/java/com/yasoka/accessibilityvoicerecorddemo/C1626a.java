package com.yasoka.accessibilityvoicerecorddemo;

import android.animation.TimeAnimator;
import android.animation.TimeAnimator.TimeListener;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;

/* renamed from: com.catalinagroup.callrecorder.i.d.a */
public class C1626a extends Drawable {

    /* renamed from: a */
    private final Paint f5026a = new Paint(0);

    /* renamed from: b */
    private final Paint f5027b = new Paint(0);

    /* renamed from: c */
    private final Xfermode f5028c = new PorterDuffXfermode(Mode.DST_IN);

    /* renamed from: d */
    private final TimeAnimator f5029d;

    /* renamed from: e */
    private final int[] f5030e;

    /* renamed from: f */
    private final Drawable f5031f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public final C1628b f5032g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public long f5033h = 0;

    /* renamed from: i */
    private float f5034i = 0.0f;

    /* renamed from: j */
    private Bitmap f5035j;

    /* renamed from: k */
    private Paint f5036k;

    /* renamed from: l */
    private Canvas f5037l;

    /* renamed from: m */
    private Bitmap f5038m;

    /* renamed from: n */
    private Bitmap f5039n;

    /* renamed from: o */
    private Canvas f5040o;

    /* renamed from: p */
    private Matrix f5041p = new Matrix();

    /* renamed from: com.catalinagroup.callrecorder.i.d.a$a */
    class C1627a implements TimeListener {
        C1627a() {
        }

        public void onTimeUpdate(TimeAnimator timeAnimator, long j, long j2) {
            if (j - C1626a.this.f5033h > 50) {
                C1626a.this.f5033h = j;
                C1626a.this.m7660e();
                C1626a.this.f5032g.mo6710a();
            }
        }
    }

    /* renamed from: com.catalinagroup.callrecorder.i.d.a$b */
    public interface C1628b {
        /* renamed from: a */
        void mo6710a();
    }

    public C1626a(int[] iArr, Drawable drawable, C1628b bVar) {
        this.f5031f = drawable;
        this.f5032g = bVar;
        this.f5029d = new TimeAnimator();
        this.f5029d.setTimeListener(new C1627a());
        this.f5030e = iArr;
    }

    /* renamed from: d */
    private int m7659d() {
        Bitmap bitmap = this.f5035j;
        if (bitmap == null) {
            return 0;
        }
        return bitmap.getWidth();
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public void m7660e() {
        if (this.f5035j != null) {
            float f = ((float) this.f5033h) / 3000.0f;
            this.f5041p.setTranslate(((float) this.f5037l.getWidth()) * f, f * ((float) this.f5037l.getHeight()));
            this.f5041p.postRotate(this.f5034i);
            this.f5036k.getShader().setLocalMatrix(this.f5041p);
            this.f5037l.drawRect(0.0f, 0.0f, (float) this.f5035j.getWidth(), (float) this.f5035j.getHeight(), this.f5036k);
            if (this.f5040o != null) {
                this.f5026a.setXfermode(this.f5028c);
                this.f5040o.drawBitmap(this.f5035j, 0.0f, 0.0f, null);
                this.f5040o.drawBitmap(this.f5038m, 0.0f, 0.0f, this.f5026a);
                this.f5026a.setXfermode(null);
            }
        }
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();
        if (width > 0 && height > 0) {
            if (!(width == m7659d() && height == m7657c())) {
                this.f5035j = Bitmap.createBitmap(width, height, Config.ARGB_8888);
                this.f5037l = new Canvas(this.f5035j);
                LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, (float) ((int) Math.sqrt((double) ((this.f5035j.getWidth() * this.f5035j.getWidth()) + (this.f5035j.getHeight() * this.f5035j.getHeight())))), 0.0f, this.f5030e, null, TileMode.MIRROR);
                this.f5036k = new Paint(0);
                this.f5036k.setShader(linearGradient);
                if (this.f5031f != null) {
                    this.f5038m = Bitmap.createBitmap(width, height, Config.ARGB_8888);
                    this.f5031f.setBounds(0, 0, width, height);
                    Canvas canvas2 = new Canvas(this.f5038m);
                    this.f5031f.setBounds(0, 0, width, height);
                    this.f5031f.draw(canvas2);
                    this.f5039n = Bitmap.createBitmap(width, height, Config.ARGB_8888);
                    this.f5040o = new Canvas(this.f5039n);
                }
                m7660e();
            }
            Bitmap bitmap = this.f5039n;
            if (bitmap == null) {
                bitmap = this.f5035j;
            }
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.f5027b);
        }
    }

    public int getOpacity() {
        return -1;
    }

    public void setAlpha(int i) {
        this.f5027b.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f5027b.setColorFilter(colorFilter);
    }

    /* renamed from: c */
    private int m7657c() {
        Bitmap bitmap = this.f5035j;
        if (bitmap == null) {
            return 0;
        }
        return bitmap.getHeight();
    }

    /* renamed from: b */
    public void mo6704b() {
        this.f5029d.start();
    }

    /* renamed from: a */
    public void mo6703a(float f) {
        this.f5034i = f;
    }

    /* renamed from: a */
    public void mo6702a() {
        this.f5029d.cancel();
    }
}
