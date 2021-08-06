package java11.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static final String RESET, RED_BOLD, RED, GREEN_BOLD, YELLOW, PURPLE;
    static {
        RESET = "\033[0m";
        RED_BOLD = "\033[1;31m";
        RED = "\033[0;31m";
        GREEN_BOLD = "\033[1;32m";
        YELLOW = "\033[0;33m";
        PURPLE = "\033[0;35m";
    }

    private static final Scanner scan = new Scanner(System.in);
    private static boolean stop = true;
    public static Character[][] modelGameBoard = {
            {'+','-','-','-','+','-','-','-','+','-','-','-','+'},
            {'|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|'},
            {'+','-','-','-','+','-','-','-','+','-','-','-','+'},
            {'|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|'},
            {'+','-','-','-','+','-','-','-','+','-','-','-','+'},
            {'|',' ',' ',' ','|',' ',' ',' ','|',' ',' ',' ','|'},
            {'+','-','-','-','+','-','-','-','+','-','-','-','+'},
    };

    public static final ArrayList<Integer> savePlayerNumberPosition = new ArrayList<>();
    public static final ArrayList<Integer> saveComputerNumberPosition = new ArrayList<>();

    private static final String PLAYER = "PLAYER";
    private static final String COMPUTER = "COMPUTER";


    /*--------------------------------------[START: Flow Business Logic]--------------------------------------*/
    /**
     * @desc logic untuk mencetak board game
     */
    public static void printGameBoard() {
        for (Character[] characters : modelGameBoard) {
            System.out.print("\t ");
            for (var column = 0; column < 13; column++) {
                System.out.print(YELLOW+ characters[column]+ RESET);
            }
            System.out.print("\n");
        }
    }

    /**
     * @desc logic untuk player memilih position
     * @param numberPosition menerima argument nomor posisi pilihan player
     */
    public static boolean playerChoosePosition(Integer numberPosition) {
        return numberPosition >= 1 && numberPosition <= 9;
    }

    /**
     * @desc logic untuk computer memilih position random
     * @return int
     */
    public static Integer computerChoosePosition() {
        Random random = new Random();
        return random.nextInt(9) + 1;
    }

    /**
     * @desc logic untuk memasukkan pilihan position player & computer didalam modelGameBoard
     * @param user untuk menentukan pilihan X atau O
     * @param userPosition menerima argument pilihan posisi player dan computer
     */
    public static void inputChoosePosition(String user, Integer userPosition) {
        Character symbol = null;
        if (user.equals("PLAYER")) {
            symbol = 'X';
           /*logic setiap player memilih nomor posisi akan disimpan sebagai list*/
            savePlayerNumberPosition.add(userPosition); //[3,5,1]
        }
        if (user.equals("COMPUTER")) {
            symbol = 'O';
            /*logic setiap computer memilih nomor posisi akan disimpan sebagai list*/
            saveComputerNumberPosition.add(userPosition);//[2,7,4]
        }

        switch (userPosition) {
            case 1: modelGameBoard[1][2] = symbol; break;
            case 2: modelGameBoard[1][6] = symbol; break;
            case 3: modelGameBoard[1][10] = symbol; break;
            case 4: modelGameBoard[3][2] = symbol; break;
            case 5: modelGameBoard[3][6] = symbol; break;
            case 6: modelGameBoard[3][10] = symbol; break;
            case 7: modelGameBoard[5][2] = symbol; break;
            case 8: modelGameBoard[5][6] = symbol; break;
            case 9: modelGameBoard[5][10] = symbol; break;
            default: break;
        }
    }

    /**
     * @desc logic untuk mengetahui siapa pemenang, apakah pemain | komputer
     */
    public static String checkWinner() {
        /*kumpulkan setiap list jalur kemenangan didalam arraylist*/
        ArrayList<List> linePositionWinning = new ArrayList<>();

        /*tentukan jalur kemenangan dengan menyimpan setiap jalur dalam list tictactoe*/
        List<Integer> rowsTop = List.of(1,2,3);
        List<Integer> rowsMid = List.of(4,5,6);
        List<Integer> rowsBottom = List.of(7,8,9);
        List<Integer> colLeft = List.of(1,4,7);
        List<Integer> colMid = List.of(2,5,8);
        List<Integer> colRight = List.of(3,6,9);
        List<Integer> cross1 = List.of(1,5,9);
        List<Integer> cross2 = List.of(3,5,7);

        linePositionWinning.add(rowsTop);
        linePositionWinning.add(rowsMid);
        linePositionWinning.add(rowsBottom);
        linePositionWinning.add(colLeft);
        linePositionWinning.add(colMid);
        linePositionWinning.add(colRight);
        linePositionWinning.add(cross1);
        linePositionWinning.add(cross2);

        for (var i = 0; i < linePositionWinning.size(); i++) {
            if (savePlayerNumberPosition.containsAll(linePositionWinning.get(i))) {
                stop = false;
                return PURPLE+"Congratulation You Won..!"+RESET;
            }else if (saveComputerNumberPosition.containsAll(linePositionWinning.get(i))) {
                stop = false;
                return PURPLE+"Computer Won..!"+RESET;
            }else if (savePlayerNumberPosition.size() + saveComputerNumberPosition.size() == 9) {
                return PURPLE+"Series!"+RESET;
            }
        }

        return "";
    }
    /*--------------------------------------[END: Flow Business Logic]----------------------------------------*/


    /*-----------------------------------------[START: Flow View]---------------------------------------------*/
    /**
     * @desc merupakan view utama
     */
    public static void viewMain() {
        viewTicTacToe();

        while (stop) {
            /*player dan computer memilih nomor posisi*/
            var player = viewPlayerChoose();
            var computer = computerChoosePosition();

            if (savePlayerNumberPosition.size() == 4) {
                inputChoosePosition(PLAYER, player);
                viewTicTacToe();
                System.out.println(checkWinner());
                break;
            }

            /*selama player memilih nomor posisi yang sama dengan nomor posisi computer*/
            while (savePlayerNumberPosition.contains(player) || saveComputerNumberPosition.contains(player)) {
                System.out.println(RED_BOLD+"Take Another Position!!"+RESET);
                player = viewPlayerChoose();
            }
            inputChoosePosition(PLAYER, player);

            /*selama computer memilih nomor posisi yang sama dengan player maka ulangi*/
            while (savePlayerNumberPosition.contains(computer) || saveComputerNumberPosition.contains(computer)) {
                computer = computerChoosePosition();
            }
            inputChoosePosition(COMPUTER, computer);


            viewTicTacToe();
            String result = checkWinner();
            System.out.println(result);
        }
    }

    /**
     * @desc ini adalah view display tictactoe
     */
    public static void viewTicTacToe() {
        System.out.println(GREEN_BOLD+"\t<< TICTACTOE >>"+RESET);
        printGameBoard();
    }

    /**
     * @desc ini adalah view untuk player memilih posisi
     */
    public static Integer viewPlayerChoose() {
        System.out.print("Choose Number ("+RED+"1-9"+RESET+") : ");
        Integer numberPosition = scan.nextInt();

        var valid = playerChoosePosition(numberPosition);
        if (!valid) {
            System.out.println(RED_BOLD+"You Choose Position Number "+numberPosition+" Not Found!\n"+RESET);
            stop = false;
        }
        System.out.println("\n");
        return  numberPosition;
    }
    /*------------------------------------------[END: Flow View]----------------------------------------------*/


    /*Main Program*/
    public static void main(String[] args) {
        viewMain();
    }
}
