package net.sourceforge.yanonymous;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class PuzzleActivity extends Activity {

  public final static String BASE_URL = "http://localhost:8080/Puzzle";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.puzzle);

    WebView webView = (WebView) findViewById(R.id.webview);
    webView.loadUrl(BASE_URL);
  }
}
