package com.sadiafoodsuggester.inzamam.foodsuggester;

public class ValuesList {
    String  image, foodtitle, labelcarbohydrates, labelprotiens, labelfats;
    int proteins, fats, carbohydrates;

    public ValuesList() {

    }

    public ValuesList(String image, String foodtitle, String labelcarbohydrates,
                      String labelprotiens, String labelfats, int proteins, int fats, int carbohydrates) {
        this.image=image;
        this.foodtitle = foodtitle;
        this.labelcarbohydrates = labelcarbohydrates;
        this.labelprotiens = labelprotiens;
        this.labelfats = labelfats;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public String getFoodtitle() {
        return foodtitle;
    }

    public String getLabelcarbohydrates() {
        return labelcarbohydrates;
    }

    public String getLabelprotiens() {
        return labelprotiens;
    }

    public String getLabelfats() {
        return labelfats;
    }

    public int getProteins() {
        return proteins;
    }

    public int getFats() {
        return fats;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public String getImage() {
        return image;
    }
}
