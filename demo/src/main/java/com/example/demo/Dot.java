package com.example.demo;

import javafx.geometry.Point2D;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

public record Dot(double centerX, double centerY, Color color, double radius) {
    public static String toMessage(double centerX, double centerY, Color color,  double radius){
        return String.format(centerX + ";" + centerY + ";" + color + ";" + radius);
    }

    public static Dot fromMessage(String message){
        String [] parts = message.split(";");
        double centerX = Double.parseDouble(parts[0]);
        double centerY = Double.parseDouble(parts[1]);
        Color color = Color.valueOf(parts[2]);
        double radius = Double.parseDouble(parts[3]);;
        return new Dot(centerX, centerY, color, radius);
    }
}
