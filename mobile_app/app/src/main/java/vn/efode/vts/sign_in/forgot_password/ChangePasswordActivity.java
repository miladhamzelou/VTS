package vn.efode.vts.sign_in.forgot_password;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import vn.efode.vts.R;
import vn.efode.vts.service.ServiceHandler;
import vn.efode.vts.sign_in.SignInActivity;
import vn.efode.vts.utils.ServerCallback;

public class ChangePasswordActivity extends AppCompatActivity {

    private TextView txtWarning;
    private EditText edtNewpass;
    private EditText edtConfirm;
    private Button btnDone;
    private String changePasswordUrl = ServiceHandler.DOMAIN + "/api/v1/user/newPassword";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        txtWarning = (TextView)findViewById(R.id.textview_changepass_warning);
        edtNewpass = (EditText)findViewById(R.id.edittext_changepass_newpass);
        edtConfirm = (EditText)findViewById(R.id.edittext_changepass_confirm);
        btnDone = (Button)findViewById(R.id.button_changepass_done);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("intensss",getIntent().getStringExtra("RESET_PASSWORD_TOKEN"));

                if (edtNewpass.getText().toString().equals(edtConfirm.getText().toString()) && !edtNewpass.equals("")) {
                    final Intent intent = new Intent(ChangePasswordActivity.this, SignInActivity.class);

                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("email", "tuan@gmail.com");
                    params.put("newPassword", edtNewpass.getText().toString());
                    params.put("resetPasswordToken", getIntent().getStringExtra("RESET_PASSWORD_TOKEN"));

                    ServiceHandler serviceHandler = new ServiceHandler();
                    serviceHandler.makeServiceCall(changePasswordUrl, Request.Method.POST,
                            params, new ServerCallback() {
                                @Override
                                public void onSuccess(JSONObject result) {
                                    Log.d("Resultt", result.toString());
                                    Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
                                    try {
                                        Boolean error = gson.fromJson(result.getString("error"), Boolean.class);
                                        if (!error) {

                                            Toast.makeText(ChangePasswordActivity.this, "Đổi thành công", Toast.LENGTH_SHORT).show();
                                            startActivity(intent);
                                        } else {

                                        }


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(VolleyError error) {
                                    Log.d("Resultt", error.getMessage());
                                }

                            });
                }else{
                    txtWarning.setText("Lỗi, vui lòng thử lại");
                    Toast.makeText(ChangePasswordActivity.this,"Lỗi", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
