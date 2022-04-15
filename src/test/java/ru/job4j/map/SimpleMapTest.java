package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenMultiPutThenGetValue() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("15", "Stas");
        map.put("16", "Petr");
        assertThat(map.get("15"), is("Stas"));
        assertThat(map.get("16"), is("Petr"));
    }

    @Test
    public void whenMultiPutDoubleKeyThenFalse() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("15", "Stas");
        assertFalse(map.put("15", "Petr"));
    }

    @Test
    public void whenGetNonExistentPairThenNull() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("15", "Stas");
        Assert.assertNull(map.get("16"));
    }

    @Test
    public void whenGetNullValueThenNull() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("15", null);
        Assert.assertNull(map.get("15"));
    }

    @Test
    public void whenRemoveThenTrueAndGetNull() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("15", "Stas");
        map.put("16", "Petr");
        assertTrue(map.remove("16"));
        Assert.assertNull(map.get("16"));
    }

    @Test
    public void whenRemoveNonExistentThenFalse() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("15", "Stas");
        assertFalse(map.remove("16"));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("15", "Stas");
        Iterator<String> it = map.iterator();
        map.put("16", "Petr");
        it.next();
    }

    @Test
    public void whenCheckIterator() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("15", "Stas");
        Iterator<String> it = map.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is("15"));
    }

    @Test
    public void whenGetIteratorFromEmptyMapThenHasNextReturnFalse() {
        SimpleMap<String, String> map = new SimpleMap<>();
        Assert.assertFalse(map.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenRemoveOnePairThenIteratorNextThrowException() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("15", "Stas");
        map.remove("15");
        map.iterator().next();
    }
}