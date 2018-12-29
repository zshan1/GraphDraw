package main;

import interfaces.Drawable;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.event.*;
import shapes.*;
import javafx.scene.shape.*;

/**
 * palette lets user select shape and color
 * @author AlexBeard
 *
 */
public class Palette extends VBox {
	
	
	private int height = 500;
	private int width = 150;
    public static ColorPicker cp_palette;
    public static Boolean mode_shape_choose=false;
    public static Boolean mode_shape_select=false;
    public static Boolean mode_shape_connect=false;
    public static Boolean mode_shape_group=false;
    public static Boolean mode_shape_multi_select=false;
    public static Color canvas_color;
    private Canvas canvas;
    private Button btn_shape_choose,btn_shape_select,btn_shape_connect,btn_shape_color,bnt_group_trigger;
    private Button btn_shape_circle,btn_shape_rect,btn_shape_polygon;

	public Palette(Canvas canvas) {
		super(10);
        this.canvas=canvas;
        this.setPadding(new Insets(5,5,5,5));
		this.setPrefHeight(height);
        this.setPrefWidth(width);
        this.InitComponents();
	}

	private void InitComponents()
    {
        //////////////////////////////////////////////////////
        // Init Color Picker
        this.cp_palette=new ColorPicker();
        final Text text=new Text("Select Color");
        text.setFont(Font.font ("Verdana", FontWeight.BOLD,14));
        text.setFill(Color.BLACK);

        this.getChildren().addAll(text,cp_palette);
        // Finish initializing Color Picker
        //////////////////////////////////////////////////////

        //////////////////////////////////////////////////////
        // Init Buttons
        btn_shape_circle=new Button("Circle");
        btn_shape_rect=new Button("Rectangle");
        btn_shape_polygon=new Button("Polygon");
        //btn_shape_choose=new Button("Shape");
        btn_shape_select=new Button("Select");
        btn_shape_color=new Button("Color");
        btn_shape_connect=new Button("Connect");
        bnt_group_trigger=new Button("Group/Ungroup");
        this.getChildren().addAll(btn_shape_circle,btn_shape_rect,btn_shape_polygon,btn_shape_select,btn_shape_color,btn_shape_connect,bnt_group_trigger);
        // Finish initializing Buttons
        //////////////////////////////////////////////////////

        //////////////////////////////////////////////////////
        // Setup buttons events
        btn_shape_circle.setOnMouseClicked(e->{
            mode_shape_choose=true;
            BasicCircle basic_circle=new BasicCircle(canvas);
        });
        btn_shape_rect.setOnMouseClicked(e->{
            mode_shape_choose=true;
            BasicRectangle basic_rect=new BasicRectangle(canvas);
        });
        btn_shape_polygon.setOnMouseClicked(e->{
            mode_shape_choose=true;
            BasicPolygon basic_polygon=new BasicPolygon(canvas);
        });
        /*
        btn_shape_choose.setOnMouseClicked(e->{
            mode_shape_choose=true;
            BasicCircle basic_circle=new BasicCircle(canvas);
            BasicRectangle basic_rect=new BasicRectangle(canvas);

        });
        */
        btn_shape_select.setOnMouseClicked(e->{
            mode_shape_select=true;
        });
        btn_shape_color.setOnMouseClicked(e->{
            canvas.selected_shape.SetColor(Palette.cp_palette.getValue());
        });
        btn_shape_connect.setOnMouseClicked(e->{
            mode_shape_connect=true;
        });
        bnt_group_trigger.setOnMouseClicked(e->{
            mode_shape_group=!mode_shape_group;
            if(mode_shape_group) {
                CompositeShape composite_shape = new CompositeShape(canvas);
                for (BasicShape shape : canvas.selected_shapes) {
                    if(!shape.is_composite) {
                        System.out.println("add" + shape.getClass().getName());
                        composite_shape.Add(shape);
                        canvas.all_shapes.remove(shape);
                    }
                }
                canvas.selected_shapes.clear();
                canvas.selected_shapes.add(composite_shape);
                canvas.all_shapes.add(composite_shape);
            }
            else
            {
                for(BasicShape shape : canvas.selected_shapes) {
                    shape.Clear();
                }
                canvas.selected_shapes.clear();
            }
        });
        // Finish setup
        //////////////////////////////////////////////////////
    }

}
