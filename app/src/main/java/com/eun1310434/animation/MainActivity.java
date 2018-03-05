/**
 * 05.03.2018
 * eun1310434@naver.com
 * https://blog.naver.com/eun1310434
 * 참고) Do it android programming
 */
package com.eun1310434.animation;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    View rootView;

    ImageView tree;
    ImageView leaf;
    ImageView forest;

    Animation shakeAnimation;
    Animation dropAnimation;
    Animation flowAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Swing tree 이미지에 애니메이션 객체 설정
        tree = (ImageView) findViewById(R.id.tree);
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        tree.setAnimation(shakeAnimation);

        // leaf
        leaf = (ImageView) findViewById(R.id.leaf);
        dropAnimation = AnimationUtils.loadAnimation(this, R.anim.drop);
        leaf.setAnimation(dropAnimation);

        // forest
        forest = (ImageView) findViewById(R.id.forest);
        flowAnimation = AnimationUtils.loadAnimation(this, R.anim.flow);
        forest.setAnimation(flowAnimation);


        //Resource를 새롭게 셋
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.forest);
        int bitmapWidth = bitmap.getWidth(); // 이미지 가로 크기 값고오기
        int bitmapHeight = bitmap.getHeight();// 이미지 세로 크기 값고오기

        //레이아웃의 속성 값을 변경할 때에는 반드시 layoutParams()메서드가 필요
        //getLayoutParams()는 현재 레이아웃 요소의 속성객체를 얻어오는 것
        //setLayoutParams()는 해당 속성객체의 값을 변경하는 것
        //두 메서드는 함께 사용해야 함.
        //set메서드만 사용할 경우 해당 객체에 대한 모든 속성을 정의해주지 않으면
        //미지정 속성에 대해 초기화가 진행
        ViewGroup.LayoutParams params = forest.getLayoutParams();// forest의 getLayoutParams 갖고오기
        params.width = bitmapWidth;// forest의 이미지에 맞게 크기 설정
        params.height = bitmapHeight;// forest의 이미지에 맞게 크기 설정

        forest.setImageBitmap(bitmap);

        flowAnimation.setAnimationListener(new AnimationAdapter());
        //에니메이션 리스너 : 애니메이션 시작과 종료시 별도의 설정이 가능
    }

    //화면에 보여지기 전 호출
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        Toast.makeText(getApplicationContext(), "onWindowFocusChanged : " + hasFocus, Toast.LENGTH_SHORT).show();

        if (hasFocus) {
            shakeAnimation.start();
            dropAnimation.start();
            flowAnimation.start();
        } else {
            shakeAnimation.reset();
            dropAnimation.reset();
            flowAnimation.reset();
        }
    }


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Toast.makeText(getApplicationContext(), "attached.", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Toast.makeText(getApplicationContext(), "detached.", Toast.LENGTH_SHORT).show();
    }


    //애니메이션의 시작과 종료 시점을 알기 위한 리스너
    private final class AnimationAdapter implements Animation.AnimationListener {
    int repeatCount = 0;

        public void onAnimationStart(Animation animation) {
            Toast.makeText(getApplicationContext(), "Animation started.", Toast.LENGTH_SHORT).show();
        }

        public void onAnimationEnd(Animation animation) {
            Toast.makeText(getApplicationContext(), "Animation ended.", Toast.LENGTH_SHORT).show();
        }

        public void onAnimationRepeat(Animation animation) {

        }
    }
}
