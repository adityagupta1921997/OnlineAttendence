package com.apkglobal.onlineattendence.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.apkglobal.onlineattendence.R;
import com.apkglobal.onlineattendence.SharedPreference.Login2Activity;
import com.apkglobal.onlineattendence.SharedPreference.Login3Activity;
import com.apkglobal.onlineattendence.SharedPreference.TeacherActivity;
import com.apkglobal.onlineattendence.login.LoginActivity;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private SliderLayout mDemoSlider;
   Button btn_adminlogin,btn_studlogin,btn_teacherlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Login");
        btn_adminlogin=(Button)findViewById(R.id.btn_admin);
        btn_teacherlogin=(Button)findViewById(R.id.btn_teacher);
        btn_studlogin=(Button)findViewById(R.id.btn_student);
        btn_teacherlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent teacher=new Intent(getApplicationContext(), TeacherActivity.class);
                startActivity(teacher);
            }
        });
        btn_studlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stud=new Intent(getApplicationContext(), Login3Activity.class);
                startActivity(stud);
            }
        });
        btn_adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ne=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(ne);
            }
        });


        //slider for image
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);

        HashMap<String,Integer> file_maps=new HashMap<String,Integer>();

        file_maps.put("WELCOME",R.drawable.download);
        file_maps.put("ENJOY THE GREENERY", R.drawable.download_1);
        file_maps.put("HAVE A EXPERIENCE HERE!",R.drawable.images);
        file_maps.put("PLAY IN FREE TIME", R.drawable.images_1);

        for (String name : file_maps.keySet())
        {
            TextSliderView textSliderView = new TextSliderView(this);
            //initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            mDemoSlider.addSlider(textSliderView);

        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);


    /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

    }


    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}