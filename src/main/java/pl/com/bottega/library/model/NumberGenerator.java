package pl.com.bottega.library.model;

import java.util.UUID;

/**
 * Created by maciek on 03.04.2017.
 */
public class NumberGenerator {

    public BookNumber generate() {
        return new BookNumber("nr-" + UUID.randomUUID().toString());
    }

}