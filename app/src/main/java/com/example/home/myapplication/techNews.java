package com.example.home.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;


public class techNews extends AppCompatActivity {
    private static final String TAG = techNews.class.getSimpleName();
    private ListView mListView;
    private ArticlesViewAdapter mListAdapter;
    private ArrayList<ArticlesItem> mListData;
    private String FEED_URL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tech_news);

        String name = getIntent().getStringExtra("name");
        String id = getIntent().getStringExtra("id");
        setTitle(name);

//the Api key for tech news via techcrunch
        FEED_URL = "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=cccda6a6c2844d8da3bcda3d1d3837c4";
        mListView = (ListView) findViewById(R.id.listView);


        mListData = new ArrayList<>();
        mListAdapter = new ArticlesViewAdapter(this, R.layout.content_layout, mListData);
        mListView.setAdapter(mListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArticlesItem item =(ArticlesItem) parent.getItemAtPosition(position);

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));

                startActivity(browserIntent);
            }
        });

        new techNews.AsyncHttpTask().execute(FEED_URL);
    }

    //Dusing bckground thread, main thread par nhi hota..
    public class AsyncHttpTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpsURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpsURLConnection) url.openConnection();


                if (result != null) {

                    String response = streamToString(urlConnection.getInputStream());


                    parseResult(response);



                    return result;



                }
            } catch (MalformedURLException e) {
                e.printStackTrace();


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(String result) {

            if (result != null) {
                mListAdapter.setListData(mListData);

            } else {
                Toast.makeText(techNews.this, "Data nhi Mila ji!!", Toast.LENGTH_SHORT).show();//Error Message!!!!
         }
        }}

    String streamToString(InputStream stream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String line;
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }

        // Close stream
        if (null != stream) {
            stream.close();
        }
        return result;
    }
    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("articles");
            ArticlesItem item;
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                String title = post.optString("title");
                String image = post.optString("urlToImage");
                String description = post.optString("description");
                String author = post.optString("author");
                String url = post.optString("url");
                item = new ArticlesItem();
                item.setTitle(title);
                item.setImage(image);
                item.setUrl(url);
                item.setDescription(description);
                item.setAuthor(author);

                mListData.add(item);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
