package www.dico.cn.partybuild.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @Class: NumImageView
 * @Description:一些图标的右上角会显示数字，如微信的头像右上角显示信息数量，淘宝的购物车图标右上角显示商品数量
 * @author: yun tuo
 * @Date: 2018\4\28 0028 15:23
 */
@SuppressLint("AppCompatCustomView")
public class NumImageView extends ImageView {
    //要显示的数量数量
    private int num = 0;
    //红色圆圈的半径
    private float radius;
    //圆圈内数字的半径
    private float textSize;
    //右边和上边内边距
    private int paddingRight;
    private int paddingTop;

    public NumImageView(Context context) {
        super(context);
    }

    public NumImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NumImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //设置显示的数量
    public void setNum(int num) {
        this.num = num;
        //重新绘制画布
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (num > 0) {
            //初始化半径
            radius = getWidth() / 3;
            //初始化字体大小
            textSize = num < 10 ? radius + 5 : radius;
            //初始化边距
            paddingRight = getPaddingRight();
            paddingTop = getPaddingTop();
            //初始化画笔
            Paint paint = new Paint();
            //设置抗锯齿
            paint.setAntiAlias(true);
            //设置颜色为红色
            paint.setColor(0xffff4444);
            //设置填充样式为充满
            paint.setStyle(Paint.Style.FILL);
            //画圆
            canvas.drawCircle(getWidth() - radius - paddingRight/2, radius + paddingTop/2, radius, paint);
            //设置颜色为白色
            paint.setColor(0xffffffff);
            //设置字体大小
            paint.setTextSize(textSize);
            //画数字
            canvas.drawText("" + (num < 99 ? num : 99),
                    num < 10 ? getWidth() - radius - textSize / 4 - paddingRight/2
                            : getWidth() - radius - textSize / 2 - paddingRight/2,
                    radius + textSize / 3 + paddingTop/2, paint);
        }
    }
}