package alc4.challenge.project.view;

import android.content.DialogInterface;
import android.net.http.SslError;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import alc4.challenge.project.R;
import alc4.challenge.project.controller.DataConnection;

public class ALCActivity extends AppCompatActivity {

    private  WebView webView;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);


        webView = (WebView) findViewById(R.id.web_view);
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);

        getSupportActionBar().setTitle(R.string.about_alc);
        getSupportActionBar().setElevation(0);



        checkNetwork(); //check for device network connection



        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {



            @Override
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(ALCActivity.this);
                builder.setMessage(R.string.notification_error_ssl_cert_invalid);
                builder.setPositiveButton(R.string.user_prompt_continue, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.proceed();
                    }
                });
                builder.setNegativeButton(R.string.user_prompt_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.cancel();
                    }
                });
                final AlertDialog dialog = builder.create();
                dialog.show();
            }




            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.GONE);
            }



            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Toast.makeText(ALCActivity.this, R.string.page_access_error, Toast.LENGTH_SHORT).show();
            }
        });



    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_about:
                aboutApp();
                break;

            case R.id.btn_refresh:
                webView.reload();
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






    private void checkNetwork() {
        if (!DataConnection.checkInternetConnection(this)) {
            Toast.makeText(getApplicationContext(),R.string.error_message_no_internet, Toast.LENGTH_LONG).show();
            alertNetworkError();
        }else {
            loadResource();

        }
    }

    private void alertNetworkError() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.check_internet);
        builder.setTitle(R.string.error_message_no_internet);
        builder.setPositiveButton(R.string.user_confirmation_ok, null);
        builder.create().show();
        mProgressBar.setVisibility(View.GONE);



    }

    private void loadResource() {
        mProgressBar.setMax(50);
        webView.setVisibility(View.INVISIBLE);
        webView.loadUrl(getString(R.string.andela_about_page));

    }



    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }

    }


}
