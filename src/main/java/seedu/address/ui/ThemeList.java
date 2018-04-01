package seedu.address.ui;

import java.util.HashMap;

//@@author Sisyphus25
/**
 * Provide list of themes and respective URL to their CSS stylesheet
 */
public class ThemeList {
    private HashMap<String, String> themeList;

    public ThemeList() {
        themeList = new HashMap<>();
        themeList.put("dark", "view/DarkTheme.css");
        themeList.put("light", "view/LightTheme.css");
        themeList.put("doge", "view/DogeTheme.css");
        themeList.put("galaxy", "view/GalaxyTheme.css");
    }

    public String getThemeStyleSheet(String theme) {
        if (!themeList.containsKey(theme)) {
            return themeList.get("light");
        }
        return themeList.get(theme);
    }
}
