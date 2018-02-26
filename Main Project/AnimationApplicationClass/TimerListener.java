import javafx.application.Application;

import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Scene;
import javafx.scene.Group;

import javafx.animation.AnimationTimer;


public class TimerListener extends AnimationTimer{

    public void handle(long now){
	System.out.println("Hi Brandon");
    }

}
