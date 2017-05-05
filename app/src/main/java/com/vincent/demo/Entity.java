package com.vincent.demo;

import java.io.Serializable;

/**
 * description ：
 * project name：RecycleViewAddStartAndEnd
 * author : Vincent
 * creation date: 2017/5/4 22:32
 *
 * @version 1.0
 */

public class Entity implements Serializable {

    public String title;

    public String content;

    public Entity(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
