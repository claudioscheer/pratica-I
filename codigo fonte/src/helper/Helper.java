package helper;

import java.net.URL;

public class Helper {

    public URL loadImage(String imagem) {
        return getClass().getResource("/imagem/" + imagem);
    }

}
