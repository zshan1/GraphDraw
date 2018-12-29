package shapes;

import interfaces.Drawable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import main.Canvas;
import main.Palette;
import java.util.List;

/**
 * Created by qin_j on 2016/11/15.
 */
public class BasicPolygon extends BasicShape implements Drawable
{
    private Canvas canvas;

    private Polygon polygon;
    public boolean is_composite;
    private double center_x;
    private double center_y;
    private double[] points;

    public BasicPolygon(Canvas canvas)
    {
        super(canvas);
        this.is_composite=false;
        canvas.all_shapes.add(this);
        this.points=new double[6];
        this.points[0]=50;
        this.points[1]=50;
        this.points[2]=60;
        this.points[3]=70;
        this.points[4]=100;
        this.points[5]=80;
        this.center_x=(this.points[0]+this.points[2]+this.points[4])/3;
        this.center_y=(this.points[1]+this.points[3]+this.points[5])/3;
        this.canvas=canvas;
        this.polygon=new Polygon();
        this.polygon.getPoints().addAll(new Double[]{
                50.0, 50.0,
                160.0, 70.0,
                100.0, 80.0 });
        this.SetColor(Palette.cp_palette.getValue());
        this.shape=polygon;
        canvas.getChildren().addAll(this.polygon);
        SetOnDrag(true);
        //canvas.selected_shape=this;
        canvas.setFocusTraversable(true);

        this.polygon.setOnMouseClicked(e->{
            if(Palette.mode_shape_select)
            {
//                if(!shape.contains(e.getX(),e.getY())) {
//                    Palette.mode_shape_select=false;
//                }else{
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
                        canvas.selected_shape.addOutEdge(line);
                        this.addInEdge(line);
                        //line.startXProperty().bind(canvas.selected_shapes.get(size - 1).);
                        //canvas.selected_shapes.add(line);

                    }
                }
                    canvas.selected_shape = this;
//                }

                if(!Palette.mode_shape_multi_select)
                    canvas.selected_shapes.clear();

                if(!canvas.selected_shapes.contains(this))
                    canvas.selected_shapes.add(this);
                //canvas.selected_shapes.add(this);

                System.out.println("polygon Selected!");
                System.out.println("canvas group" + canvas.selected_shapes.size());
            }

//            if(Palette.mode_shape_connect){
//                int size = canvas.selected_shapes.size();
//                if(size > 1){
//                    // draw line between shapes
//
//                    double startX = canvas.selected_shapes.get(size - 1).GetCenterX();
//                    double startY = canvas.selected_shapes.get(size - 1).GetCenterY();
//                    double endX = this.GetCenterY();
//                    double endY = this.GetCenterY();
//                    //canvas.selected_shapes.add();
//                    BasicLine line = new BasicLine(canvas, startX, startY, endX, endY);
//                    //line.startXProperty().bind(canvas.selected_shapes.get(size - 1).);
//                    //canvas.selected_shapes.add(line);
//                }
//            }
        });
    }
    public void Clear()
    {

    }
    public void SetOnDrag(Boolean is_set)
    {
        if(is_set)
        {
            this.polygon.setOnMouseDragged(e -> {

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

        polygon.setLayoutX(value);
    }
    public void SetCenterY(double value)
    {
        this.center_y=value;
        polygon.setLayoutY(value);
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
            this.polygon.setScaleX(this.polygon.getScaleX()*factor);
        if(is_vertical)
            this.polygon.setScaleY(this.polygon.getScaleY()*factor);
    }

    public double GetScaleX()
    {
        return this.polygon.getScaleX();
    }

    public double GetScaleY()
    {
        return this.polygon.getScaleY();
    }
    public void SetColor(Color color)
    {
        this.polygon.setFill(color);
    }

    public void Delete()
    {
        canvas.all_shapes.remove(this);
        canvas.getChildren().remove(this.polygon);
    }
}
