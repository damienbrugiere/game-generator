import map.Map;
import map.MapGenerator;

public class Main {

    public static void main(String[] args) {
        Map map = MapGenerator.builder().withHeight(100).withWidth(100).generate();
        map.displayMap();
        System.out.println(map.getIslands().get(0).size());
    }
}
