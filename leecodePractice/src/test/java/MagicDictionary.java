import com.LeecodeApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = LeecodeApplication.class)
public class MagicDictionary {

    private final Map<Integer, List<String>> dictionaryMap;

    public MagicDictionary() {
        this.dictionaryMap = new HashMap<>();
    }

    public void buildDict(String[] dictionary) {
        for(String dic : dictionary){
            int dicLength = dic.length();
            List<String> list = dictionaryMap.get(dicLength);
            if(list == null || list.isEmpty()){
                list = new ArrayList<>();
            }
            list.add(dic);
            dictionaryMap.put(dicLength, list);
        }
    }

    public boolean search(String searchWord) {
        int length = searchWord.length();
        List<String> list = dictionaryMap.get(length);
        if(list == null || list.isEmpty()){
            return false;
        }else{
            int diff = 0;
            for (String s : list) {
                if(diff == 1){
                    return true;
                }
                diff = 0;
                for(int i = 0; i < length; i++){
                    if(s.charAt(i) != searchWord.charAt(i)){
                        diff ++;
                    }
                    if(diff > 1){
                        break;
                    }
                }
            }
            return diff == 1;
        }
    }

    @Test
    public void test(){
        MagicDictionary magicDictionary = new MagicDictionary();
        String[] strings = {"hello","hallo","leetcode","judge"};
        magicDictionary.buildDict(strings);
        System.out.println(magicDictionary.search("hello"));
        System.out.println(magicDictionary.search("hallo"));
        System.out.println(magicDictionary.search("hell"));
        System.out.println(magicDictionary.search("leetcoded"));
        System.out.println(magicDictionary.search("aaaaa"));
    }
}
