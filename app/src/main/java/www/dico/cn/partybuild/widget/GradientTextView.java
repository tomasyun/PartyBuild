package www.dico.cn.partybuild.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import www.dico.cn.partybuild.R;


@SuppressLint("AppCompatCustomView")
public class GradientTextView extends TextView {
    private LinearGradient mLinearGradient;
    private Paint mPaint;
    private int mViewWidth = 0;
    private Rect mTextBound = new Rect();
    private int startColor, endColor;

    public GradientTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray arry = context.obtainStyledAttributes(attrs, R.styleable.GradientTextView);
        startColor = arry.getInt(R.styleable.GradientTextView_startColor, ContextCompat.getColor(context, R.color.theme_color));//渐变的第一个颜色，默认主题色
        endColor = arry.getInt(R.styleable.GradientTextView_endColor, ContextCompat.getColor(context, R.color.white));//渐变的第二个颜色，默认白色
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mViewWidth = getMeasuredWidth();
        mPaint = getPaint();
        String mTipText = getText().toString();
        mPaint.getTextBounds(mTipText, 0, mTipText.length(), mTextBound);
        mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                new int[]{startColor, endColor},
                null, Shader.TileMode.REPEAT);
        mPaint.setShader(mLinearGradient);
        canvas.drawText(mTipText, getMeasuredWidth() / 2 - mTextBound.width() / 2, getMeasuredHeight() / 2 + mTextBound.height() / 2, mPaint);
    }
}
