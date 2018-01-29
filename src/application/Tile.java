package application;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {
	
	private int height;
	private int width;
	
    public Tile(Color c, int cellWidth, int cellHeight) {
    	this.width = cellWidth;
    	this.height = cellHeight;
    	set(c);
    }
    	
    public void set(Color c) {
        getChildren().clear();

    	Rectangle r = new Rectangle(width, height);
		r.setStroke(Color.BLACK);
		r.setFill(Color.LIGHTGRAY);
		
        getChildren().add(r);
        
        if (c == null)
        	return;
        
        Ellipse bg = new Ellipse(width * 0.38, height * 0.32);
        bg.setFill(Color.GREY);

        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(width * 0.03);

        bg.setTranslateY(height * 0.07);

        Ellipse ellipse = new Ellipse(width * 0.38, height * 0.32);
        ellipse.setFill(c);

        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(width * 0.03);

        getChildren().addAll(bg, ellipse);
    }
}
