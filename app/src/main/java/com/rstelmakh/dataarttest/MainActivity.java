package com.rstelmakh.dataarttest;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by roman on 3/15/2015.
 */
public class MainActivity extends Activity  implements OnStoreSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            addFragment(new StoresListFragment(), false);
        }
    }

    private void addFragment(Fragment fragment, boolean addToBackStack){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.add(R.id.container, fragment);
        if(addToBackStack){
            transaction.addToBackStack(fragment.getClass().getName());
        }
        transaction.commit();
    }

    @Override
    public void onStoreSelected(long storeId) {
        addFragment(InstrumentsListFragment.newInstance(storeId), true);
    }
}