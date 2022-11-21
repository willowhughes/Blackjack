import java.util.*;
import java.io.*;
import java.util.stream.*;

public class rpgblackjack {
    public static void main (String[] args) {
        //players gold will be passed in when implemented
        int gold = 1000;
        int loop = 1;

        System.out.println("Welcome to blackjack!");
        System.out.println("You have " + gold + " gold");
        while (loop == 1) {
            if (gold < 20) {
                System.out.println("Sorry, you need at least 20 gold to play");
                loop = 0;
            } else {
                int currentCard = 0;
                rpgblackjackdeck d = new rpgblackjackdeck();
                int[] hand = new int[30];
                int handTotal = 0;
                int[] dealerhand = new int[30];
                int dealerhandTotal = 0;

                hand[0] = d.deck.pop();
                handTotal += hand[0];
                hand[1] = d.deck.pop();
                handTotal += hand[1];

                dealerhand[0] = d.deck.pop();
                dealerhandTotal += dealerhand[0];
                dealerhand[1] = d.deck.pop();
                dealerhandTotal += dealerhand[1];

                int loop2 = 1;
                int bet = 0;
                Scanner console = new Scanner(System.in);
                String answer = "";
                while (loop2 == 1) {
                    System.out.println("How much would you like to bet?");
                    answer = console.next();
                    bet = Integer.parseInt(answer);
                    if (bet > gold) {
                        System.out.println("You can't bet more than you own");
                    } else {
                        loop2 = 0;
                    }
                }
                gold = gold - bet;


                System.out.println("your starting cards are " + hand[0] + " and " + hand[1]);
                System.out.println("hand total: " + handTotal);
                System.out.println("dealers starting cards are: " + dealerhand[0] + "[hidden]");

                handTotal = deal(d, hand, currentCard, handTotal, d.deck);

                System.out.println("hand total: " + handTotal);
                if (handTotal > 21) {
                    System.out.println("YOU LOSE");
                } else if (handTotal == 21) {
                    System.out.println("YOU WIN");
                    bet = bet + (bet / 2);
                    System.out.println("+" + bet);
                    gold = gold + bet;
                } else {
                    System.out.println("dealers starting cards were: " + dealerhand[0] + " and " + dealerhand[1]);
                    if ((dealerhand[0] + dealerhand[1]) < 16) {
                        dealerhand[3] = d.deck.pop();
                        dealerhandTotal += dealerhand[3];
                        System.out.println("dealers cards sum to under 16 so another card is drawn..." + dealerhand[3]);
                    }
                    System.out.println("dealer's total: " + dealerhandTotal);
                    if (dealerhandTotal > 21) {
                        System.out.println("YOU WIN");
                        System.out.println("+" + bet / 2);
                        bet = bet + (bet / 2);
                        gold = gold + bet;
                    } else if (dealerhandTotal == 21) {
                        System.out.println("YOU LOSE");
                        System.out.println("-" + bet);
                    } else {
                        if (handTotal > dealerhandTotal) {
                            System.out.println("YOU WIN");
                            System.out.println("+" + bet / 2);
                            bet = bet + (bet / 2);
                            gold = gold + bet;
                        } else if (handTotal == dealerhandTotal) {
                            System.out.println("YOU TIED");
                            gold = gold + bet;
                        } else {
                            System.out.println("YOU LOSE");
                            System.out.println("-" + bet);
                        }
                    }
                }
                System.out.println("You have " + gold + " gold");
                System.out.println("Would you like to play again?");
                answer = console.next();
                if (answer.charAt(0) == 'y' || answer.charAt(0) == 'Y') {
                    loop = 1;
                } else {
                    loop = 0;
                }
            }
        }
        System.out.println("Thanks for playing!\nYou have " + gold + " gold");
        //return gold;
    }

    public static int deal(rpgblackjackdeck d, int[] hand, int currentCard, int handTotal, Stack<Integer> stackdeck) {

        int loop = 1;

        System.out.println("Press 'h' to hit or any key to stay");
        while (loop == 1) {
            Scanner console = new Scanner(System.in);
            String answer = console.next();
            if(answer.charAt(0) == 'h' || answer.charAt(0) == 'H') {
                hand[currentCard] = stackdeck.pop();
                System.out.println(hand[currentCard]);
                handTotal += hand[currentCard];
                currentCard++;
                System.out.println("hand total: " + handTotal);
                currentCard++;
                if (handTotal > 21) {
                    loop = 0;
                } else {
                    loop = 1;
                }
                System.out.println("another card? Press 'h' to hit or any key to stay");
            } else {
                loop = 0;
            }
        }
        return handTotal;
    }
}