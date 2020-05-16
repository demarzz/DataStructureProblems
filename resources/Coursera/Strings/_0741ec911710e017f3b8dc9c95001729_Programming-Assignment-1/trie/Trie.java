import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Trie {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    List<Map<Character, Integer>> buildTrie(String[] patterns) {
        List<Map<Character, Integer>> trie = new ArrayList<Map<Character, Integer>>();
        int newIndex = 1;
        for(int i=0; i<patterns.length; i++){
            int currentIndex = 0;
            for(int j=0; j<patterns[i].length(); j++){
                Character value = patterns[i].charAt(j);
                if(trie.size()>currentIndex){
                    Map<Character, Integer> map = trie.get(currentIndex);
                    if(!map.containsKey(value)){
                        map.put(value, newIndex);
                        newIndex++;
                        currentIndex = newIndex;
                    } else{
                        currentIndex = map.get(value);
                    }
                } else{
                    Map<Character, Integer> temp = new HashMap<>();
                    temp.put(value, newIndex);
                    for(int t = trie.size();t<currentIndex-1;t++ ){
                        trie.add(new HashMap<>());
                    }
                    trie.add(temp);
                    newIndex++;
                    currentIndex = newIndex;
                }


            }
        }
        // write your code here

        return trie;
    }

    static public void main(String[] args) throws IOException {
        new Trie().run();
    }

    public void print(List<Map<Character, Integer>> trie) {
        for (int i = 0; i < trie.size(); ++i) {
            Map<Character, Integer> node = trie.get(i);
            for (Map.Entry<Character, Integer> entry : node.entrySet()) {
                System.out.println(i + "->" + entry.getValue() + ":" + entry.getKey());
            }
        }
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        int patternsCount = scanner.nextInt();
        String[] patterns = new String[patternsCount];
        for (int i = 0; i < patternsCount; ++i) {
            patterns[i] = scanner.next();
        }
        List<Map<Character, Integer>> trie = buildTrie(patterns);
        print(trie);
    }
}