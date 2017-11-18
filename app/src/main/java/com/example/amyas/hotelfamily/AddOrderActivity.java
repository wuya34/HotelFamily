package com.example.amyas.hotelfamily;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * author: Amyas
 * date: 2017/11/18
 */

public class AddOrderActivity extends SingleFragmentActivity {
    public static final String EXTRA_ID  = "uuid";
    @Override
    public Fragment getFragment() {
        UUID uuid = (UUID) getIntent().getSerializableExtra(EXTRA_ID);
        return AddOrderFragment.newInstance(uuid);
    }
    public static Intent newIntent(Context context, UUID uuid){
        Intent intent = new Intent(context, AddOrderActivity.class);
        if (uuid!=null){
            intent.putExtra(EXTRA_ID, uuid);
        }
        return intent;
    }

}
