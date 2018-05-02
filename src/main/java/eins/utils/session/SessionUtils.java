/*
 * SessionUtils
 *
 * Version 1.0-SNAPSHOT
 *
 * 29.04.18
 *
* Written by Orok.Eins
 * */

package eins.utils.session;

import eins.entity.enums.LanguageEnum;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SessionUtils {

    private static final String LANGUAGE_ATTR = "languageAttr";

    private static HttpSession session;

    @Autowired
    public SessionUtils(HttpSession httpSession) {
        session = httpSession;
    }

    public static HttpSession getSession() {
        return session;
    }

    public static LanguageEnum getCurrentLanguage() {
        if (session == null) return null;
        LanguageEnum language = (LanguageEnum) session.getAttribute(LANGUAGE_ATTR);
        if (language == null) {
            session.setAttribute(LANGUAGE_ATTR, LanguageEnum.ENG);
            return LanguageEnum.ENG;
        }
        return language;
    }

    public static void setLanguage(LanguageEnum language) {
        if (session == null) return;
        session.setAttribute(LANGUAGE_ATTR, language);
    }

}
