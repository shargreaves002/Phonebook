import java.util.*;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import java.util.Arrays;

class Phonebook {
    private Multimap<String, String> phonebook = LinkedListMultimap.create();

    Phonebook(Map<String, List<String>> map) {
        Collection<String> keys = map.keySet();
        for (Object k : keys){
            String key = (String) k;
            List<String> values = map.get(key);
            phonebook.putAll(key, values);
        }
    }

    Phonebook() {
    }

    void add(String name, String phoneNumber){
        phonebook.put(name, phoneNumber);
    }

    void addAll(String name, String... phoneNumbers){
        phonebook.putAll(name, Arrays.asList(phoneNumbers));
    }

    void remove(String name){
        phonebook.removeAll(name);
    }

    Boolean hasEntry(String name){
        return phonebook.containsKey(name);
    }

    List<String> lookup(String name){
        return new ArrayList<>(phonebook.get(name));
    }

    String reverseLookup(String phoneNumber){
        if (phonebook.containsValue(phoneNumber)) {
            for (Object o : phonebook.keySet()) {
                String key = (String) o;
                Collection<String> values = phonebook.get(key);
                for (Object j : values){
                    String number = (String) j;
                    if (number.equals(phoneNumber)) return key;
                }
            }
        }

        return "No phone number was found.";
    }

    List<String> getAllContactNames(){
        ArrayList<String> answer = new ArrayList<>();
        for (Object o : phonebook.keySet()){
            String key = (String) o;
            answer.add(key);
        }
        return answer;
    }

    Multimap<String, String> getMap() {
        return phonebook;
    }
}
