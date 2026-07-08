package com.glyph.app;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setContentView(webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/glyph-word-game.html");
    }

    @Override
    public void onBackPressed() {
        webView.evaluateJavascript(
            "(function(){" +
            "var m=document.querySelector('.modal-overlay.show');" +
            "if(m){m.classList.remove('show');return 1;}" +
            "var s=document.querySelector('.screen.active');" +
            "if(s&&s.id!='screen-home'&&s.id!='screen-game'){" +
            "var b=document.querySelector('[data-back]');" +
            "if(b){b.click();return 1;}}" +
            "if(s&&s.id=='screen-game'){" +
            "document.getElementById('game-back').click();return 1;}" +
            "return 0;" +
            "})()",
            result -> {
                if ("0".equals(result)) {
                    finish();
                }
            }
        );
    }
}
