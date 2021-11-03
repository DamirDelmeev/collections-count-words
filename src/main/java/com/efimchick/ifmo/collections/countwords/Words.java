package com.efimchick.ifmo.collections.countwords;


import java.util.*;


public class Words {
    public static LinkedHashMap<String, Integer> sortByValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(comparingByValue());
        Collections.reverse(list);
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            Integer value = entry.getValue();
            if (value >= 10) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    public static <K, V extends Comparable<? super V>> Comparator<Map.Entry<K, V>> comparingByValue() {
        return new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> c1, Map.Entry<K, V> c2) {
                int i = c1.getValue().compareTo(c2.getValue());
                if (i == 0) {
                    int iForCheckKey = compareMyString((String) c1.getKey(), (String) c2.getKey());
                    if (iForCheckKey > 0) {
                        iForCheckKey = -1;
                    } else {
                        iForCheckKey = 1;
                    }
                    return iForCheckKey;
                }
                return i;
            }
        };
    }

    static int compareMyString(String s1, String s2) {
        int i = s1.compareTo(s2);
        return i;
    }

    public String countWords(List<String> lines) {
        TreeMap<String, Integer> words = getListWords(lines);
        LinkedHashMap<String, Integer> result = sortByValue(words);
        return toString(result);
    }

    private TreeMap<String, Integer> getListWords(List<String> lines) {
        TreeMap<String, Integer> words = new TreeMap<>();
        String text = String.join(",", lines);
        String textToLowerCase = text.toLowerCase(Locale.ROOT);
        String[] splitText = textToLowerCase.split("[^а-яё*a-z*]+");
        List<String> list = Arrays.asList(splitText);
        for (String word : list) {
            if ((word.length() >= 4)) {
                int result = 0;
                if (words.containsKey(word)) {
                    result = words.get(word) + 1;
                } else {
                    result = 1;
                }
                words.put(word, result);
            }
        }
        return words;
    }

    public String toString(LinkedHashMap<String, Integer> map) {
        String text = "";
        int counter = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            text = text + (entry.getKey() + " - " + entry.getValue());
            counter++;
            if (counter < map.size()) {
                text = text + "\n";
            }
        }
        return text;
    }
}
