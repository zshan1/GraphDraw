package shapes;

import interfaces.Drawable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import main.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qin_j on 2016/11/15.
 */
public class BasicShape implements Drawable
{
    private Canvas canvas;

    public boolean is_composite;
    public Shape shape;

    public List<BasicLine> outEdges;
    public List<BasicLine> inEdges;

    public void addOutEdge(BasicLine line){
        this.outEdges.add(line);
    }

    public void addInEdge(BasicLine line){
        this.inEdges.add(line);
    }

    public BasicShape(Canvas canvas)
    {
        this.is_composite=false;
        this.shape=null;
        this.outEdges = new ArrayList<>();
        this.inEdges = new ArrayList<>();
    }
    public void Clear()
    {

    }
    public void SetOnDrag(Boolean is_set)
    {

    }
    public double GetCenterX()
    {
        return 0;
    }
    public double GetCenterY()
    {
        return 0;
    }
    public void SetCenterX(double value)
    {
    }
    public void SetCenterY(double value)
    {

    }

    public void Move(double up, double down, double left,double right)
    {

    }

    public void Resize(double factor,boolean is_horizontal, boolean is_vertical)
    {

    }

    public double GetScaleX()
    {
        return 0;
    }

    public double GetScaleY()
    {
        return 0;
    }
    public void SetColor(Color color)
    {

    }

    public void Delete()
    {

    }
}
