package br.edu.ifsc.alunos.clientesd.utilidades;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import br.edu.ifsc.alunos.clientesd.R;

public abstract class BaseActivty extends AppCompatActivity implements MapElement {

    private Toolbar mToolbar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        createRestListener();
        mapComponents();
        mapActionComponents();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    public Toolbar getToolbar() {
       return mToolbar;
    }

    public void setTitleToolbar(String title) {
        setTitle(title);
    }

    public void setTitleToolbar(int resString) {
        mToolbar.setTitle(resString);
    }

    @Override
    public void mapComponents() {
        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
    }

    @Override
    public void mapActionComponents() {

    }

    public void configToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getToolbar().setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        getToolbar().setContentInsetsAbsolute(0, 0);
        getToolbar().setContentInsetStartWithNavigation(0);
        getToolbar().setContentInsetStartWithNavigation(0);
    }

    public abstract void createRestListener();
}
