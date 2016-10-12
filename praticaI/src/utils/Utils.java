package utils;

import com.alee.managers.notification.NotificationManager;
import com.alee.managers.notification.WebNotification;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.text.MaskFormatter;

public class Utils {

    public static int TempoPadrao = 5000;

    public static int wPadrao = 600;
    public static int hPadrao = 500;

    public static class CoresPadrao {

        public static Color fundoDesktop = Color.decode("#B0BEC5");
        public static Color fundoPadrao = Color.decode("#263238");
        public static Color verde = Color.decode("#4caf50");
    }

    public static class MascarasPadrao {

        public static String valorDouble = "#######.##";
        public static String taxa = "###.##";
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
        adicionar,
        editar,
        deletar,
        atualizar,
        notificacao,
        gifcarregando,
        fechar,
        anterior,
        proximo,
        buscar,
        notafiscal,
        ativoimobilizado,
        pagar,
        FluxoCaixa
    }

    public static ImageIcon getImage(Image icone) {
        String url = "";
        switch (icone) {
            case fundo:
                url = "fundo.jpg";
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

            case adicionar:
                url = "adicionar.png";
                break;

            case editar:
                url = "editar.png";
                break;

            case deletar:
                url = "deletar.png";
                break;

            case atualizar:
                url = "atualizar.png";
                break;

            case notificacao:
                url = "notificacao2.png";
                break;

            case gifcarregando:
                url = "carregando.gif";
                break;

            case fechar:
                url = "fechar.png";
                break;

            case anterior:
                url = "anterior.png";
                break;

            case proximo:
                url = "proximo.png";
                break;

            case buscar:
                url = "buscar.png";
                break;

            case notafiscal:
                url = "notafiscal.png";
                break;

            case ativoimobilizado:
                url = "ativoimobilizado.png";
                break;

            case pagar:
                url = "pagar.png";
                break;

            case FluxoCaixa:
                url = "fluxoCaixa.png";
                break;

        }

        return new ImageIcon(Utils.class.getResource("/imagens/" + url));
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

    public static String formatData(Date data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dataFormat.format(data);
    }

    public static MaskFormatter getMascara(String formato) {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(formato);
            mask.setPlaceholderCharacter('0');
        } catch (ParseException ex) {
            mask = new MaskFormatter();
        }
        return mask;
    }

}
