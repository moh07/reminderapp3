package com.example.moh.reminderapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.DefaultAudience;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;


public class FacebookLoginActivity extends AppCompatActivity {
    private static final String GRAPH_PATH = "me/permissions";
    private static final String SUCCESS = "success";

    private static final int PICK_PERMS_REQUEST = 0;

    private CallbackManager callbackManager;

    private ProfilePictureView profilePictureView;
    private TextView userNameView;
    private LoginButton fbLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
        callbackManager = CallbackManager.Factory.create();

        fbLoginButton = (LoginButton) findViewById(R.id._fb_login);
        profilePictureView = (ProfilePictureView) findViewById(R.id.user_pic);
        profilePictureView.setCropped(true);

        userNameView = (TextView) findViewById(R.id.user_name);

        final Button deAuthButton = (Button) findViewById(R.id.deauth);
        deAuthButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isLoggedIn()) {
                    Toast.makeText(
                            FacebookLoginActivity.this,
                            R.string.app_not_logged_in,
                            Toast.LENGTH_LONG).show();
                    return;
                }
                GraphRequest.Callback callback = new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        try {
                            if (response.getError() != null) {
                                Toast.makeText(
                                        FacebookLoginActivity.this,
                                        getResources().getString(
                                                R.string.failed_to_deauth,
                                                response.toString()),
                                        Toast.LENGTH_LONG
                                ).show();
                            } else if (response.getJSONObject().getBoolean(SUCCESS)) {
                                LoginManager.getInstance().logOut();
                                // updateUI();?
                            }
                        } catch (JSONException ex) { /* no op */ }
                    }
                };
                GraphRequest request = new GraphRequest(AccessToken.getCurrentAccessToken(),
                        GRAPH_PATH, new Bundle(), HttpMethod.DELETE, callback);
                request.executeAsync();
            }
        });

        final Button permsButton = (Button) findViewById(R.id.perms);
        permsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Intent selectPermsIntent =
                        new Intent(FacebookLoginActivity.this, PermissionSelectActivity.class);
                startActivityForResult(selectPermsIntent, PICK_PERMS_REQUEST);
            }
        });

        // Callback registration
        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                // App code
                Toast.makeText(
                        FacebookLoginActivity.this,
                        R.string.success,
                        Toast.LENGTH_LONG).show();
                updateUI();
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(
                        FacebookLoginActivity.this,
                        R.string.cancel,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(final FacebookException exception) {
                // App code
                Toast.makeText(
                        FacebookLoginActivity.this,
                        R.string.error,
                        Toast.LENGTH_LONG).show();
            }
        });

        new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    final Profile oldProfile,
                    final Profile currentProfile) {
                updateUI();
            }
        };
    }

    private boolean isLoggedIn() {
        AccessToken accesstoken = AccessToken.getCurrentAccessToken();
        return !(accesstoken == null || accesstoken.getPermissions().isEmpty());
    }

    private void updateUI() {
        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            profilePictureView.setProfileId(profile.getId());
            userNameView
                    .setText(String.format("%s %s", profile.getFirstName(), profile.getLastName()));
        } else {
            profilePictureView.setProfileId(null);
            userNameView.setText(getString(R.string.welcome));
        }
    }

    @Override
    protected void onActivityResult(
            final int requestCode,
            final int resultCode,
            final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PERMS_REQUEST) {
            if (resultCode == RESULT_OK) {
                String[] readPermsArr = data
                        .getStringArrayExtra(PermissionSelectActivity.EXTRA_SELECTED_READ_PARAMS);
                String writePrivacy = data
                        .getStringExtra(PermissionSelectActivity.EXTRA_SELECTED_WRITE_PRIVACY);
                String[] publishPermsArr = data
                        .getStringArrayExtra(
                                PermissionSelectActivity.EXTRA_SELECTED_PUBLISH_PARAMS);

                fbLoginButton.clearPermissions();

                if (readPermsArr != null) {
                    if (readPermsArr.length > 0) {
                        fbLoginButton.setReadPermissions(readPermsArr);
                    }
                }

                if ((readPermsArr == null ||
                        readPermsArr.length == 0) &&
                        publishPermsArr != null) {
                    if (publishPermsArr.length > 0) {
                        fbLoginButton.setPublishPermissions(publishPermsArr);
                    }
                }
                // Set write privacy for the user
                if ((writePrivacy != null)) {
                    DefaultAudience audience;
                    if (DefaultAudience.EVERYONE.toString().equals(writePrivacy)) {
                        audience = DefaultAudience.EVERYONE;
                    } else if (DefaultAudience.FRIENDS.toString().equals(writePrivacy)) {
                        audience = DefaultAudience.FRIENDS;
                    } else {
                        audience = DefaultAudience.ONLY_ME;
                    }
                    fbLoginButton.setDefaultAudience(audience);
                }
            }
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
}
