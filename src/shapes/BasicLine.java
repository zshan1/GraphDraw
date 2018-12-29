package shapes;

import interfaces.Drawable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import main.Canvas;
import main.Palette;

/**
 * Created by danny on 2016/12/6.
 */
public class BasicLine extends BasicShape implements Drawable {
    private Canvas canvas;
    private Line line;
    public BasicLine(Canvas canvas, BasicShape outShape, BasicShape inShape) {
        super(canvas);
        this.canvas = canvas;
        this.canvas.all_shapes.add(this);
        this.SetColor(Palette.cp_palette.getValue());
        line = new Line(outShape.GetCenterX(),outShape.GetCenterY(),inShape.GetCenterX(),inShape.GetCenterY());
        this.shape = line;
        canvas.getChildren().addAll(this.line);
        SetOnDrag(true);
    }

    public void setStartX(double x){
        this.line.setStartX(x);
    }

    public void setStartY(double y){
        this.line.setStartY(y);
    }

    public void setEndX(double x){
        this.line.setEndX(x);
    }

    public void setEndY(double y){
        this.line.setEndY(y);
    }

}
