package com.ldkj.yaodian.customer.wxapi;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ldkj.yaodian.customer.Constants;
import com.ldkj.yaodian.customer.R;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler{
	
private static final String TAG = "WXPayEntryActivity";
	
    private IWXAPI api;
    
    private int error_code = 10000;
    
    private TextView tv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        tv = (TextView) findViewById(R.id.tv_wxpay_result);
    	api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);

        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			this.error_code = resp.errCode;
			if(error_code == 0){
				tv.setText("支付结果：成功");
			}else if(error_code == -2){
				tv.setText("支付结果：取消操作");
			}else{
				tv.setText("支付结果：失败");
			}
		}
	}
	
	public void onSubmit(View v){
		finish();
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		Intent intent  = new Intent();
		if(error_code == 0){

		}else if(error_code == -1){

		}else{

		}
		sendBroadcast(intent);
	}
}