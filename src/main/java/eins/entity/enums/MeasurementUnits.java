package eins.entity.enums;

import lombok.Getter;

public enum MeasurementUnits implements MultiLanguageEnum {
    PCS(1, "pcs", "шт.", "шт."),
    UPS(2, "ups", "уп.", "уп."),
    KG(3, "kg", "кг", "кг");

    private Integer id;
    @Getter
    private String engD, uaD, ruD;

    MeasurementUnits(Integer id, String engD, String uaD, String ruD) {
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
