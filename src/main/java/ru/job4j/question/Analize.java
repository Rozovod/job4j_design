package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> currentMap = current.stream()
                .collect(Collectors.toMap(
                        User::getId,
                        User::getName
                ));
        for (User user : previous) {
            if (!Objects.equals(user.getName(), (currentMap.get(user.getId())))
                    && currentMap.get(user.getId()) != null) {
                changed++;
            }
            if (currentMap.get(user.getId()) == null) {
                deleted++;
            }
        }
        int added = current.size() - previous.size() + deleted;
        return new Info(added, changed, deleted);
    }
}
