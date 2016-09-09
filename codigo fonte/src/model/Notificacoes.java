package model;

import forms.FormPrincipal;
import java.util.ArrayList;
import javax.swing.JLabel;

public class Notificacoes {

    private static Notificacoes instance = null;
    private ArrayList<Notificacao> notificacoes;
    private int qntdNotificacoes;

    private JLabel notificacoesLabel;

    public Notificacoes() {
        this.notificacoes = new ArrayList<>();
    }

    public static Notificacoes getInstance() {
        if (instance == null) {
            instance = new Notificacoes();
        }
        return instance;
    }

    public void add(Notificacao n) {
        if (!this.notificacaoJaExiste(n)) {
            this.notificacoes.add(n);
            this.qntdNotificacoes++;
        }
        this.setQntdNotificacoes();
    }

    private void setQntdNotificacoes() {
        FormPrincipal formPrincipal = FormPrincipal.getInstance();
        if (qntdNotificacoes <= 0) {
            formPrincipal.setQntdNotificacoes("");
        } else {
            formPrincipal.setQntdNotificacoes(String.valueOf(qntdNotificacoes));
        }
    }

    private boolean notificacaoJaExiste(Notificacao notifi) {
        for (Notificacao n : notificacoes) {
            //n.setVisualizada(false);
            if (notifi.equals(n)) {
                return true;
            }
        }
        return false;
    }

    public Notificacao get(int index) {
        return this.notificacoes.get(index);
    }

    public void setVisualizada(int index) {
        this.get(index).setVisualizada(true);
        this.qntdNotificacoes--;
        this.setQntdNotificacoes();
    }

    public ArrayList<Notificacao> getNotificacoes() {
        return this.notificacoes;
    }

    public void addMaisNotificacoes(ArrayList<Notificacao> notificacoes) {
        this.notificacoes.addAll(notificacoes);
    }
}
