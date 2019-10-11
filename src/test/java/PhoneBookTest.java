import com.google.common.collect.Multimap;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBookTest {

    @Test
    public void testNullaryConstructor() {
        // given
        Phonebook phoneBook = new Phonebook();

        // then
        Assert.assertTrue(phoneBook.getMap() instanceof Multimap);
    }

    @Test
    public void testNonNullaryConstructor() {
        // given
        Map<String, List<String>> dependency = new HashMap<>();

        // when
        Phonebook phoneBook = new Phonebook(dependency);

        // then
        Assert.assertEquals(dependency, phoneBook.getMap().asMap());
    }

    @Test
    public void addAllTest() {
        // given
        Phonebook phoneBook = new Phonebook();
        String name = "Sarah";
        String[] phoneNumbers = {
                "302-555-4444",
                "302-555-3333",
                "302-555-2222",
                "302-555-1111",
        };

        // when
        phoneBook.addAll(name, phoneNumbers);
        List<String> actualPhoneNumbers = phoneBook.lookup(name);

        //then
        Assert.assertEquals(Arrays.asList(phoneNumbers), actualPhoneNumbers);
    }

    @Test
    public void getAllContactNamesTest() {
        // given
        Phonebook phoneBook = new Phonebook();
        String[] names = new String[]{"John", "Joe", "Jim", "Jay"};
        for (String name : names) {
            phoneBook.add(name, "");
        }

        // when
        List<String> actualNames = phoneBook.getAllContactNames();

        // then
        Assert.assertEquals(Arrays.asList(names), actualNames);
    }

    @Test
    public void removeTest() {
        // given
        Phonebook phoneBook = new Phonebook();
        String name = "John";
        String phoneNumber = "302-555-4545";
        phoneBook.add(name, phoneNumber);
        Assert.assertTrue(phoneBook.hasEntry(name));

        // when
        phoneBook.remove(name);

        // then
        Assert.assertFalse(phoneBook.hasEntry(name));
    }

    @Test
    public void reverseLookupTest() {
        // given
        Phonebook phoneBook = new Phonebook();
        String expectedName = "John";
        String phoneNumber = "302-555-4545";
        phoneBook.add(expectedName, phoneNumber);
        Assert.assertTrue(phoneBook.hasEntry(expectedName));

        // when
        String actualName = phoneBook.reverseLookup(phoneNumber);

        // then
        Assert.assertEquals(expectedName, actualName);
    }
}
