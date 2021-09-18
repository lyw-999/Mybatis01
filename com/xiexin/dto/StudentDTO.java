package com.xiexin.dto;

public class StudentDTO {
    private int s_id;
    private String s_name;
    private int avgScore;

    @Override
    public String toString() {
        return "StudentDTO{" +
                "s_id=" + s_id +
                ", s_name='" + s_name + '\'' +
                ", avgScore=" + avgScore +
                '}';
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public int getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(int avgScore) {
        this.avgScore = avgScore;
    }
}
