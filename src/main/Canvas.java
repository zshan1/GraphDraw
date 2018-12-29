package main;

import javafx.scene.layout.Pane;

import shapes.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The canvas on which shapes will be drawn.
 * @author 
 *
 */
public class Canvas extends Pane {


	private int width = 500;
	private int height = 500;
	private double move_offset=5;

	//private BasicCircle basic_circle;
	public BasicShape selected_shape; // current one, the last one of select_shapes
	public List<BasicShape> selected_shapes; // selected group
	public List<BasicShape> all_shapes; // all shape on canvas
	/**
	 * creates a new canvas
	 */
	public Canvas() {
		this.setPrefHeight(height);
		this.setPrefWidth(width);
		this.all_shapes=new ArrayList<>();
		this.selected_shape=null;
		this.selected_shapes=new ArrayList<>();
		setUpKeyPresses();
		this.setOnMouseClicked(e->{
			boolean releaseSelect = true;
			if(this.selected_shapes.size() > 0) {
				for (BasicShape shape : this.selected_shapes) {
					if (shape.shape.contains(e.getX(), e.getY())) {
						releaseSelect = false;
						break;
					}
				}
				if(releaseSelect) {
					System.out.println("Select mode OFF");
					this.selected_shape = null;
					this.selected_shapes.clear();
					Palette.mode_shape_select = false;
					Palette.mode_shape_multi_select = false;
					Palette.mode_shape_connect = false;
				}
			}
		});
	}


	/**
	 * Set up key pressed events. Fill in the key handler to manipulate the shapes on the screen.
	 */
	private void setUpKeyPresses() {

		this.setOnKeyPressed(e -> {
			System.out.println("Key was pressed");
			e.consume(); // avoid side effects from the key press, like changing the focus with arrow keys
			switch (e.getCode())
			{
				case UP:
					for(BasicShape shape : selected_shapes) {
						shape.Move(move_offset, 0, 0, 0);
					}
					break;
				case DOWN:
					for(BasicShape shape : selected_shapes) {
						shape.Move(0,move_offset,0,0);
					}
					break;
				case LEFT:
					for(BasicShape shape : selected_shapes) {
						shape.Move(0,0,move_offset,0);
					}
					break;
				case RIGHT:
					for(BasicShape shape : selected_shapes) {
						shape.Move(0,0,0,move_offset);
					}
					break;
				case DELETE:
					for(BasicShape shape : selected_shapes) {
						shape.Delete();
					}
					break;
				case Z:
					for(BasicShape shape : selected_shapes) {
						shape.Resize(0.95,true,false);
					}
					break;
				case X:
					for(BasicShape shape : selected_shapes) {
						shape.Resize(0.95,false,true);
					}
					break;
				case C:
					for(BasicShape shape : selected_shapes) {
						shape.Resize(1.05,true,false);
					}
					break;
				case S:
					for(BasicShape shape : selected_shapes) {
						shape.Resize(1.05,false,true);
					}
					break;
				case CONTROL:
					if(Palette.mode_shape_select) {
						Palette.mode_shape_multi_select = true;
						System.out.println("Multi-select mode ON");
					}
					else {
						Palette.mode_shape_multi_select = false;
						System.out.println("Multi-select mode OFF");
					}
					break;
				default:
					break;
			}
		});
		//System.out.println("Keys set up");
	}
}
