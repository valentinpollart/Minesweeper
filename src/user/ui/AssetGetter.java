package user.ui;

import javax.swing.*;
import java.awt.*;

public class AssetGetter {

    public static final int TILE_SIZE_PX = 25;

    static ImageIcon loadImage(String imageName) {
         return new ImageIcon(new ImageIcon(AssetGetter.class.getResource("../../assets/" + imageName)).getImage().getScaledInstance(TILE_SIZE_PX,TILE_SIZE_PX, Image.SCALE_SMOOTH));
     }

}
