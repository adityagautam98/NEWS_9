/** package com.example.home.myapplication;
                                                                        WILL MAKE THEM SOON
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


public class current extends AppCompatActivity {
    private static final String TAG = current.class.getSimpleName();
    private ListView mListView2;
    private ArticlesViewAdapter mListAdapter;
    private ArrayList<ArticlesItem> mListData;
    private String FEED_URL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_news);

        String name = getIntent().getStringExtra("name");
        String id = getIntent().getStringExtra("id");
        setTitle(name);

//the Api key for wallstreet
        FEED_URL = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=cccda6a6c2844d8da3bcda3d1d3837c4";
        mListView2 = (ListView) findViewById(R.id.listView2);


        mListData2 = new ArrayList<>();
        mListAdapter2 = new ArticlesViewAdapter(this, R.layout.content_layout2, mListData2);
        mListView2.setAdapter(mListAdapter2);

        mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArticlesItem item =(ArticlesItem) parent.getItemAtPosition(position);

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));

                startActivity(browserIntent);
            }
        });

        new current.AsyncHttpTask().execute(FEED_URL);
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
                mListAdapter.setListData(mListData2);

            } else {
                Toast.makeText(current.this, "Data yahan bhi nhi Mila ji!!", Toast.LENGTH_SHORT).show();//Error Message!!!!
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
    }}
    */