package alc4.challenge.project.view;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import alc4.challenge.project.R;


public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button about_btn;
    private Button profile_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        about_btn = (Button) findViewById(R.id.about_btn);
        profile_btn = (Button) findViewById(R.id.profile_btn);


        getSupportActionBar().setTitle(R.string.alc_phase_1);
        getSupportActionBar().setElevation(0);


        about_btn.setOnClickListener(this);
        profile_btn.setOnClickListener(this);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.btn_refresh).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_about:
                aboutApp();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void aboutApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.about_app_builder);
        builder.setTitle(R.string.about_app);
        builder.setPositiveButton(R.string.user_confirmation_ok, null);
        builder.create().show();
    }


    /**
     * Logic to determine clicked button
     * @param v represent a view in this case a button
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.about_btn:
                startActivity(new Intent(WelcomeActivity.this, ALCActivity.class));
                break;

            case R.id.profile_btn:
                startActivity(new Intent(WelcomeActivity.this,ProfileActivity.class));
                break;

                default:
                    break;

        }
    }


}
