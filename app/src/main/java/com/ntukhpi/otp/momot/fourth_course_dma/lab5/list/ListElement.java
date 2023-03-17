package com.ntukhpi.otp.momot.fourth_course_dma.lab5.list;

public class ListElement implements Comparable<ListElement> {
    private String text1;
    private String text2;
    private int imageResourceId;

    public ListElement(String text1, String text2, int imageResourceId) {
        this.text1 = text1;
        this.text2 = text2;
        this.imageResourceId = imageResourceId;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    @Override
    public int compareTo(ListElement o) {
        return Character.compare(this.text2.charAt(text2.length() - 1), (o).text2.charAt(text2.length() - 1));
    }
}
