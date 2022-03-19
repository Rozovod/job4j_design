package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenTitleIsResponsible() {
        RoleStore store = new RoleStore();
        store.add(new Role("Petr", "Responsible"));
        Role result = store.findById("Petr");
        assertThat(result.getTitle(), is("Responsible"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("Petr", "Responsible"));
        Role result = store.findById("Stas");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindTitleIsResponsible() {
        RoleStore store = new RoleStore();
        store.add(new Role("Petr", "Responsible"));
        store.add(new Role("Petr", "Executor"));
        Role result = store.findById("Petr");
        assertThat(result.getTitle(), is("Responsible"));
    }

    @Test
    public void whenReplaceThenTitleIsExecutor() {
        RoleStore store = new RoleStore();
        store.add(new Role("Petr", "Responsible"));
        store.replace("Petr", new Role("Petr", "Executor"));
        Role result = store.findById("Petr");
        assertThat(result.getTitle(), is("Executor"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeTitle() {
        RoleStore store = new RoleStore();
        store.add(new Role("Petr", "Responsible"));
        store.replace("Stas", new Role("Stas", "Executor"));
        Role result = store.findById("Petr");
        assertThat(result.getTitle(), is("Responsible"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("Petr", "Responsible"));
        store.delete("Petr");
        Role result = store.findById("Petr");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenTitleIsResponsible() {
        RoleStore store = new RoleStore();
        store.add(new Role("Petr", "Responsible"));
        store.delete("Stas");
        Role result = store.findById("Petr");
        assertThat(result.getTitle(), is("Responsible"));
    }
}