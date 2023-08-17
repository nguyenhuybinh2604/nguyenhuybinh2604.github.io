import java.util.HashMap;
import java.util.HashSet;

// https://leetcode.com/problems/implement-magic-dictionary/submissions/1013773428/
public class MagicDictionary {

    // hashMap de nhom cac tu co cung length vao 1 set
    HashMap<Integer, HashSet<String>> lenMap;

    public MagicDictionary() {

        // khoi tao hashMap khi tao moi tu dien
        lenMap = new HashMap<>();
    }

    public void buildDict(String[] dictionary) {

        // day tung tu 1 vao hashMap voi key = do dai
        for (String s : dictionary) {
            int len = s.length();

            // neu chua co set cac tu -> add moi hashset
            if (!lenMap.containsKey(len)) lenMap.put(len, new HashSet<>());

            // add tu vao tu dien
            lenMap.get(len).add(s);
        }
    }

    public boolean search(String searchWord) {
        int len = searchWord.length();

        // kiem tra do dai truoc, neu chua co trong tu dien -> tra luon ve false
        if (!lenMap.containsKey(len)) return false;
        HashSet<String> stringSet = lenMap.get(len);

        // neu co thi duyet tung tu trong nhom do dai do
        outerLoop:
        for (String item : stringSet) {
            int count = 0;
            for (int i = 0; i < item.length(); i++) {
                if (item.charAt(i) != searchWord.charAt(i)) count++;

                // de tang toc neu gap truong hop khac nhau >1 chu cai -> duyet sang tu tiep theo
                if (count > 1) continue outerLoop;
            }

            // neu duyet xong 1 tu va chi co 1 chu cai khac nhau -> tra luon ve true
            if (count == 1) return true;
        }

        // neu duyet het va khong tim thay tu nao -> false
        return false;
    }
}
