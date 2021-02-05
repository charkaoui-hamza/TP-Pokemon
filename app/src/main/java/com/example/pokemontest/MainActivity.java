package com.example.pokemontest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;

import com.example.pokemontest.Common.Common;
import com.example.pokemontest.models.Pokemon;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private TabLayout mimiToolbar;
    private ViewPager mimiViewpager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mimiToolbar = findViewById(R.id.mimiToolbar);
        mimiViewpager =  findViewById(R.id.mimiViewpager);

        ArrayList<String> mimiGeneration  = new ArrayList<>();
        mimiGeneration.add("gen1");
        mimiGeneration.add("gen2");
        mimiGeneration.add("gen3");
        mimiGeneration.add("gen4");
        mimiGeneration.add("gen5");
        mimiGeneration.add("gen6");
        mimiGeneration.add("gen7");
        mimiGeneration.add("gen8");

        mimiPager(mimiViewpager,mimiGeneration);
        mimiToolbar.setupWithViewPager(mimiViewpager);


        //

    }



    private void mimiPager(ViewPager mimiViewpager, ArrayList<String> mimiGeneration) {
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        PokemonListByGeneration fragment = new PokemonListByGeneration();
        for (int i = 0; i < mimiGeneration.size();i++){
                Bundle bundle = new Bundle();
                bundle.putString("title",mimiGeneration.get(i));
                bundle.putInt("Gen", i+1);
                fragment.setArguments(bundle);
                adapter.addFragment(fragment,mimiGeneration.get(i));
                fragment = new PokemonListByGeneration();
        }
        mimiViewpager.setAdapter(adapter);
    }
    private class  MainAdapter extends FragmentPagerAdapter{
        ArrayList<String> arrayList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment,String title){
            arrayList.add(title);
            fragmentList.add(fragment);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return arrayList.get(position);
        }
    }




}