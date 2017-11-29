package com.mio.app.mioapp.control;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Japo on 28/11/17.
 */

public class TokenMngr extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String myToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("MYTOKEN","THE TOKEN: "+myToken);
    }
}


