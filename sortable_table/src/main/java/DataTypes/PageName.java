package DataTypes;

public enum PageName {
    SORTABLE_DATA_TABLE("Sortable Data Tables");
    private final String name;

    PageName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    }
