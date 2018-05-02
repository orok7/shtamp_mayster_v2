/*
 * AbstractMultiLanguageEnum
 *
 * Version 1.0-SNAPSHOT
 *
 * 29.04.18
 *
 * All rights reserved by DoubleO Team (Team#1)
 * */

package eins.entity.enums;

import eins.utils.session.SessionUtils;

public interface MultiLanguageEnum {

    String getEngD();
    String getUaD();
    String getRuD();

    default String getDescription() {
        LanguageEnum language = SessionUtils.getCurrentLanguage();
        if (language != null) {
            switch (language) {
                case UA:
                    return getUaD();
                case RU:
                    return getRuD();
            }
        }
        return getEngD();
    }
}
