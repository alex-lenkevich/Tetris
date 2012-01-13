package domain;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;

/**
 * User: alexander.lenkevich
 * Date: 1/13/12
 * Time: 6:57 PM
 */
public class ImageAreaInitializer implements AreaInitializer {
    
    private int[][] initData;

    public ImageAreaInitializer(int[][] initData) {
        this.initData = initData;
    }

    @Override
    public void init(Area area) {
        for(int y = 0 ; y <initData.length ; y++){
            for(int x = 0 ; x < initData[y].length ; x++){
                if(initData[y][x] != 0){
                    int ry = initData.length - y - 1;
                    Color color;
                    if(initData[y][x] == 1){
                        int number = (int) Math.round(Math.random() * (FigureType.getFiguresToGen().size() - 1));
                        FigureType figureType = FigureType.getFiguresToGen().get(number);
                        color = figureType.getDefaultColor();
                    } else {
                        color = FigureType.values()[initData[y][x] - 2].getDefaultColor();
                    }
                    area.add(new Figure(domain.FigureType.P, new Point(x, ry), FigureOrient.UP, color));
                }
            }
        }
        area.setWidth(initData[0].length);
    }
}