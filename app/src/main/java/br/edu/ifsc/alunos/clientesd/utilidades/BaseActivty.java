package br.edu.ifsc.alunos.clientesd.utilidades;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import br.edu.ifsc.alunos.clientesd.R;

public class BaseActivty extends AppCompatActivity implements MapElement {

    private Toolbar mToolbar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
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
}
