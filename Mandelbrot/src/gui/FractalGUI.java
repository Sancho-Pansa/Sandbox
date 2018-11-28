package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import math.*;

public class FractalGUI extends Application
{
	private final double MAX_WIDTH = 960;
	private final int MAX_ITERATIONS = 60;
	
	private final Complex MIN_MANDELBROT = new Complex(-2, -2);
	private final Complex MAX_MANDELBROT = new Complex(2, 2);
	
	@SuppressWarnings("unused")
	private static int zoom = 1;
	
	@Override
	public void start(Stage primaryStage) throws IOException
	{
		Pane fractalPane = new Pane();
		Canvas canvas = new Canvas(MAX_WIDTH, MAX_WIDTH);
		
		paintFractal(canvas.getGraphicsContext2D(), MIN_MANDELBROT, MAX_MANDELBROT);
		
		fractalPane.getChildren().add(canvas);
		
		/*canvas.setOnMouseClicked(event -> 
		{
			zoom = event.getButton() == MouseButton.PRIMARY ? (zoom *= 2) : (zoom /= 2);
			
			Complex start = new Complex(event.getSceneX(), event.getSceneY());
			Complex div = Complex.substraction(MAX_MANDELBROT, MIN_MANDELBROT);
			Complex newMin =  Complex.substraction(start, Complex.division(div, new Complex(zoom, 0)));
			Complex newMax = Complex.addition(start, Complex.division(div, new Complex(zoom, 0)));
			paintFractal(((Canvas) event.getSource()).getGraphicsContext2D(), newMin, newMax);
		});*/
		
		Scene scene = new Scene(fractalPane);
		primaryStage.setTitle("Mandelbrot");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void paintFractal(GraphicsContext gc, Complex minC, Complex maxC)
	{
		double precision = Math.max((maxC.getRe() - minC.getRe()) / MAX_WIDTH, 
				(maxC.getIm() - minC.getIm()) / MAX_WIDTH);
		Fractal fract;
		
		for(double x = minC.getRe(), xPixel = 0; xPixel < MAX_WIDTH; x += precision, xPixel++)
		{
			for(double y = minC.getIm(), yPixel = 0; yPixel < MAX_WIDTH; y += precision, yPixel++)
			{
				fract = new Mandelbrot(MAX_ITERATIONS, new Complex(x, y));
				int iter = fract.createFractal();
				double t = iter / (double) MAX_ITERATIONS;
				if(iter <= 1)
					gc.setFill(Color.gray(0.1));
				else if(iter != MAX_ITERATIONS)
					gc.setFill(Color.color(t, t, 0.0));
					else
						gc.setFill(Color.color(1.0, 1.0, 0.75));
				gc.fillRect(xPixel, yPixel, 1, 1);
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
