package br.edu.ifsc.alunos.clientesd.resources.generics;

import android.os.Build;

import androidx.annotation.RequiresApi;

public interface ListenerService<E> {
    void success(E objeto);
    void error(String msmErro);
}
