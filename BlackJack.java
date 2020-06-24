
import java.util.Scanner;

public class BlackJack {

    public static void main(String[] args) {

        String Card[] = {"Spade:Ace", "Spade:02", "Spade:03", "Spade:04", "Spade:05", "Spade:06", "Spade:07", "Spade:08", "Spade:09", "Spade:10", "Spade:Jack", "Spade:Queen", "Spade:King",
            "Heart:Ace", "Heart:02", "Heart:03", "Heart:04", "Heart:05", "Heart:06", "Heart:07", "Heart:08", "Heart:09", "Heart:10", "Heart:Jack", "Heart:Queen", "Heart:King",
            "Club:Ace", "Club:02", "Club:03", "Club:04", "Club:05", "Club:06", "Club:07", "Club:08", "Club:09", "Club:10", "Club:Jack", "Club:Queen", "Club:King",
            "Diamond:Ace", "Diamond:02", "Diamond:03", "Diamond:04", "Diamond:05", "Diamond:06", "Diamond:07", "Diamond:08", "Diamond:09", "Diamond:10", "Diamond:Jack", "Diamond:Queen", "Diamond:King"
        };
        int Sort[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52};
        int test, whichcard;                                      //test mode
        int cardno = 1;
        int testdep[] = new int[52];
        boolean taken[] = new boolean[52];
        System.out.print("Go to Test Mode ?(0-No, 1-Yes):");
        Scanner scan = new Scanner(System.in);
        test = scan.nextInt();
        System.out.println();
        while (test != 0 && test != 1) {
            System.out.print("You must input 0 or 1!");
            System.out.print("\nGo to Test Mode ?(0-No, 1-Yes):");
            test = scan.nextInt();
        }
        if (test == 1) {

            do {
                System.out.print("Input Card" + cardno + " in your deck (0 to end): ");
                whichcard = scan.nextInt();
                while (whichcard > 52 || whichcard < 0) {
                    System.out.println("you must input in the range of 1 to 52");
                    System.out.print("Input Card" + cardno + " in your deck (0 to end): ");
                    whichcard = scan.nextInt();
                }
                if (whichcard != 0) {
                    while (taken[whichcard - 1] == true) {
                        System.out.println("the card has taken,please input anothoer card");
                        System.out.print("Input Card" + cardno + " in your deck (0 to end): ");
                        whichcard = scan.nextInt();
                    }
                    testdep[cardno - 1] = whichcard - 1;
                    taken[whichcard - 1] = true;
                    cardno++;
                }
            } while (whichcard != 0);

        }
        int player;
        int stated = 0;
        System.out.print("How many players?");
        player = scan.nextInt();
        while (player <= 0) {            //ensure that player exist 
            System.out.println("Number of players must be greater than or equal to 1");
            System.out.print("How many players?");
            player = scan.nextInt();
        }
        while (player >= 26) {            //ensure that every player have at least 2 card
            System.out.println("Number of players must not be greater than 25");
            System.out.print("How many players?");
            player = scan.nextInt();
        }
        String temp;
        if (test != 1) {
            int tem;
            int a = 0;
            while (a < Card.length) {           //shuffle the card 
                int b = (int) (Math.random() * 52);
                temp = Card[b];                  //swipe the card
                Card[b] = Card[a];
                Card[a] = temp;
                tem = Sort[b];                  //swipe the sort
                Sort[b] = Sort[a];
                Sort[a] = tem;
                a++;
            }
        }
        System.out.println("\n\nGame Start! (" + player + " players)\n======================================");
        int x = 0, y = 1;

        while (y <= player + 1) {         //print out the game start round
            while (y <= player) {
                if (test == 0) {
                    System.out.println("Player " + y + "'s Hand: [  Unknown " + Card[x + player + 1] + "  ]");
                } else {
                    System.out.println("Player " + y + "'s Hand: [  Unknown " + Card[testdep[x + player + 1]] + "  ]");
                }
                x++;
                y++;
            }
            if (test == 1) {
                System.out.println("Dealer's Hand: [  Unknown  " + Card[testdep[(player * 2) + 1]] + "  ]");
            } else {
                System.out.println("Dealer's Hand: [  Unknown  " + Card[(player * 2) + 1] + "  ]");
            }
            y += 2;
        }
        int m = 0, n = 1, pcinfo = 0;
        int hit;
        String playerinfo[] = new String[player];
        String blacket = "]";
        int nextcard = (player + 1) * 2;
        int totalpoint[] = new int[player + 1]; // playrt total point
        System.out.println("\n\nPlayers' Round (" + player + " players)\n======================================");
        String sentence;
        int ace[] = new int[player + 1];
        boolean status[] = new boolean[player + 1];
        while (n <= player) {
            if (test == 0) {
                System.out.print("Player " + n + "'s Hand: [  " + Card[m] + "  " + Card[m + player + 2] + "  " + blacket);
                sentence = ("Player " + n + "'s Hand: [  " + Card[m] + "  " + Card[m + player + 2] + "  ");
                if (Sort[m] % 13 == 1) {
                    ace[n - 1]++;
                }
                if (Sort[m + player + 2] % 13 == 1) {
                    ace[n - 1]++;
                }
                totalpoint[n] = totalpoint[n] + getPoint(Sort[m]) + getPoint(Sort[m + player + 2]);//count the point of first 2 card
                while (totalpoint[n] > 21 && ace[n - 1] > 0) {
                    totalpoint[n] = totalpoint[n] - 10;
                    ace[n - 1]--;
                }
            } else {
                System.out.print("Player " + n + "'s Hand: [  " + Card[testdep[m]] + "  " + Card[testdep[m + player + 1]] + "  " + blacket);
                sentence = ("Player " + n + "'s Hand: [  " + Card[testdep[m]] + "  " + Card[testdep[m + player + 1]] + "  ");
                if (Sort[testdep[m]] % 13 == 1) {
                    ace[n - 1]++;
                }
                if (Sort[testdep[m + player + 1] + 1] % 13 == 1) {
                    ace[n - 1]++;
                }
                totalpoint[n] = totalpoint[n] + getPoint(Sort[testdep[m]]) + getPoint(Sort[testdep[m + player + 1]]);      //count the point of first 2 card  
                while (totalpoint[n] > 21 && ace[n - 1] > 0) {
                    totalpoint[n] = totalpoint[n] - 10;
                    ace[n - 1]--;
                }
            }
            if (totalpoint[n] >= 21) {
                stated++;
                status[n] = true;
            }

            while (totalpoint[n] < 21) {
                System.out.print("\nPlayer " + n + ", do you want to Stand or Hit (0-Stand, 1-Hit)?");
                hit = scan.nextInt();
                if (hit > 1 || hit < 0) {
                    System.out.print("\nYou must input 0 or 1!");
                } else if (hit == 0) {
                    break;
                } else if (hit == 1) {
                    if (test == 0) {
                        System.out.print(sentence + " " + Card[nextcard] + "  " + blacket);
                        if (Sort[nextcard] % 13 == 1) {
                            ace[n - 1]++;
                        }
                        totalpoint[n] = totalpoint[n] + getPoint(Sort[nextcard]);
                        while (totalpoint[n] > 21 && ace[n - 1] > 0) {
                            totalpoint[n] = totalpoint[n] - 10;
                            ace[n - 1]--;
                        }
                        //System.out.printl(totalpoint[n]);
                        sentence = (sentence + " " + Card[nextcard] + " ");
                        nextcard++;
                    } else if (test == 1) {
                        System.out.print(sentence + " " + Card[testdep[nextcard]] + "  " + blacket);
                        if (Sort[testdep[nextcard]] % 13 == 1) {
                            ace[n - 1]++;
                        }
                        totalpoint[n] = totalpoint[n] + getPoint(Sort[testdep[nextcard]]);
                        while (totalpoint[n] > 21 && ace[n - 1] > 0) {
                            totalpoint[n] = totalpoint[n] - 10;
                            ace[n - 1]--;
                        }
                        System.out.println(totalpoint[n]);
                        sentence = (sentence + " " + Card[testdep[nextcard]] + " ");
                        nextcard++;
                    }
                }
                if (totalpoint[n] > 21) {
                    System.out.println(chkStatus(totalpoint[n]));
                    stated++;
                    status[n] = true;
                } else if (totalpoint[n] == 21) {
                    System.out.println("");
                }
            }
            playerinfo[pcinfo] = (sentence + " " + blacket + chkStatus(totalpoint[n]));
            pcinfo++;
            m++;
            n++;

        }
        System.out.println("\n\nDealers' Round (" + player + " players)\n======================================");           //dealer round
        String dsentence = null;
        if (stated == player) {
            System.out.println("All players have won or lost the game !");
            if (test == 0) {
                totalpoint[player] = 0;
                dsentence = ("Dealer's Hand: [  " + Card[player + 1] + "  " + Card[(player + 1) * 2] + "  ");
                if (Sort[player + 1] % 13 == 1) {
                    ace[player]++;
                }
                if (Sort[(player + 1) * 2] % 13 == 1) {
                    ace[player]++;
                }
                totalpoint[player] = totalpoint[player] + getPoint(Sort[player + 1]) + getPoint(Sort[(player + 1) * 2]);
                while (totalpoint[player] > 21 && ace[n - 1] > 0) {
                    totalpoint[player] = totalpoint[player] - 10;
                    ace[n - 1]--;
                }
            } else if (test == 1) {
                totalpoint[player] = 0;
                dsentence = ("Dealer's Hand: [  " + Card[testdep[player]] + "  " + Card[testdep[(player * 2) + 1]] + "  ");

                if (Sort[testdep[player]] % 13 == 1) {
                    ace[player]++;
                }
                if (Sort[testdep[(player * 2) + 1]] % 13 == 1) {
                    ace[player]++;
                }
                totalpoint[player] = totalpoint[player] + getPoint(Sort[testdep[player]]) + getPoint(Sort[testdep[(player * 2) + 1]]);
                while (totalpoint[player] > 21 && ace[n - 1] > 0) {
                    totalpoint[player] = totalpoint[player] - 10;
                    ace[n - 1]--;
                }
            }
        } else {
            for (int u = 0; u < player; u++) {
                System.out.println(playerinfo[u]);
            }

            if (test == 0) {
                System.out.println("Dealer's Hand: [  " + Card[player + 1] + "  " + Card[(player + 1) * 2] + "  " + blacket);
                totalpoint[player] = 0;

                if (Sort[player + 1] % 13 == 1) {
                    ace[player]++;
                }
                if (Sort[(player + 1) * 2] % 13 == 1) {
                    ace[player]++;
                }
                totalpoint[player] = totalpoint[player] + getPoint(Sort[player + 1]) + getPoint(Sort[(player + 1) * 2]);
                while (totalpoint[player] > 21 && ace[n - 1] > 0) {
                    totalpoint[player] = totalpoint[player] - 10;
                    ace[n - 1]--;
                }
                dsentence = ("Dealer's Hand: [  " + Card[player + 1] + "  " + Card[(player + 1) * 2] + "  ");
            } else if (test == 1) {
                System.out.println("Dealer's Hand: [  " + Card[testdep[player]] + "  " + Card[testdep[(player * 2) + 1]] + "  " + blacket);
                totalpoint[player] = 0;
                ;
                if (Sort[testdep[player]] % 13 == 1) {
                    ace[player]++;
                }
                if (Sort[testdep[(player * 2) + 1]] % 13 == 1) {
                    ace[player]++;
                }
                totalpoint[player] = totalpoint[player] + getPoint(Sort[testdep[player]]) + getPoint(Sort[testdep[(player * 2) + 1]]);
                while (totalpoint[player] > 21 && ace[n - 1] > 0) {
                    totalpoint[player] = totalpoint[player] - 10;
                    ace[n - 1]--;
                }
                dsentence = ("Dealer's Hand: [  " + Card[testdep[player]] + "  " + Card[testdep[(player * 2) + 1]] + "  ");

            }
            while (totalpoint[player] < 17) {
                if (test == 0) {
                    System.out.print("Lower than 17, add new cards");
                    System.out.print("\n" + dsentence + " " + Card[nextcard] + "  " + blacket);
                    if (Sort[nextcard] % 13 == 1) {
                        ace[player]++;
                    }
                    totalpoint[player] = totalpoint[player] + getPoint(Sort[nextcard]);
                    while (totalpoint[player] > 21 && ace[n - 1] > 0) {
                        totalpoint[player] = totalpoint[player] - 10;
                        ace[n - 1]--;
                    }
                    dsentence = (dsentence + " " + Card[nextcard] + " ");
                    nextcard++;
                } else if (test == 1) {
                    System.out.print("Lower than 17, add new cards");
                    System.out.print("\n" + dsentence + " " + Card[testdep[nextcard]] + "  " + blacket);

                    if (Sort[testdep[nextcard]] % 13 == 1) {
                        ace[player]++;
                    }
                    totalpoint[player] = totalpoint[player] + getPoint(Sort[testdep[nextcard]]);
                    while (totalpoint[player] > 21 && ace[n - 1] > 0) {
                        totalpoint[player] = totalpoint[player] - 10;
                        ace[n - 1]--;
                    }
                    dsentence = (dsentence + " " + Card[testdep[nextcard]] + " ");
                    nextcard++;
                }
            }
            if (totalpoint[player] > 21) {
                System.out.println(chkStatus(totalpoint[player]));
            }
        }
        System.out.println("\n\nFinal Reesult (" + player + " players)\n======================================");    //   final resault
        for (int u = 0; u < player; u++) {                          // player card info
            System.out.print("\n" + playerinfo[u]);
            if (status[u + 1] == false) {
                chkStatus(totalpoint[u + 1], totalpoint[player]);
            }

        }
        System.out.println("");
        System.out.println(dsentence + " " + blacket + chkStatus(totalpoint[player]));      //dealer card info

    }

    public static int getPoint(int pt) {
        int point = 0;
        if (pt % 13 >= 10 || pt % 13 == 0) {
            point = 10;
        } else if (pt % 13 == 1) {
            point = 11;
        } else {
            point = pt % 13;
        }
        return point;
    }

    public static String chkStatus(int chk) {
        String status = null;

        if (chk > 21) {
            status = " Bust!";
        } else if (chk == 21) {
            status = " BlackJack!";
        } else if (chk < 21) {
            status = " ";
        }
        return status;
    }

    public static void chkStatus(int player, int delaer) {
        if (player > delaer) {
            System.out.print("Win!");
        } else if (player == delaer) {
            System.out.print("Push!");
        } else if (player < delaer) {
            System.out.print("Lose!");
        }
    }
}
