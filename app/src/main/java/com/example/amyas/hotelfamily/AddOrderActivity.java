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
    public static final String EXTRA_TABLE_ID  = "uuid_table";
    public static final String EXTRA_ORDER_ID  = "uuid_order";
    @Override
    public Fragment getFragment() {
        UUID uuidTable = (UUID) getIntent().getSerializableExtra(EXTRA_TABLE_ID);
        UUID uuidOrder = (UUID) getIntent().getSerializableExtra(EXTRA_ORDER_ID);
        return AddOrderFragment.newInstance(uuidTable, uuidOrder);
    }
    public static Intent newIntent(Context context, UUID uuidTable, UUID uuidOrder){
        Intent intent = new Intent(context, AddOrderActivity.class);
        intent.putExtra(EXTRA_TABLE_ID, uuidTable);
        intent.putExtra(EXTRA_ORDER_ID, uuidOrder);
        return intent;
    }

}
