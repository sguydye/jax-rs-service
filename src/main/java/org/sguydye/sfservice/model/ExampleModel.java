package org.sguydye.sfservice.model;

public class ExampleModel {
    private String string;
    private Integer anInt;

    public ExampleModel() {}
    public ExampleModel(String str, int anInt) {
        this.anInt = anInt;
        this.string = str;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Integer getAnInt() {
        return anInt;
    }

    public void setAnInt(Integer anInt) {
        this.anInt = anInt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExampleModel that = (ExampleModel) o;

        if (string != null ? !string.equals(that.string) : that.string != null) return false;
        return anInt != null ? anInt.equals(that.anInt) : that.anInt == null;
    }

    @Override
    public int hashCode() {
        int result = string != null ? string.hashCode() : 0;
        result = 31 * result + (anInt != null ? anInt.hashCode() : 0);
        return result;
    }
}
