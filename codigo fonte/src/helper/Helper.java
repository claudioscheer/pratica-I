package helper;

import com.alee.managers.notification.NotificationManager;
import com.alee.managers.notification.WebNotification;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Helper {

    public static int TempoPadrao = 5000;
    
    public static class CoresPadrao {
        public static Color fundoDesktop = Color.decode("#B0BEC5");
        public static Color fundoPadrao = Color.decode("#263238");
    }

    public static enum TipoNotificacao {
        erro,
        ok
    }

    public static enum Image {
        fundo,
        logo,
        frame,
        erro,
        ok,
        add,
        edit,
        delete,
        atualizar,
        notificacao,
        gifCarregando,
    }

    public static ImageIcon getImage(Image icone) {
        String url = "";
        switch (icone) {
            case fundo:
                url = "fundo2.jpg";
                break;

            case logo:
                url = "logo.png";
                break;

            case frame:
                url = "frame.png";
                break;

            case erro:
                url = "erro.png";
                break;
            case ok:
                url = "ok.png";
                break;

            case add:
                url = "add.png";
                break;

            case edit:
                url = "edit.png";
                break;

            case delete:
                url = "delete.png";
                break;

            case atualizar:
                url = "atualizar.png";
                break;

            case notificacao:
                url = "notificacao2.png";
                break;

            case gifCarregando:
                url = "carregando.gif";
                break;
        }

        return new ImageIcon(Helper.class.getResource("/imagens/" + url));
    }

    public static void notificacao(String mensagem, TipoNotificacao tipoNotificacao, int tempo) {
        WebNotification notificationPopup = new WebNotification();
        if (tipoNotificacao == TipoNotificacao.erro) {
            notificationPopup.setIcon(getImage(Image.erro));
        } else {
            notificationPopup.setIcon(getImage(Image.ok));
        }
        notificationPopup.setDisplayTime(tempo <= 0 ? TempoPadrao : tempo);
        notificationPopup.setContent(mensagem);
        NotificationManager.showNotification(notificationPopup);
    }

}
