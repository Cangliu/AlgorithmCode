import com.LeecodeApplication;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(classes = LeecodeApplication.class)
public class addTwoNumbers {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoListNode(l1, l2, false);
    }

    public ListNode addTwoListNode(ListNode l1, ListNode l2, boolean add){
        ListNode listNode = new ListNode();
        int val;
        int val1;
        int val2;
        val1 = l1 == null ? 0 : l1.val;
        val2 = l2 == null ? 0 : l2.val;
        if(add){
            val = val1 + val2 + 1;
        }else{
            val = val1 + val2;
        }
        if(val > 9){
            val = val % 10;
            add = true;
        }else{
            add = false;
        }
        if((l1 != null && l1.next != null) || (l2 != null && l2.next != null) || add){
            listNode = new ListNode(val, addTwoListNode(l1 == null ? null : l1.next, l2 == null ? null : l2.next, add));
        }else{
            listNode = new ListNode(val);
        }
        return listNode;
    }

    @Test
    public void test(){
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(5);
        ListNode l3 = new ListNode(4);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(6);
        ListNode l6 = new ListNode(4);
        l1.next = l3;
        l3.next = l4;
        l4.next = l5;
        l2.next = l6;
        System.out.println(addTwoNumbers(l1, l2));
    }

    @Test
    public void test2(){
        List<FeiZong> peekList = new ArrayList<>();
        FeiZong feiZong = new FeiZong();
        feiZong.setName("你是猪！");
        feiZong.setGender("girl");
        peekList.add(feiZong);
        List<FeiZong> collect = peekList.stream().peek(e -> e.setName("凑肥鯮" + e.getName())).collect(Collectors.toList());
        for (FeiZong zong : collect) {
            System.out.println(zong);
        }
    }

    public class FeiZong {
        public String name;
        public String gender;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public FeiZong() {
        }

        public FeiZong(String name, String gender) {
            this.name = name;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "FeiZong{" +
                    "name='" + name + '\'' +
                    ", gender='" + gender + '\'' +
                    '}';
        }
    }
}
