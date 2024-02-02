package com.moroshchyk.electroestimate.model;

/**
 * Перерахування, що містить шляхи до JSON-файлів з даними.
 */
public enum Path {
    USER_JSON("src/com/moroshchyk/electroestimate/repository/UserData.json"),
    ELECTRO_JSON("src/com/moroshchyk/electroestimate/repository/ElectroServiceData.json"),
    PROJECT_JSON("src/com/moroshchyk/electroestimate/repository/ProjectData.json");

    private final String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

