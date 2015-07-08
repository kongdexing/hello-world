package mapchat.demowidget.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import mapchat.demowidget.R;

/**
 * Created by kongdexing on 7/3/15.
 */
public class GlassIncomingWaveView extends LinearLayout {

    private static final int ANIMATIONEACHOFFSET = 500; // 每个动画的播放时间间隔
    private int viewCount = 7;
    private ImageView[] acceptViews = new ImageView[viewCount];
    private ImageView[] terminateViews = new ImageView[viewCount];
    private AnimationSet[] acceptAni = new AnimationSet[viewCount];
    private AnimationSet[] terminateAni = new AnimationSet[viewCount];

    public GlassIncomingWaveView(Context context) {
        this(context, null);
    }

    public GlassIncomingWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.incoming_waveview, this);

        RelativeLayout rl_wave_parent = (RelativeLayout) view.findViewById(R.id.rl_wave_parent);

        for (int i = 0; i < viewCount; i++) {
            acceptViews[i] = new ImageView(context);
            acceptViews[i].setBackgroundResource(R.mipmap.incoming_green_wave);
            rl_wave_parent.addView(acceptViews[i]);
            acceptAni[i] = getWaveAnimationSet();

            terminateViews[i] = new ImageView(context);
            terminateViews[i].setBackgroundResource(R.mipmap.incoming_red_wave);
            rl_wave_parent.addView(terminateViews[i]);
            terminateAni[i] = getTerminateAnimationSet();
        }

        showWaveAnimation();
    }

    private AnimationSet getWaveAnimationSet() {
        AnimationSet as = new AnimationSet(true);
        ScaleAnimation sa = new ScaleAnimation(0.5f, 10f, 0.5f, 5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(ANIMATIONEACHOFFSET * viewCount);
        sa.setRepeatCount(-1);// 设置循环
        AlphaAnimation aniAlp = new AlphaAnimation(1, 0.1f);
        aniAlp.setRepeatCount(-1);// 设置循环
        as.setDuration(ANIMATIONEACHOFFSET * viewCount);
        as.addAnimation(sa);
        as.addAnimation(aniAlp);
        return as;
    }

    private AnimationSet getTerminateAnimationSet() {
        AnimationSet as = new AnimationSet(true);
        ScaleAnimation sa = new ScaleAnimation(0.5f, 5f, 0.5f, 5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(ANIMATIONEACHOFFSET * viewCount);
        sa.setRepeatCount(-1);// 设置循环
        AlphaAnimation aniAlp = new AlphaAnimation(1, 0.1f);
        aniAlp.setRepeatCount(-1);// 设置循环
        as.setDuration(ANIMATIONEACHOFFSET * viewCount);
        as.addAnimation(sa);
        as.addAnimation(aniAlp);
        return as;
    }

    public void showWaveAnimation() {
        acceptViews[0].startAnimation(acceptAni[0]);
        terminateViews[0].startAnimation(terminateAni[0]);

        for (int i = 1; i < viewCount; i++) {
            handler.sendEmptyMessageDelayed(i, ANIMATIONEACHOFFSET * i);
        }
    }

    public void cancalWaveAnimation() {
        for (int i = 0; i < viewCount; i++) {
            acceptViews[i].clearAnimation();
            terminateViews[i].clearAnimation();
        }
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            int id = msg.what;
            acceptViews[id].startAnimation(acceptAni[id]);
            terminateViews[id].startAnimation(terminateAni[id]);
            super.handleMessage(msg);
        }

    };


    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        Log.i("screen","onWindowVisibilityChanged "+visibility);
        switch (visibility) {
            case View.INVISIBLE: // invisible but still takes up space for layout purposes.
            case View.GONE:      // invisible and it doesn't take any space for layout
//                handler.removeCallbacksAndMessages(null);
                break;

            default:
                break;
        }

        super.onWindowVisibilityChanged(visibility);
    }
}
