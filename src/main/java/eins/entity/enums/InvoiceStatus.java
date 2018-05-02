package eins.entity.enums;

import lombok.Getter;

public enum InvoiceStatus implements MultiLanguageEnum {
    DRAFT(1, "Draft", "В очікуванні", "В ожидание"),
    CANCELED(2, "Canceled", "Скасоване", "Отменено"),
    IN_WORK(3, "In work", "В роботі", "В роботе"),
    DONE(4, "Done", "Виконане", "Исполнено");

    private Integer id;
    @Getter
    private String uaD, engD, ruD;

    InvoiceStatus(Integer id, String engD, String uaD, String ruD) {
        this.id = id;
        this.engD = engD;
        this.uaD = uaD;
        this.ruD = ruD;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
