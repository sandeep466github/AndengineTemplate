package com.example.chintu.andenginetemplate.manager;

import android.os.AsyncTask;

public class LoadSceneManager extends AsyncTask<IAsyncCallback, Integer, Boolean> {


    // ===========================================================
    // Fields
    // ===========================================================

    private IAsyncCallback[] _params;

    // ===========================================================
    // Inherited Methods
    // ===========================================================

    @Override
    protected Boolean doInBackground(IAsyncCallback... params) {
        this._params = params;
        int count = params.length;
        for (int i = 0; i < count; i++) {
            params[i].workToDo();
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        int count = this._params.length;
        for (int i = 0; i < count; i++) {
            this._params[i].onComplete();
        }
    }
}