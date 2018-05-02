package eins.entity.enums;

import lombok.Getter;

public enum PaymentType implements MultiLanguageEnum {
    CASH(1, "cash when received in the office", "готівка при отримані в офісі", "наличные при получении в офисе"),
    CASH_ON_DELIVERY(2, "upon receipt at the post office", "при отриманні в поштовому відділенні", "при получении в почтовом отделении"),
    CASHLESS(3, "by bank account", "по перерахунку", "по перечислению");

    private Integer id;
    @Getter
    String engD, uaD, ruD;

    PaymentType(Integer id, String engD, String uaD, String ruD) {
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
