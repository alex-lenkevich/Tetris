package domain;

/**
 * User: alexander.lenkevich
 * Date: 1/11/12
 * Time: 10:51 AM
 */
public enum FigureOrient {
    UP {
        @Override
        public FigureOrient getNext() {
            return RIGHT;
        }
    }, LEFT {
        @Override
        public FigureOrient getNext() {
            return UP;
        }
    }, DOWN {
        @Override
        public FigureOrient getNext() {
            return LEFT;
        }
    }, RIGHT {
        @Override
        public FigureOrient getNext() {
            return DOWN;
        }
    };

    public abstract FigureOrient getNext();
}
