package model;

import java.util.ArrayList;

public class Notificacoes {

    private static Notificacoes instance = null;
    private ArrayList<Notificacao> notificacoes;

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
        }
    }

    private boolean notificacaoJaExiste(Notificacao notifi) {
        for (Notificacao n : notificacoes) {
            n.setVisualizada(false);
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
    }

    public ArrayList<Notificacao> getNotificacoes() {
        return this.notificacoes;
    }
}
