package shapes;
import interfaces.Drawable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import main.*;

/**
 * Created by qin_j on 2016/11/15.
 */
public class BasicCircle extends BasicShape implements Drawable
{
    private Canvas canvas;

    private Circle circle;
    public boolean is_composite;
    public BasicCircle(Canvas canvas)
    {
        super(canvas);
        this.is_composite=false;
        canvas.all_shapes.add(this);
        double x=100;
        double y=100;
        double r=50;
        this.canvas=canvas;
        this.circle=new Circle(x,y,r);
        this.SetColor(Palette.cp_palette.getValue());
        this.shape=circle;
        canvas.getChildren().addAll(this.circle);
        SetOnDrag(true);
        //canvas.selected_shape=this;
        canvas.setFocusTraversable(true);

        this.circle.setOnMouseClicked(e->{
            //System.out.println("Circle Clicked!");
            if(Palette.mode_shape_select)
            {
//                if(!shape.contains(e.getX(),e.getY()))
//                {
//                    Palette.mode_shape_select=false;
//                    System.out.println("Select mode OFF");
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
                        //line.startXProperty().bind(canvas.selected_shapes.get(size - 1).);
                        //canvas.selected_shapes.add(line);
                        canvas.selected_shape.addOutEdge(line);
                        this.addInEdge(line);
                    }
                }
                    canvas.selected_shape = this;
                //}
                if(!Palette.mode_shape_multi_select)
                    canvas.selected_shapes.clear();

                if(!canvas.selected_shapes.contains(this))
                    canvas.selected_shapes.add(this);



                System.out.println("Circle Selected!");
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
//
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
            this.circle.setOnMouseDragged(e -> {
                this.circle.setCenterX(e.getX());
                this.circle.setCenterY(e.getY());

                for(BasicLine line: this.outEdges){
                    line.setStartX(this.circle.getCenterX());
                    line.setStartY(this.circle.getCenterY());
                }

                for(BasicLine line: this.inEdges){
                    line.setEndX(this.circle.getCenterX());
                    line.setEndY(this.circle.getCenterY());
                }
            });
        }
    }
    public double GetCenterX()
    {
        return this.circle.getCenterX();
    }
    public double GetCenterY()
    {
        return this.circle.getCenterY();
    }
    public void SetCenterX(double value)
    {
        this.circle.setCenterX(value);
    }
    public void SetCenterY(double value)
    {
        this.circle.setCenterY(value);
    }

    public void Move(double up, double down, double left,double right)
    {
        this.SetCenterX(this.GetCenterX()+right-left);
        this.SetCenterY(this.GetCenterY()+down-up);

        for(BasicLine line: this.outEdges){
            line.setStartX(this.circle.getCenterX());
            line.setStartY(this.circle.getCenterY());
        }

        for(BasicLine line: this.inEdges){
            line.setEndX(this.circle.getCenterX());
            line.setEndY(this.circle.getCenterY());
        }
    }

    public void Resize(double factor,boolean is_horizontal, boolean is_vertical)
    {
        if(is_horizontal)
            this.circle.setScaleX(this.circle.getScaleX()*factor);
        if(is_vertical)
            this.circle.setScaleY(this.circle.getScaleY()*factor);
    }

    public double GetScaleX()
    {
        return this.circle.getScaleX();
    }

    public double GetScaleY()
    {
        return this.circle.getScaleY();
    }
    public void SetColor(Color color)
    {
        this.circle.setFill(color);
    }

    public void Delete()
    {
        canvas.all_shapes.remove(this);
        canvas.getChildren().remove(this.circle);
    }
}
