package com.green0.customerApp.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.green0.customerApp.RestGreeno.ApiClient;
import com.green0.customerApp.RestGreeno.ApiInterface;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.example.ironman.myapplication.R;
import com.green0.customerApp.model.UserLogin;
import com.green0.customerApp.model.UserProfile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginNew extends AppCompatActivity {


        private static final String TAG = LoginNew.class.getSimpleName();
        private static final int REQUEST_SIGNUP = 0;
        private ProgressDialog progressDialog;
        private ApiInterface ApiService;

        @BindView(R.id.input_email) EditText emailText;
        @BindView(R.id.input_password) EditText passwordText;
        @BindView(R.id.btn_login) Button loginButton;
        @BindView(R.id.link_signup) TextView signupLink;

        public LoginNew() {
        progressDialog = null;
    }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_new);
            ButterKnife.setDebug(true);
            ButterKnife.bind(this);
            progressDialog = new ProgressDialog(LoginNew.this,
                R.style.AppTheme_Dark_Dialog);
            //TODO : auto login using shared preferences


            //TODO : remove these auto settexts
            emailText.setText("spiderman");
            passwordText.setText("maryjanew");

             ApiService = ApiClient.getClient().create(ApiInterface.class);


//            call.enqueue(new Callback<MoviesResponse>() {
//                @Override
//                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
//                    int statusCode = response.code();
//                    List<Movie> movies = response.body().getResults();
//                    recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
//                }
//
//                @Override
//                public void onFailure(Call<MoviesResponse> call, Throwable t) {
//                    // Log error here since request failed
//                    Log.e(TAG, t.toString());
//                }
//            });
        }

        @OnClick(R.id.btn_login)
        public  void onLoginClick(View view){
            login();
        }

        @OnClick(R.id.link_signup   )
        public  void onRegisterClick(View  view){
            // Start the Signup activity
            Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
            startActivityForResult(intent, REQUEST_SIGNUP);
        }

        public void login() {
            Log.d(TAG, "trying to Login");
            //TODO : validate username and password
//            if (!validate()) {
//                onLoginFailed();
//                return;
//            }

            //TODO move this to preexecute
            /***/
            loginButton.setEnabled(false);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();
            /***/

            String username = emailText.getText().toString();
            String password = passwordText.getText().toString();


            UserLogin user = new UserLogin(username,password);

            Call<UserProfile> loginTask = ApiService.AuthenticateUser(user);
            loginTask.enqueue(
                    new Callback<UserProfile>() {
                        @Override
                        public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                            progressDialog.dismiss();
                            if(response.code()==200) {
                                UserProfile userProfile = response.body();
                                Log.d(TAG, userProfile.getUsername());
                                Log.d(TAG, "success login");
                                startActivity(new Intent(LoginNew.this, MainActivity.class));
                                finish();
                            }


                           else if(response.code()==401) {
                                Toast.makeText(getBaseContext(), "wrong username/password", Toast.LENGTH_LONG).show();
                                loginButton.setEnabled(true);
                            }

                            else {
                                Toast.makeText(getBaseContext(), "wrong username/password", Toast.LENGTH_LONG).show();
                                loginButton.setEnabled(true);
                            }
                        }

                        @Override
                        public void onFailure(Call<UserProfile> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
                            loginButton.setEnabled(true);
                        }
                    }
            );

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == REQUEST_SIGNUP) {
                if (resultCode == RESULT_OK) {

                    // TODO: Implement successful signup logic here
                    // By default we just finish the Activity and log them in automatically
                    this.finish();
                }
            }
        }

        @Override
        public void onBackPressed() {
            // disable going back to the MainActivity
            moveTaskToBack(true);
        }

        public void onLoginFailed() {
            Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
            loginButton.setEnabled(true);
        }

        public boolean validate() {
            //TODO : implement validation
            boolean valid = true;

            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();

            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailText.setError("enter a valid email address");
                valid = false;
            } else {
                emailText.setError(null);
            }

            if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
                passwordText.setError("between 4 and 10 alphanumeric characters");
                valid = false;
            } else {
                passwordText.setError(null);
            }

            return valid;
        }

        public boolean isConnected(){
            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected())
                return true;
            else
                return false;
        }

    }






