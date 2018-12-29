package interfaces;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public interface Drawable
{
    void Move(double up,double down, double left, double right);
    void SetOnDrag(Boolean is_set);
    void Resize(double factor,boolean is_horizontal, boolean is_vertical);
    void SetColor(Color color);
    void Delete();
    double GetScaleX();
    double GetScaleY();
    double GetCenterX();
    double GetCenterY();
    void SetCenterX(double value);
    void SetCenterY(double value);
    void Clear();
}