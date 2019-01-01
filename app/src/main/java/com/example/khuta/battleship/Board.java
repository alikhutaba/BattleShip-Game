package com.example.khuta.battleship;


class Board {

    int[][] grid;
    private int gridSize;
    private int numberOfShipsFloating;
    private String typeOfPlayer;
    private int numberOfShots = 0;
    private int numberOfHits = 0;

    BoardView boardView;

    Ship aircraft;
    Ship battleship;
    Ship destroyer;
    Ship submarine;
    Ship patrol;


    Board(String player , int gridSize) {

        setTypeOfPlayer(player);
        setGridSize(gridSize);

        grid = new int[gridSize][gridSize];

        aircraft = new Ship(this.gridSize ,"aircraft", 5);
        battleship = new Ship(this.gridSize,"battleship", 4);
        destroyer = new Ship(this.gridSize, "destroyer",3);
        submarine = new Ship(this.gridSize, "submarine",3);
        patrol = new Ship(this.gridSize, "patrol" , 2);

        addShipToGrid(aircraft.randomCoordinates());
        addShipToGrid(battleship.randomCoordinates());
        addShipToGrid(destroyer.randomCoordinates());
        addShipToGrid(submarine.randomCoordinates());
        addShipToGrid(patrol.randomCoordinates());

        setNumberOfShipsFloating(5);

    }


    int[][] readBoatCoordinates() {
        return grid;
    }

    void addShipToGrid(int[][] coordinates) {
        for (int i = 0; i < coordinates.length; i++) {
            for (int j = 0; j < coordinates.length; j++) {
                if (coordinates[i][j] == 1) { // Aircraft ID
                    grid[i][j] = 1;
                }
                if (coordinates[i][j] == 2) { // Battleship ID
                    grid[i][j] = 2;
                }
                if (coordinates[i][j] == 3) { // Destroyer ID
                    grid[i][j] = 3;
                }
                if (coordinates[i][j] == 4) { // Submarine ID
                    grid[i][j] = 4;
                }
                if (coordinates[i][j] == 5) { // Patrol ID
                    grid[i][j] = 5;
                }
            }
        }
    }


    public void setGridSize(int gridSize){
        this.gridSize = gridSize;
    }

    public int getGridSize(){
        return this.gridSize;
    }


    String getTypeOfPlayer() {
        return typeOfPlayer;
    }

    private void setTypeOfPlayer(String typeOfPlayer) {
        this.typeOfPlayer = typeOfPlayer;
    }


    public int getNumberOfShots() {
        return numberOfShots;
    }


    void shoots() {
        this.numberOfShots = numberOfShots + 1;
    }

    public int getNumberOfShipsFloating() {
        return numberOfShipsFloating;
    }

    public void setNumberOfShipsFloating(int numberOfShipsFloating) {
        this.numberOfShipsFloating = numberOfShipsFloating;
    }
    public void updateNumberOfShipsFloating() {
        this.numberOfShipsFloating = numberOfShipsFloating - 1;
    }


    public int getnumberOfHits() {
        return numberOfHits;
    }

    public void Hits() {
        this.numberOfHits = numberOfHits + 1;
    }

}
