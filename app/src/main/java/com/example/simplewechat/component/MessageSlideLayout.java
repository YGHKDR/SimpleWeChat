package com.example.simplewechat.component;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

import com.example.simplewechat.R;

public class MessageSlideLayout extends FrameLayout {
    private View contentView;
    private View menuView;

    private int viewHeight;
    private int contentWidth;
    private int menuWidth;

    //滑动器
    private Scroller scroller;

    public MessageSlideLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        scroller=new Scroller(context);
    }

    //布局文件加载完成时调用
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView=findViewById(R.id.content);
        menuView=findViewById(R.id.menu);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        viewHeight=getMeasuredHeight();
        contentWidth=contentView.getMeasuredWidth();
        menuWidth=menuView.getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        menuView.layout(contentWidth,0,contentWidth+menuWidth,viewHeight);

    }

    private float startX;
    private float startY;
    private float downX;
    private float downY;
    private float endX;
    private float endY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                // 记录最初的x坐标
                startX = event.getX();
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                endX = event.getX();
                endY = event.getY();

                // dx为横向发生的完整距离
                float dx = Math.abs(endX - downX);
                if (dx > 0 ) { // 只要发生横向滑动，纵向禁止滑动
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                // 滑动距离
                float dis = endX-startX;
                Log.e("onTouchEvent:", String.valueOf(dis));
                // 向左滑为负值，通过下面一行代码转换至滑至的x坐标
                dis = getScrollX()-dis;
                if (dis < 0){ // 坐标最小为0
                    dis = 0;
                }else if (dis > menuWidth){ // 最多向左滑menuWidth距离
                    dis = menuWidth;
                }
                scrollTo((int) dis,getScrollY());

                // 更新开始x坐标
                startX = event.getX();
                break;
            case MotionEvent.ACTION_UP: // 如果滑出距离小于菜单宽度一半，菜单自动关闭
                int totalX = getScrollX(); // 视图显示部分的左边缘
                if (totalX < menuWidth/2){
                    closeMenu();
                }else {
                    openMenu();
                }
                break;
        }
        return true;
    }

    /**
     * 打开menu菜单
     */
    public void openMenu() {
        int dx = menuWidth-getScrollX();
        scroller.startScroll(getScrollX(), getScrollY(),dx, getScrollY());
        invalidate();
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        //0表示menu移动到的目标距离,目标位置-起始位置
        int dx = 0-getScrollX();
        scroller.startScroll(getScrollX(), getScrollY(),dx, getScrollY());
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset())
        {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }

    /**
     * 监听菜单打开关闭
     */
    public interface OnStateChangeListener{
        void onClose(MessageSlideLayout layout);
        void onDown(MessageSlideLayout layout);
        void onOpen(MessageSlideLayout layout);
    }

    private OnStateChangeListener onStateChangeListener;

    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.onStateChangeListener = onStateChangeListener;
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean isIntercept = false; // 最初为不拦截
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = startX = event.getX();
                try {
                    if (onStateChangeListener == null){
                        throw new Exception("状态改变监听者未设置");
                    }
                    onStateChangeListener.onDown(MessageSlideLayout.this); // 关闭其他item
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                endX = event.getX();
                float distance = Math.abs(endX - startX);
                if (distance > 0){ // 横向发生滑动，拦截事件
                    isIntercept = true;
                }
                break;
        }
        return isIntercept;
    }

}
