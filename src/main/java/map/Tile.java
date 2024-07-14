package map;

import java.util.Objects;

public class Tile {
    private long x,y;
    private boolean block;

    private Integer island;
    public Tile(long x, long y, boolean block, Integer island) {
        this.x = x;
        this.y = y;
        this.block = block;
        this.island = island;
    }

    public int getY() {
        return (int) y;
    }

    public int getX() {
        return (int) x;
    }

    public boolean isBlock() {
        return block;
    }

    public void setIsland(int island) {
        this.island = island;
    }

    public void display(){
        if (block) {
            System.out.print("M");
        } else {
            System.out.print(island);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return x == tile.x && y == tile.y && block == tile.block && Objects.equals(island, tile.island);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, block, island);
    }
}
