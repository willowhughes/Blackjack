import java.util.*;
import java.io.*;

public class rpgblackjackdeck {

    Random rand = new Random();
    public Stack<Integer> deck = new Stack<Integer>();

    public rpgblackjackdeck() {

        int[] cardValues = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10};
        for (int count = 0; count < 52; count++) {
            int random = rand.nextInt(12);
            deck.push(cardValues[random]);
        }
    }
}
