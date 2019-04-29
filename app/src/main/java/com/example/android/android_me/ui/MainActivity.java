package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    private  int headIndex;
    private  int bodyIndex;
    private  int legsIndex;

    private boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.android_me_linear_layout) !=null){
            mTwoPane = true;

            Button nextButton = (Button)findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            if (savedInstanceState == null) {



            FragmentManager  fragmentManager = getSupportFragmentManager();

            BodyPartFragment headFragment =new BodyPartFragment();

            //fragment transaction
            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit();
            BodyPartFragment bodyFragment = new BodyPartFragment();

            //fragment transaction
            fragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit();
            BodyPartFragment legsFragment = new BodyPartFragment();

            fragmentManager.beginTransaction()
                    .add(R.id.legs_container, legsFragment)
                    .commit();

        }}
        else {
            mTwoPane =false;
        }


}

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this,"Position clicked "+position, Toast.LENGTH_SHORT).show();

        int bodyPartNimber = position/12;

        int listIndex = position - 12 * bodyPartNimber;

        if (mTwoPane){

            BodyPartFragment newFragment = new BodyPartFragment();

            switch (bodyPartNimber){
                case 0:
                    newFragment.setImageId(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, newFragment)
                            .commit();
                    break;
                case 1:
                    newFragment.setImageId(AndroidImageAssets.getBodies());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, newFragment)
                            .commit();
                    break;
                case 2:
                    newFragment.setImageId(AndroidImageAssets.getLegs());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.legs_container, newFragment)
                            .commit();
                    break;
            }
        }

        else{

        switch (bodyPartNimber){
            case  0: headIndex = listIndex;
               break;
            case 1: bodyIndex = listIndex;
               break;
            case 2: legsIndex = listIndex;
               break;
            default: break;}

        Bundle b = new Bundle();

        b.putInt("headIndex", headIndex);
        b.putInt("bodyIndex", bodyIndex);
        b.putInt("legsIndex", legsIndex);

        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(b);

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }}
}
