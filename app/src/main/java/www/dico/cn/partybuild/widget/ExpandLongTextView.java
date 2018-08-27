package www.dico.cn.partybuild.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import www.dico.cn.partybuild.R;

@SuppressLint("AppCompatCustomView")
public class ExpandLongTextView extends TextView {
    private String originText;
    private int initWidth = 0;
    private int mMaxLines = 6;
    private boolean isExpand = true;
    private SpannableString ELLIPSIS = null;

    public ExpandLongTextView(Context context) {
        super(context);
    }

    public ExpandLongTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandLongTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    private void init() {
        String content = "全文";
        ELLIPSIS = new SpannableString(content);
        ButtonSpan span = new ButtonSpan(getContext(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpand) {
                    setMaxLines(Integer.MAX_VALUE);
                    setText(originText);
                }
            }
        }, R.color.cornflowerBlue);
        ELLIPSIS.setSpan(span, 0, content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    @Override
    public void setMaxLines(int maxLines) {
        this.mMaxLines = maxLines;
        super.setMaxLines(maxLines);
    }

    /**
     * 初始化TextView的宽度
     *
     * @param width
     */
    public void initWidth(int width) {
        initWidth = width;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setExpandText(CharSequence text) {
        if (null == ELLIPSIS) {
            init();
        }
        originText = text.toString();
        boolean appendShowAll = false;
        int maxLines = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            maxLines = getMaxLines();
        } else {
            maxLines = mMaxLines;
        }
        String workingText = new StringBuilder(originText).toString();
        if (maxLines != -1) {
            Layout layout = createWorkingLayout(workingText);
            if (layout.getLineCount() > maxLines) {
                //获取一行显示字符个数，然后截取字符串数
                workingText = originText.substring(0, layout.getLineEnd(maxLines - 1)).trim();
                String showText = originText.substring(0, layout.getLineEnd(maxLines - 1)).trim() + "..." + ELLIPSIS;
                Layout layout2 = createWorkingLayout(showText);
                while (layout2.getLineCount() > maxLines) {
                    int lastSpace = workingText.length() - 1;
                    if (lastSpace == -1) {
                        break;
                    }
                    workingText = workingText.substring(0, lastSpace);
                    layout2 = createWorkingLayout(workingText + "..." + ELLIPSIS);
                }
                appendShowAll = true;
                workingText = workingText + "...";
            }
        }
        setText(workingText);
        if (appendShowAll) {
            // 必须使用append，不能在上面使用+连接，否则spannable会无效
            append(ELLIPSIS);
            setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    //返回textview的显示区域的layout，该textview的layout并不会显示出来，只是用其宽度来比较要显示的文字是否过长
    @RequiresApi(api = Build.VERSION_CODES.M)
    private Layout createWorkingLayout(String workingText) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return StaticLayout.Builder.obtain(workingText, 0, workingText.length(), getPaint(), initWidth - getPaddingLeft() - getPaddingRight())
                    .setLineSpacing(getLineSpacingExtra(), getLineSpacingMultiplier())
                    .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                    // 注意，在textview里需要设置这两个参数，否则显示会不正常
                    .setBreakStrategy(Layout.BREAK_STRATEGY_HIGH_QUALITY)
                    .setHyphenationFrequency(Layout.HYPHENATION_FREQUENCY_FULL)
                    .build();
        } else {
            return StaticLayout.Builder.obtain(workingText, 0, workingText.length(), getPaint(), initWidth - getPaddingLeft() - getPaddingRight())
                    .setLineSpacing(getLineSpacingExtra(), 1.0f)
                    .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                    // 注意，在textview里需要设置这两个参数，否则显示会不正常
                    .setBreakStrategy(Layout.BREAK_STRATEGY_HIGH_QUALITY)
                    .setHyphenationFrequency(Layout.HYPHENATION_FREQUENCY_FULL)
                    .build();
        }
    }
}
