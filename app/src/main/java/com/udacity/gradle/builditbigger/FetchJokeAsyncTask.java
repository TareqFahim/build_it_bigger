package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.example.hope.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by Hope on 8/31/2017.
 */

public class FetchJokeAsyncTask extends AsyncTask<Void, Void, String> {

    private static MyApi mApiService = null;
    private String jokeStr;
    private JokeCallback mJokeCallback;

    public FetchJokeAsyncTask(JokeCallback jokeCallback){
        this.mJokeCallback = jokeCallback;
    }

    @Override
    protected String doInBackground(Void... params) {
        if(mApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            mApiService = builder.build();
        }
        try {
            jokeStr =  mApiService.getJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            jokeStr =  null;
        }
        return jokeStr;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mJokeCallback.getJokeWhenfetched(s);
    }

    interface JokeCallback{
        void getJokeWhenfetched(String joke);
    }
}
