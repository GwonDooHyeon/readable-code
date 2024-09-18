package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.gamelevel.VeryBeginner;
import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutputHandler;

public class GameApplication {

    public static void main(String[] args) {
        GameLevel gameLevel = new VeryBeginner();
        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutputHandler();

        Minesweeper minesweeper = new Minesweeper(gameLevel, inputHandler, outputHandler);
        minesweeper.initialize();
        minesweeper.run();
    }

    /**
     * DIP (Dependency Inversion Principle)
     * - 고수준 모듈은 저수준 모듈에 의존해서는 안된다.
     *  대신, 둘 다 추상화에 의존하여야 한다.
     * - 추상화는 구체적인 것에 의존하지 말아야 한다.
     *  구체적인 클래스가 아닌, 추상화 된 인터페이스나 클래스를 사용해야 한다.
     *
     * DI (Dependency Injection) - 의존성 주입
     *
     * IOC(Inversion of Control) - 제어의 역전
     *
     */

}
