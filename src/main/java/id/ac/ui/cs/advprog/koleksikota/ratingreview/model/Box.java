package id.ac.ui.cs.advprog.koleksikota.ratingreview.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

// dummy model box
@Getter @Setter
public class Box {
    private String boxId;
    private String boxName;
    private String boxDescription;

    public Box(){
        this.boxId = UUID.randomUUID().toString();
    }
}