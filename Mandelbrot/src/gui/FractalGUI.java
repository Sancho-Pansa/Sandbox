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
	private final double MAX_WIDTH = 1000;
	private final int MAX_ITERATIONS = 50;
	
	private final Complex MIN_MANDELBROT = new Complex(-2, -2);
	private final Complex MAX_MANDELBROT = new Complex(2, 2);
	
	private final double RCode = 0.6;
	private final double GCode = 1.0;
	private final double BCode = 0.6;
	
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
		Runnable r1 = () -> {fillCanvasWithStep(gc, minC, precision, 4, 0);};
		r1.run();
		Runnable r2 = () -> {fillCanvasWithStep(gc, minC, precision, 4, 1);};
		r2.run();
		Runnable r3 = () -> {fillCanvasWithStep(gc, minC, precision, 4, 2);};
		r3.run();
		Runnable r4 = () -> {fillCanvasWithStep(gc, minC, precision, 4, 3);};
		r4.run();
	}

	private void fillCanvasWithStep(GraphicsContext gc, Complex minC, double precision, int step, int startY) {
		Fractal fract;
		for (double y = minC.getIm() + precision * startY, yPixel = startY; 
				yPixel < MAX_WIDTH; 
				y += precision * step, yPixel += step) {
			for (double x = minC.getRe(), xPixel = 0; 
					xPixel < MAX_WIDTH; 
					x += precision, xPixel++) {
				fract = new Mandelbrot(MAX_ITERATIONS, new Complex(x, y));
				int iter = fract.createFractal();
				double t = iter / (double) MAX_ITERATIONS;
				if (iter <= 1)
					gc.setFill(Color.gray(0.0));
				else if (iter != MAX_ITERATIONS)
					gc.setFill(Color.color(t * RCode, t * GCode, t * BCode));
				else
					gc.setFill(Color.color(RCode, GCode, BCode));
				gc.fillRect(xPixel, yPixel, 1, 1);

			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
