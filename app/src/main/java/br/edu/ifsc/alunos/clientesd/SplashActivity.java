package br.edu.ifsc.alunos.clientesd;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private RelativeLayout layout_container;
    private ImageView imageViewImagem;
    private TextView textView;
    private Bundle mBundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Animation animacaoFadeIn = AnimationUtils.loadAnimation(this, R.anim.fab_scale_up);

        int lSplashAnimTime = 2500;

        animacaoFadeIn.setDuration(lSplashAnimTime);

        imageViewImagem = findViewById(R.id.imageViewImagem);
        layout_container = findViewById(R.id.layout_container);
        textView = findViewById(R.id.tv_version);
        layout_container.setVisibility(View.INVISIBLE);
        textView.setText(BuildConfig.VERSION_NAME);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, lSplashAnimTime);
    }
}
