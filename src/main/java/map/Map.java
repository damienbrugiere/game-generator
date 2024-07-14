package map;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private long height;
    private long width;
    private Tile[][] map;

    private List<Island> islands = new ArrayList<>();

    public Map(long height, long width, Tile[][] map) {
        this.height = height;
        this.width = width;
        this.map = map;
        this.init();
    }
    public Map(long height, long width){
        this(height, width, new Tile[(int) height][(int) width]);
    }


    public Tile[][] getMap() {
        return map;
    }

    public void displayMap(){
        for(int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.map[x][y].display();
            }
            System.out.println();
        }
    }
    void init(){
        for(int x = 0; x < width; x++){
            for(int y = 0; y < width; y++){
                map[x][y]= new Tile((long) x,(long) y, true, null);
            }
        }
    }

    public Tile getNorth(int x, int y){
        return getTile(x, y-1);
    }
    public  Tile getNorthEast(int x, int y){
        return getTile(x+1,y-1);
    }

    public  Tile getEast(int x, int y){
        return getTile(x+1, y);
    }
    public  Tile getSouthEast(int x, int y){
        return getTile(x+1,y+1);
    }

    public  Tile getSouth(int x, int y){
        return getTile(x,y+1);
    }
    public  Tile getSouthWest(int x, int y){
        return getTile(x-1,y+1);
    }
    public  Tile getWest(int x, int y){
        return getTile(x-1,y);
    }

    public  Tile getNorthWest(int x, int y){
       return getTile(x-1,y-1);
    }

    public Tile getTile(int x, int y){
        if(x < 0 || x >= width || y< 0 || y>= height ){
            return null;
        }
        return map[x][y];
    }

    Tile transformTileToGround(int x, int y, int index){
        this.map[x][y] = new Tile(x,y, false, index);
        return this.getTile(x,y);
    }

    List<Tile> getAllTileNear(int x,int y){
        List<Tile> tiles = new ArrayList<>();
        addIfNotNull(getNorth(x,y), tiles);
        addIfNotNull(getNorthWest(x,y), tiles);
        addIfNotNull(getWest(x,y), tiles);
        addIfNotNull(getSouthWest(x,y), tiles);
        addIfNotNull(getSouth(x,y), tiles);
        addIfNotNull(getSouthEast(x,y), tiles);
        addIfNotNull(getEast(x,y), tiles);
        addIfNotNull(getNorthEast(x,y), tiles);
        return tiles;
    }
    List<Tile> getAllTileNearWithoutNullAndSea(Tile tile){
        List<Tile> tiles = new ArrayList<>();
        addIfNotNullAndNotSea(getNorth(tile.getX(),tile.getY()), tiles);
        addIfNotNullAndNotSea(getNorthWest(tile.getX(),tile.getY()), tiles);
        addIfNotNullAndNotSea(getWest(tile.getX(),tile.getY()), tiles);
        addIfNotNullAndNotSea(getSouthWest(tile.getX(),tile.getY()), tiles);
        addIfNotNullAndNotSea(getSouth(tile.getX(),tile.getY()), tiles);
        addIfNotNullAndNotSea(getSouthEast(tile.getX(),tile.getY()), tiles);
        addIfNotNullAndNotSea(getEast(tile.getX(),tile.getY()), tiles);
        addIfNotNullAndNotSea(getNorthEast(tile.getX(),tile.getY()), tiles);
        return tiles;
    }

    Tile[] getAllTileNearArray(int x,int y){
        Tile[] tiles = new Tile[8];
        tiles[0]=getNorth(x,y);
        tiles[1]=getNorthWest(x,y);
        tiles[2]=getWest(x,y);
        tiles[3]=getSouthWest(x,y);
        tiles[4]=getSouth(x,y);
        tiles[5]=getSouthEast(x,y);
        tiles[6]=getEast(x,y);
        tiles[7]=getNorthEast(x,y);
        return tiles;
    }


    boolean isTooCloseToIslands(Tile tile){
        return this.islands.stream().anyMatch(island -> !island.isTileSeparated(tile));
    }

    private void addIfNotNull(Tile tile, List<Tile> tiles){
        if(tile == null){
            return;
        }
        tiles.add(tile);
    }
    private void addIfNotNullAndNotSea(Tile tile, List<Tile> tiles){
        if(tile == null || !tile.isBlock()){
            return;
        }
        tiles.add(tile);
    }

    void addIsland(Island island){
        this.islands.add(island);
    }

    public List<Island> getIslands() {
        return islands;
    }
}
