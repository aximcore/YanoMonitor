package net.sourceforge.yanonymous;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import net.sourceforge.yanomsg.YanoMsg.Graph;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class PuzzleClient {

  interface PuzzleListener {
    void onResponse(String response);

    void onError(String error);
  }

  private PuzzleListener listener;

  public PuzzleClient(PuzzleListener listener) {
    this.listener = listener;
  }

  public void execute(String url, Graph graph) {
    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(url);

    httppost.setEntity(new ByteArrayEntity(graph.toByteArray()));
    try {
      HttpResponse response = httpclient.execute(httppost);
      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        HttpEntity entity = response.getEntity();
        if (entity != null) {
          BufferedReader br =
              new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
          StringBuilder sb = new StringBuilder();
          String line;
          while ((line = br.readLine()) != null) {
            sb.append(line);
          }
          listener.onResponse(sb.toString());
        } else
          listener.onError("Empty response");
      } else
        listener.onError("SC_" + response.getStatusLine().getStatusCode());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
