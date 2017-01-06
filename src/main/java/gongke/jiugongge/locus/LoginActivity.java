package gongke.jiugongge.locus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import gongke.jiugongge.locus.LocusPassView.OnCompleteListener;

import gongke.jiugongge.R;

public class LoginActivity extends Activity {
	private LocusPassView lpwv;
	private Toast toast;
	private TextView toastTv;
	private void showToast(CharSequence message) {
		if (null == toast) {
			toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
			// toast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			toast.setText(message);
		}

		toast.show();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		lpwv = (LocusPassView) this.findViewById(R.id.mLocusPassWordView);
		lpwv.setOnCompleteListener(new OnCompleteListener() {
			@Override
			public void onComplete(String mPassword) {
				if (lpwv.isPasswordEmpty()){
					lpwv.resetPassWord(mPassword);
					showToast("密码已成功纪录!");
					toastTv.setText("请输入手势密码进行解锁");
					lpwv.clearPassword(); //清除绘制曲线
				}
				else if (lpwv.verifyPassword(mPassword)) {
					showToast("解锁成功！");
					toastTv.setText("已解锁");
					lpwv.clearPassword(); //清除绘制曲线
					lpwv.disableTouch();
				} else {
					showToast("密码输入错误,请重新输入");
					lpwv.clearPassword(); //清除绘制曲线
				}
			}
		});

	}

	@Override
	protected void onStart() {
		super.onStart();
		// 如果密码为空,则设置密码
		toastTv = (TextView) findViewById(R.id.login_toast);
		if (lpwv.isPasswordEmpty()) {
			toastTv.setText("请先绘制手势密码");
		} else {
			toastTv.setText("请输入手势密码");

		}
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

}
