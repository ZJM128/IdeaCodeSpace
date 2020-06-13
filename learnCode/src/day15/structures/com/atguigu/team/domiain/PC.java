package day15.structures.com.atguigu.team.domiain;

public class PC implements Equipment {
    private String model;
    private String diaplay;

    public PC(String model, String diaplay) {
        this.model = model;
        this.diaplay = diaplay;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDiaplay() {
        return diaplay;
    }

    public void setDiaplay(String diaplay) {
        this.diaplay = diaplay;
    }

    @Override
    public String getDescription() {
        return model+diaplay;
    }
}
