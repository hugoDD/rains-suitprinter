package com.rains.printer.layer;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouhao
 * @since 1.0
 */
@SuppressWarnings("ALL")
@EqualsAndHashCode(callSuper = true)
@Data
public class TextLayer extends AbstractLayer {

    private String text;

    //宽度，用于居中和自动换行
    private int width = -1;
    //高度
    private int height;

    private Align align = Align.left;

    private VerticalAlign verticalAlign = VerticalAlign.top;

    @Override
    protected void doDraw(Graphics2D graphics) {
        String text = getText();
        if (text == null) {
            text = "";
        }
        FontMetrics fontMetrics = graphics.getFontMetrics();

        List<java.util.function.Consumer<Integer>> runnables = new ArrayList<>();
        int textHeight = fontMetrics.getHeight();
        int fontSize = fontMetrics.getFont().getSize();
        //字体宽度大于总宽度,需要换行
        if (text.contains("\n") || (width > 0 && fontSize * textHeight > width)) {
            int totalHeight = 0;

            int nowY = 0;
            for (String line : text.split("[\n]")) {
                if (line.trim().length() == 0) {
                    continue;
                }
                StringBuilder temp = new StringBuilder();
                for (char c : line.toCharArray()) {
                    temp.append(c);
                    String tempStr = temp.toString();
                    boolean shouldNewLine;
                    if (align == Align.both) {
                        shouldNewLine = temp.length() * fontSize >= width;
                    } else {
                        shouldNewLine = getTextWidth(tempStr, fontMetrics) >= width;
                    }
                    if (shouldNewLine) {
                        int finalY = nowY;
                        runnables.add(y -> align.draw(graphics, tempStr, getWidth(), getX(), y + finalY));
                        nowY += textHeight;
                        totalHeight += textHeight;
                        temp = new StringBuilder();
                    }
                }
                if (temp.length() > 0) {
                    int finalY = nowY;
                    String tempStr = temp.toString();
                    runnables.add(y -> align.draw(graphics, tempStr, getWidth(), getX(), y + finalY));
                    nowY += textHeight;
                    totalHeight += textHeight;
                }

            }
            int bodyHeight = totalHeight;
            runnables.forEach(run -> run.accept(getVerticalAlign().compute(getY(), getHeight(), bodyHeight)));
        } else {

            align.draw(graphics, text, getWidth(), getX(), getVerticalAlign().compute(getY(), getHeight(), textHeight));
        }
    }

    private static void doDrawString(Graphics2D graphics, String text, float x, float y) {
        graphics.drawString(text, x, y + graphics.getFontMetrics().getMaxAscent() - graphics.getFontMetrics().getLeading());
    }


    private static int getTextWidth(String text, FontMetrics metrics) {
        return metrics.stringWidth(text);
    }

    public enum Align implements TextAlignPainter {
        /**
         * 2端对齐居中
         * <pre>
         *  -------------------
         *  |文              字|
         *  |两    端    对   齐|
         * </pre>
         */
        both {
            @Override
            public void draw(Graphics2D graphics, String text, int width, int x, int y) {
                if (text.length() == 1) {
                    doDrawString(graphics, text, x, y);
                    return;
                }
                FontMetrics metrics = graphics.getFontMetrics();
                char[] chars = text.toCharArray();
                int textLength = chars.length;

                float margin = (float) width / (textLength - 1);
                if (margin < 0) {
                    margin = 0;
                }
                int xTemp = x;
                for (int i = 0; i < textLength; i++) {
                    String singleString = String.valueOf(chars[i]);
                    int fontWidth = metrics.charWidth(chars[i]);
                    int fontSize = metrics.getFont().getSize();
                    float newX;
                    if (i == 0) {
                        newX = x;
                        xTemp += margin;
                    } else if (i == textLength - 1) {
                        newX = width;
                        if (fontWidth < fontSize) {
                            newX += (fontSize - fontWidth) / 2F;
                        }
                    } else {
                        newX = xTemp;
                        if (fontWidth < fontSize) {
                            newX += (fontSize - fontWidth) / 2F;
                        }
                        xTemp += margin;
                    }
                    doDrawString(graphics, singleString, newX, y);
                }
            }
        },
        /**
         * 居中
         */
        center {
            @Override
            public void draw(Graphics2D graphics, String text, int width, int x, int y) {
                int textWidth = getTextWidth(text, graphics.getFontMetrics());
                x = x + (width - textWidth) / 2;
                doDrawString(graphics, text, x, y);
            }
        },
        /**
         * 居右
         */
        right {
            @Override
            public void draw(Graphics2D graphics, String text, int width, int x, int y) {
                int textWidth = getTextWidth(text, graphics.getFontMetrics());
                x = x + (width - textWidth);
                doDrawString(graphics, text, x, y);
            }
        },
        /**
         * 居左
         */
        left {
            @Override
            public void draw(Graphics2D graphics, String text, int width, int x, int y) {
                doDrawString(graphics, text, x, y);
            }
        };

        public static Align from(String align) {
            if (align == null) {
                return null;
            }
            return Align.valueOf(align.toLowerCase());
        }
    }

    public enum VerticalAlign implements VerticalAlignComputer {
        top {
            @Override
            public int compute(int y, int height, int bodyHeight) {
                return y;
            }
        }, center {
            @Override
            public int compute(int y, int height, int bodyHeight) {
                if (height < bodyHeight) {
                    return y;
                }
                return y + (height - bodyHeight) / 2;
            }
        }, bottom {
            @Override
            public int compute(int y, int height, int bodyHeight) {
                if (height < bodyHeight) {
                    return y;
                }
                return y + (height - bodyHeight);
            }
        };


        public static VerticalAlign from(String align) {
            if (align == null) {
                return null;
            }
            return VerticalAlign.valueOf(align.toLowerCase());
        }
    }

    interface TextAlignPainter {
        void draw(Graphics2D graphics, String text, int width, int x, int y);
    }

    interface VerticalAlignComputer {
        int compute(int y, int height, int bodyHeight);
    }


}
