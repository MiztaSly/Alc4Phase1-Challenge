package alc4.challenge.project.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import alc4.challenge.project.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView twitter;
    private ImageView ytube;
    private ImageView facebook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        twitter = (ImageView)findViewById(R.id.img_twitter);
        ytube = (ImageView)findViewById(R.id.img_ytube);
        facebook = (ImageView)findViewById(R.id.img_facebook);


        getSupportActionBar().setTitle(R.string.my_profile);
        getSupportActionBar().setElevation(0);



        twitter.setOnClickListener(this);
        ytube.setOnClickListener(this);
        facebook.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_twitter:
                startActivity(new Intent(ProfileActivity.this,TwitterActivity.class));
                break;
            case R.id.img_facebook:
                startActivity(new Intent(ProfileActivity.this,FacebookActivity.class));
                break;
            case R.id.img_ytube:
                Toast.makeText(this, getString(R.string.not_yet_response), Toast.LENGTH_LONG).show();
                break;

                default:
                    break;

        }

    }
}
