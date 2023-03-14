import com.LeecodeApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest(classes = LeecodeApplication.class)
public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> finalList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int asteroid : asteroids) {
            list.add(asteroid);
        }
        collision(list, finalList, asteroids);

        int[] ints = new int[finalList.size()];

        for (int i = 0; i < finalList.size(); i++) {
            ints[i] = finalList.get(i);
        }

        return ints;
    }

    public void collision(List<Integer> list, List<Integer> finalList, int[] asteroids){
        List<Integer> index = new ArrayList<>();
        int max = 0;
        int min = 0;
        for (int asteroid : list) {
            if (asteroid > max) {
                max = asteroid;
            }
            if (asteroid < min) {
                min = asteroid;
            }
        }
        if(max == 0 || min == 0){
            finalList.addAll(list);
        }
        int fin = max > -min ? min : max;

        for(int i = 0; i < list.size(); i++){
            if(Math.abs(list.get(i)) >= Math.abs(fin)){
                index.add(i);
            }
        }
        
        if(index.size() == 1){
            finalList.add(list.get(index.get(0)));
        }else if(index.size() == 0){
            for (Integer integer : list) {
                if(integer * fin > 0){
                    finalList.add(integer);
                }
            }
        }else{
            for (int i = 0; i < index.size(); i++) {
                if(i == index.size() - 1){
                    List<Integer> subList = list.subList(index.get(i), list.size());
                    collision(subList, finalList, asteroids);
                }else if(index.get(i + 1) - index.get(i) > 1){
                    List<Integer> subList = list.subList(index.get(i), index.get(i + 1));
                    collision(subList, finalList, asteroids);
                }
                else{
                    if(asteroids[i] != -fin){
                        finalList.add(asteroids[i]);
                    }
                }
            }
        }
    }

    @Test
    public void test(){
        int[] ints = {5,10,-5};
        System.out.println(Arrays.toString(asteroidCollision(ints)));;
    }
}
