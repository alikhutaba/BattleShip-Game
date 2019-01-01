package com.example.khuta.battleship;


public class Ship {
    int[][] map ;
    private int shipSize;
    private int mapSize = 10;
    private int hit;
    private boolean sink;
    private String shipName;
    private String position; //vertical-horizontal


    public Ship(int mapSize , String ship , int shipSize){

        setMapSize(mapSize);
        setShipSize(shipSize);
        setSink(false);
        setShipName(ship);
        map = new int[mapSize][mapSize];

    }


    public int[][] randomCoordinates() {
        int coordinatesRange = (map.length - getShipSize());
        int randomX = (int) (Math.random() * coordinatesRange);
        int randomY = (int) (Math.random() * coordinatesRange);
        int direction = (int) (Math.random() * 2);

        if (direction == 1) { // if boat is horizontal
            setPosition("horizontal");
            for (int i = 0; i < getShipSize(); i++) { // place boat horizontal
                if (getShipName().equals("aircraft")) {
                    map[randomX][randomY + i] = 1; // Adding to the right of the head
                }
                if (getShipName().equals("battleship")) {
                    map[randomX][randomY + i] = 2; // Adding to the right of the head
                }
                if (getShipName().equals("destroyer")) {
                    map[randomX][randomY + i] = 3; // Adding to the right of the head
                }
                if (getShipName().equals("submarine")) {
                    map[randomX][randomY + i] = 4; // Adding to the right of the head
                }
                if (getShipName().equals("patrol")) {
                    map[randomX][randomY + i] = 5; // Adding to the right of the head
                }
            }
        } else {
            setPosition("vertical");
            for (int j = 0; j < getShipSize(); j++) { // place boat vertical
                if (getShipName().equals("aircraft")) {
                    map[randomX + j][randomY] = 1; // Adding below of the head
                }
                if (getShipName().equals("battleship")) {
                    map[randomX + j][randomY] = 2; // Adding below of the head
                }
                if (getShipName().equals("destroyer")) {
                    map[randomX + j][randomY] = 3; // Adding below of the head
                }
                if (getShipName().equals("submarine")) {
                    map[randomX + j][randomY] = 4; // Adding below of the head
                }
                if (getShipName().equals("patrol")) {
                    map[randomX + j][randomY] = 5; // Adding below of the head
                }
            }
        }
        return map;
    }


    public int[][] getCoordinates() {
        return map;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public int getShipSize() {
        return shipSize;
    }

    public void setShipSize(int shipSize) {
        this.shipSize = shipSize;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public void setSink(boolean sink) {
        this.sink = sink;
    }
    public boolean getSink(){
        return sink;
    }


    public int getHit() {
        return hit;
    }


    public void hit() {
        hit++;
        if(hit == shipSize)
            setSink(true);
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }


}