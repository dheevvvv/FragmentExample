/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.fragmentexample;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mOpenButton;
    private Boolean isFragmentDisplayed = false;
    private static final String FRAGMENT_STATE = "fragment-state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOpenButton = findViewById(R.id.open_button);

        if (savedInstanceState != null){
            isFragmentDisplayed = savedInstanceState.getBoolean(FRAGMENT_STATE);
            if(isFragmentDisplayed){
                showFragment();
            }
            else {
                closeFragment();
            }
        }
        mOpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFragmentDisplayed){
                    showFragment();
                }
                else{
                    closeFragment();
                }
            }
        });
    }

    private void showFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SimpleFragment simpleFragment = SimpleFragment.newInstance();
        fragmentTransaction.add(R.id.fragment_container, simpleFragment).addToBackStack(null).commit();

        mOpenButton.setText(R.string.close);
        isFragmentDisplayed = true;

    }

    private void closeFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        fragmentTransaction.remove(simpleFragment).commit();

        mOpenButton.setText(R.string.open);
        isFragmentDisplayed = false;

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(FRAGMENT_STATE,isFragmentDisplayed );
        super.onSaveInstanceState(outState);
    }
}
