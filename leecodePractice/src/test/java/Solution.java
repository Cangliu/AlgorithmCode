import com.LeecodeApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = LeecodeApplication.class)
public class Solution {


    @Test
    public void oddCells() {
        int m = 2;
        int n = 3;
        int[][] indices = new int[][]{new int[]{0,1},new int[]{1,1}};

        List<List<Integer>> matrix= new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            List<Integer> vector = new ArrayList<>(n);
            for (int j = 0; j < n; j++){
                vector.add(0);
            }
            matrix.add(vector);
        }

        for (int[] index : indices) {
            horizontalAdd(matrix, index[0]);
            perpendicularAdd(matrix, index[1]);
        }

        System.out.println(getOdds(matrix));

    }

    private void horizontalAdd(List<List<Integer>> matrix, Integer position){
        for (int i = 0; i < matrix.get(position).size(); i++) {
            matrix.get(position).set(i, matrix.get(position).get(i) + 1);
        }
    }

    private void perpendicularAdd(List<List<Integer>> matrix, Integer position){
        for (List<Integer> integers : matrix) {
            Integer integer = integers.get(position);
            integers.set(position, ++integer);
        }
    }

    private Integer getOdds(List<List<Integer>> matrix){
        Integer odds = 0;
        for (List<Integer> integers : matrix) {
            for (Integer integer : integers) {
                if(integer % 2 != 0)
                odds++;
            }
        }
        return odds;
    }


    @Test
    public void test(){
        String[] strings = {"apple"};
        WordFilter wordFilter = new WordFilter(strings);
        System.out.println(wordFilter.trie);
    }
}
