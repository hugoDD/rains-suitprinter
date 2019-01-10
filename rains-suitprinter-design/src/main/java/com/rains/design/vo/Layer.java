package com.rains.design.vo;


import java.io.Serializable;

public class Layer implements Serializable {

    private int fontSize = 12;
    private String type = "text";
    private String rp = "rp72";
    private float x;
    private float y;
    private float originalY;
    private String text;
    private String fontFamily = "仿宋_GB2312";
    private String fill = "#000000";
    private String color = "#000000";
    private String strokeWidth = "1";
    private String loopDirection = "";
    private int loopSpacing = 0;
    private String loopData = "";
    private String loopNum = "";
    private String visible = "";
    private String position = "left";
    private String maxWidth = ":";

    private int width =80;
    private int height=18;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRp() {
        return rp;
    }

    public void setRp(String rp) {
        this.rp = rp;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getOriginalY() {
        return originalY;
    }

    public void setOriginalY(float originalY) {
        this.originalY = originalY;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(String strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public String getLoopDirection() {
        return loopDirection;
    }

    public void setLoopDirection(String loopDirection) {
        this.loopDirection = loopDirection;
    }

    public int getLoopSpacing() {
        return loopSpacing;
    }

    public void setLoopSpacing(int loopSpacing) {
        this.loopSpacing = loopSpacing;
    }

    public String getLoopData() {
        return loopData;
    }

    public void setLoopData(String loopData) {
        this.loopData = loopData;
    }

    public String getLoopNum() {
        return loopNum;
    }

    public void setLoopNum(String loopNum) {
        this.loopNum = loopNum;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(String maxWidth) {
        this.maxWidth = maxWidth;
    }


}
