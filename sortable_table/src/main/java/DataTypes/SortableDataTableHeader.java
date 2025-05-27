package DataTypes;

public enum SortableDataTableHeader {
    LAST_NAME("Last Name",1),
    FIRST_NAME("First Name",2),
    EMAIL("Email",3),
    DUE("Due",4),
    WEBSITE("Web Site", 5);

    private final String header;
    private final int colIndex;
    SortableDataTableHeader(String header, int index){
        this.header = header;
        this.colIndex = index;
    }
    public String getHeader(){
        return header;
    }
    public int getColIndex(){
        return colIndex;
    }
}
