package com.example.khuta.battleship;


class Game {

    private final int EASY = 10;
    private final int MEDIUM = 14;
    private final int HARD = 18;
    private int boardSize;
    private Board playerBoard;
    private Board computerBoard;
    private String difficulty;
    private boolean isGameOver;


    Game(String difficulty) {

        setDifficulty(difficulty);
        setGameOver(false);

        Board playerBoard = new Board("player", getBoardSize());
        Board computerBoard = new Board("Computer", getBoardSize());

        setPlayerBoard(playerBoard);
        setComputerBoard(computerBoard);

    }



    //return true if it finds a boat at given coordinates else false

    boolean shootsAt(Board board, int x, int y) {

        if (board.grid[x][y] >= 1) {
            int boatID = board.grid[x][y];
            board.grid[x][y] = -1;
            switch (boatID) {
                case 1:  // Shot aircraft
                    board.aircraft.hit();
                    //board.aircraft.map[x][y] = -1;
                    //board.addShipToGrid(board.aircraft.map);
                    board.boardView.gameCoordinates[x][y] = 8; // Set it to 8 to indicate it is a hit
                    getPlayerBoard().shoots(); // Increment counter for # of shots
                    board.boardView.invalidate();
                    board.Hits();
                    if(board.aircraft.getSink())
                        board.updateNumberOfShipsFloating();

                    return true;

                case 2:  // Shot battleship
                    board.battleship.hit();
                    //board.addBoatToGrid(board.battleship.map);
                    board.boardView.gameCoordinates[x][y] = 8; // Set it to 8 to indicate it is a hit
                    getPlayerBoard().shoots(); // Increment counter for # of shots
                    board.boardView.invalidate();
                    board.Hits();
                    if(board.battleship.getSink())
                        board.updateNumberOfShipsFloating();

                    return true;

                case 3:  // Shot destroyer
                    board.destroyer.hit();
                    //board.addBoatToGrid(board.destroyer.map);
                    board.boardView.gameCoordinates[x][y] = 8; // Set it to 8 to indicate it is a hit
                    getPlayerBoard().shoots(); // Increment counter for # of shots
                    board.boardView.invalidate();
                    board.Hits();
                    if(board.destroyer.getSink())
                        board.updateNumberOfShipsFloating();

                    return true;

                case 4:  // Shot submarine
                    board.submarine.hit();
                    //board.addBoatToGrid(board.submarine.map);
                    board.boardView.gameCoordinates[x][y] = 8; // Set it to 8 to indicate it is a hit
                    getPlayerBoard().shoots(); // Increment counter for # of shots
                    board.boardView.invalidate();
                    board.Hits();
                    if(board.submarine.getSink())
                        board.updateNumberOfShipsFloating();

                    return true;

                case 5:  // Shot patrol
                    board.patrol.hit();
                    //board.addBoatToGrid(board.patrol.map);
                    board.boardView.gameCoordinates[x][y] = 8; // Set it to 8 to indicate it is a hit
                    getPlayerBoard().shoots(); // Increment counter for # of shots
                    board.boardView.invalidate();
                    board.Hits();
                    if(board.patrol.getSink())
                        board.updateNumberOfShipsFloating();

                    return true;
            }

        }

        board.grid[x][y] = -1;
        board.boardView.gameCoordinates[x][y] = -9; // Set it to -9 to indicate it is a miss
        board.boardView.invalidate();
        return false;
    }


    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        setBoardSize();
    }

    private void setBoardSize(){
        if(difficulty.equals("EASY"))
            boardSize = EASY;
        else
            if(difficulty.equals("MEDIUM"))
                boardSize = MEDIUM;
            else
                if(difficulty.equals("HARD"))
                    boardSize = HARD;
    }

    private int getBoardSize(){
        return this.boardSize;
    }


    public Board getPlayerBoard() {
        return this.playerBoard;
    }

    public void setPlayerBoard(Board playerBoard) {
        this.playerBoard = playerBoard;
    }

    public Board getComputerBoard() {
        return this.computerBoard;
    }

    public void setComputerBoard(Board computerBoard) {
        this.computerBoard = computerBoard;
    }

    public boolean isGameOver() {
        if(playerBoard.getNumberOfShipsFloating() == 0 || computerBoard.getNumberOfShipsFloating() == 0)
            isGameOver = true;
        return this.isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.isGameOver = gameOver;
    }
}