package com.example.deom;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	private TextView maintitle;
	private Button querycode_btn;
	private EditText edittext_code;
	private RelativeLayout title_input, re_r, whole_ll, title_input_2;
	private int width;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		whole_ll = (RelativeLayout) findViewById(R.id.whole_ll);
		edittext_code = (EditText) findViewById(R.id.input_code);
		querycode_btn = (Button) findViewById(R.id.querycode_btn);
		title_input = (RelativeLayout) findViewById(R.id.title_input);
		title_input_2 = (RelativeLayout) findViewById(R.id.title_input_2);
		maintitle = (TextView) findViewById(R.id.maintitle);
		re_r = (RelativeLayout) findViewById(R.id.re_r);

		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		maintitle.measure(w, h);
		width = maintitle.getMeasuredWidth();

		whole_ll.setOnTouchListener(new whole_llOnTouchListener());

		edittext_code.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				EditAnimation(arg1);
			}
		});
	}

	protected void EditAnimation(boolean arg1) {
		if (arg1) { // 此处为得到焦点时的处理内容
			new Thread(new Runnable() {

				@Override
				public void run() {
					InputGone();

				}
			}).run();

		} else {

			new Thread(new Runnable() {

				@Override
				public void run() {
					InputShow();

				}
			}).run();

		}

	}

	private void InputShow() {

		int moveWidth = width + 20;
		Animation animation = new TranslateAnimation(0, moveWidth, 0, 0);
		animation.setDuration(200);
		animation.setRepeatCount(0);
		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {

			}

			@Override
			public void onAnimationRepeat(Animation arg0) {

			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				querycode_btn.setVisibility(View.GONE);
				title_input_2.clearAnimation();
				maintitle.setVisibility(View.VISIBLE);
			}
		});

		title_input_2.startAnimation(animation);

	}

	protected void InputGone() {
		int moveWidth = width + 20;
		Animation animation = new TranslateAnimation(0, -moveWidth, 0, 0);
		animation.setDuration(200);
		animation.setRepeatCount(0);
		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {

			}

			@Override
			public void onAnimationRepeat(Animation arg0) {

			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				maintitle.setVisibility(View.GONE);
				title_input.clearAnimation();
				querycode_btn.setVisibility(View.VISIBLE);
			}
		});

		title_input.startAnimation(animation);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		beforeMove();
	}
	
	private void beforeMove() {
		querycode_btn.setVisibility(View.GONE);
		maintitle.setVisibility(View.VISIBLE);
		
	}

	private class whole_llOnTouchListener implements OnTouchListener {

		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			re_r.setFocusable(true);
			re_r.setFocusableInTouchMode(true);
			re_r.requestFocus();
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);    
	        imm.hideSoftInputFromWindow(edittext_code.getWindowToken(), 0) ;  
			return false;
		}
	}

}
