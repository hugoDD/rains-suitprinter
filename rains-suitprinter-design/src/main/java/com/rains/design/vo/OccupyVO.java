package com.rains.design.vo;

import com.rains.design.constant.OutType;
import com.rains.design.constant.TPDConsts;

public class OccupyVO {

//    {
//        "fontSize": 22,
//            "type": "text",
//            "rp": "rp72",
//            "x": 202,
//            "y": 13,
//            "originalY": 7.40625,
//            "text": "转账交易电子回单",
//            "fontFamily": "仿宋_GB2312",
//            "fill": "#000000",
//            "color": "#000000",
//            "strokeWidth": "1",
//            "loopDirection": "",
//            "loopSpacing": 0,
//            "loopData": "",
//            "loopNum": "",
//            "visible": "",
//            "position": "left",
//            "maxWidth": ""
//    },

    private String name;
    private int width;
    private int height;
    private Integer outType = OutType.ONE.getCode();
    private int intervalLen;
    private int positionX = TPDConsts.BORDER;
    private int positionY = TPDConsts.BORDER;
    private FontColorVO fc;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

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

    public Integer getOutType() {
	return outType;
    }

    public void setOutType(Integer outType) {
	this.outType = outType;
    }

    public int getIntervalLen() {
	return intervalLen;
    }

    public void setIntervalLen(int intervalLen) {
	this.intervalLen = intervalLen;
    }

    public int getPositionX() {
	return positionX;
    }

    public void setPositionX(int positionX) {
	this.positionX = positionX;
    }

    public int getPositionY() {
	return positionY;
    }

    public void setPositionY(int positionY) {
	this.positionY = positionY;
    }

    public FontColorVO getFc() {
	return fc;
    }

    public void setFc(FontColorVO fc) {
	this.fc = fc;
    }

    public OccupyVO clone() {
	OccupyVO vo = new OccupyVO();
	vo.setName(name);
	vo.setWidth(width);
	vo.setHeight(height);
	vo.setOutType(outType);
	vo.setIntervalLen(intervalLen);
	vo.setPositionX(positionX);
	vo.setPositionY(positionY);
	if(fc!=null){
        vo.setFc(fc.clone());
    }

	return vo;
    }
}
