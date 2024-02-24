package data;

public enum ColorData {
        BLACK("черный"), BROWN("коричневый"), WHITE("белый"), GRAY("серый");
    private String name;

    ColorData(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
