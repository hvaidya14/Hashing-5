class Solution {
    Map<Character, List<Character>> m=null;
    int[] indegrees=null;
    public String alienOrder(String[] words) {
        StringBuilder sb =new StringBuilder("");
        m = new HashMap<>();
        Queue<Character> q = new LinkedList<>();
        indegrees = new int[26];
        int i=0;
        for(i=0;i<words.length;i++) {
            for(int j=0;j<words[i].length();j++) {
                if(!m.containsKey(words[i].charAt(j))) {
                    m.put(words[i].charAt(j), new ArrayList<>());
                }
            }
        }
        System.out.println(m);
        for(i=0;i<words.length-1;i++) {
            if(!buildgraph(words[i], words[i+1])) {
                return "";
            }
        }
        System.out.println(m);
        for(char c : m.keySet())  {
            if(indegrees[c-'a'] == 0) {
                q.add(c);
            }
        }
        if(q.isEmpty()) {
            return "";
        }
        while(!q.isEmpty()) {
            char c = q.poll();
            sb.append(c);
            if(m.get(c) != null) {
                List<Character> temp = m.get(c);
                for(char l :temp) {
                    indegrees[l-'a']--;
                    if(indegrees[l-'a'] == 0) {
                        q.add(l);
                    }
                }
            }
        }
        if(sb.length() == m.size()) {
            return sb.toString();
        } else {
            return "";
        }
    }
    private boolean buildgraph(String word1, String word2) {
        int index=0;
        for (index=0;index<word1.length() && index<word2.length();index++){
            if(word1.startsWith(word2) && word1.length() > word2.length()) {
                m.clear();
                return false;
            }
            if (word1.charAt(index) != word2.charAt(index)) {
                m.get(word1.charAt(index)).add(word2.charAt(index));
                indegrees[word2.charAt(index)-'a']++;
                break;
            }
        }
        return true;
    }
}


class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> m = new HashMap<>();
        for(int i=0;i<order.length();i++) {
            m.put(order.charAt(i), i);
        }
        System.out.println(m);
        for(int j=0;j<words.length-1;j++) {
            if (!isSorted(words[j], words[j+1], m)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSorted(String word1, String word2, Map<Character, Integer> m) {
        int index=0;
        for (index=0; index<word1.length() && index < word2.length() ; index++) {
            if (word1.charAt(index) != word2.charAt(index)) {
                System.out.println(word1.charAt(index));
                System.out.println(word2.charAt(index));
                if (m.get(word1.charAt(index)) > m.get(word2.charAt(index))) {
                    return false;
                } else {
                    return true;
                }
            }

        }
        if (word1.length() > word2.length()) {
            return false;
        }
        return true;
    }
}
