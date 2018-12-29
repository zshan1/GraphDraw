package shapes;
import java.util.*;

import interfaces.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.Canvas;
import main.Palette;

/**
 * Created by qin_j on 2016/11/15.
 */
public class CompositeShape extends BasicShape implements Drawable
{
    private List<BasicShape> child_drawable=new ArrayList<BasicShape>();

    public boolean is_composite;
    public void Add(BasicShape shape)
    {
        child_drawable.add(shape);
    }
    public void Remove(BasicShape shape)
    {
        child_drawable.remove(shape);
    }
    public void Clear()
    {
        for(BasicShape shape : this.child_drawable)
        {
            this.child_drawable.remove(shape);
        }
    }


    public CompositeShape(Canvas canvas)
    {
        super(canvas);
        is_composite=true;
        canvas.all_shapes.add(this);
    }
    public void Select()
    {
        for(BasicShape shape : this.child_drawable)
        {
        }
    }

    public void SetColor(Color color)
    {
        for(BasicShape shape : this.child_drawable)
        {
            shape.SetColor(Palette.canvas_color);
        }
    }

    public double GetCenterX()
    {
        double sum_x=0;
        System.out.println(this.child_drawable.size());
        for(BasicShape shape : this.child_drawable)
        {
            sum_x+=shape.GetCenterX();
        }
        return sum_x/this.child_drawable.size();
    }
    public double GetCenterY()
    {
        double sum_y=0;
        for(BasicShape shape : this.child_drawable)
        {
            sum_y+=shape.GetCenterY();
        }
        return sum_y/this.child_drawable.size();
    }

    public void SetCenterX(double value)
    {
        //center_x=value;
    }
    public void SetCenterY(double value)
    {
        //this.center_y=value;
    }
    public void SetOnDrag(Boolean is_set)
    {
        for(BasicShape shape : this.child_drawable)
        {
            if(is_set)
            {
                shape.SetOnDrag((true));
            }
        }
    }
    public void Move(double up, double down, double left,double right)
    {
        for(BasicShape shape: this.child_drawable)
        {
            shape.SetCenterX(shape.GetCenterX() + right - left);
            shape.SetCenterY(shape.GetCenterY() + down - up);
        }
    }
    public void Resize(double factor,boolean is_horizontal, boolean is_vertical)
    {
        for(BasicShape shape: this.child_drawable)
        {
            shape.Resize(factor,is_horizontal,is_vertical);
        }
    }
    public double GetScaleX()
    {
        return 0;
    }
    public double GetScaleY()
    {
        return 0;
    }

    public void Delete()
    {
        for(BasicShape shape: this.child_drawable)
        {
            shape.Delete();
        }
    }
}
