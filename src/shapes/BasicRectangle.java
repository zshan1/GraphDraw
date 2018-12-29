package shapes;

import interfaces.Drawable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.*;
import javafx.scene.shape.Line;
import main.Canvas;
import main.Palette;

/**
 * Created by qin_j on 2016/11/15.
 */
public class BasicRectangle extends BasicShape implements Drawable
{
    private Canvas canvas;

    private Rectangle rectangle;
    public boolean is_composite;
    private double center_x;
    private double center_y;
    private double height;
    private double width;
    private double x;
    private double y;

    public BasicRectangle(Canvas canvas)
    {
        super(canvas);
        this.is_composite=false;
        canvas.all_shapes.add(this);
        this.x=50;
        this.y=50;
        this.height=50;
        this.width=100;
        this.center_x=x+width/2;
        this.center_y=y+height/2;
        this.canvas=canvas;
        this.rectangle=new Rectangle(x,y,width,height);
        this.SetColor(Palette.cp_palette.getValue());
        this.shape=rectangle;
        canvas.getChildren().addAll(this.rectangle);
        SetOnDrag(true);
        //canvas.selected_shape=this;
        canvas.setFocusTraversable(true);

        this.rectangle.setOnMouseClicked(e->{
            if(Palette.mode_shape_select)
            {
//                if(!shape.contains(e.getX(),e.getY()))
//                {
//                    Palette.mode_shape_select=false;
//                    System.out.println("Select mode OFF");
//                }else{

                //Palette.mode_shape_select=false;
                if(Palette.mode_shape_connect){
                    //int size = canvas.selected_shapes.size();
                    if(canvas.selected_shape != null){
                        // draw line between shapes

                        //double startX = canvas.selected_shapes.get(size - 1).GetCenterX();
                        //double startY = canvas.selected_shapes.get(size - 1).GetCenterY();
                        //double endX = this.GetCenterY();
                        //double endY = this.GetCenterY();
                        //canvas.selected_shapes.add();
                        BasicLine line = new BasicLine(canvas, canvas.selected_shape, this);
                        //line.startXProperty().bind(canvas.selected_shapes.get(size - 1).);
                        //canvas.selected_shapes.add(line);
                        canvas.selected_shape.addOutEdge(line);
                        this.addInEdge(line);
                    }
                }
                    canvas.selected_shape = this;
//                }
                if(!Palette.mode_shape_multi_select)
                    canvas.selected_shapes.clear();
                // add anyway
                if(!canvas.selected_shapes.contains(this))
                    canvas.selected_shapes.add(this);

                System.out.println("Rectangle Selected!");
                System.out.println("canvas group" + canvas.selected_shapes.size());
            }


        });
    }
    public void Clear()
    {

    }
    public void SetOnDrag(Boolean is_set)
    {
        if(is_set)
        {
            this.rectangle.setOnMouseDragged(e -> {
                this.SetCenterX(e.getX());
                this.SetCenterY(e.getY());

                for(BasicLine line: this.outEdges){
                    line.setStartX(this.GetCenterX());
                    line.setStartY(this.GetCenterY());
                }

                for(BasicLine line: this.inEdges){
                    line.setEndX(this.GetCenterX());
                    line.setEndY(this.GetCenterY());
                }
            });

        }
    }
    public double GetCenterX()
    {
        return this.center_x;
    }
    public double GetCenterY()
    {
        return this.center_y;
    }
    public void SetCenterX(double value)
    {
        this.center_x=value;
        this.rectangle.setX(this.center_x-this.width/2);
    }

    public void SetCenterY(double value)
    {
        this.center_y=value;
        this.rectangle.setY(this.center_y-this.height/2);
    }

    public void Move(double up, double down, double left,double right)
    {
        this.SetCenterX(this.GetCenterX()+right-left);
        this.SetCenterY(this.GetCenterY()+down-up);

        for(BasicLine line: this.outEdges){
            line.setStartX(this.GetCenterX());
            line.setStartY(this.GetCenterY());
        }

        for(BasicLine line: this.inEdges){
            line.setEndX(this.GetCenterX());
            line.setEndY(this.GetCenterY());
        }
    }

    public void Resize(double factor,boolean is_horizontal, boolean is_vertical)
    {
        if(is_horizontal)
            this.rectangle.setScaleX(this.rectangle.getScaleX()*factor);
        if(is_vertical)
            this.rectangle.setScaleY(this.rectangle.getScaleY()*factor);
    }

    public double GetScaleX()
    {
        return this.rectangle.getScaleX();
    }

    public double GetScaleY()
    {
        return this.rectangle.getScaleY();
    }
    public void SetColor(Color color)
    {
        this.rectangle.setFill(color);
    }

    public void Delete()
    {
        canvas.all_shapes.remove(this);
        canvas.getChildren().remove(this.rectangle);
    }
}
