package sk.stopangin.entity;

import lombok.Data;

@Data
public class BaseIdentifiableEntity {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
