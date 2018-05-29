package com.howietian.rxjavademo;

import java.util.ArrayList;

public class Result {

    private Classify item;
    private ArrayList<Tag> items;


    public Classify getItem() {
        return item;
    }

    public void setItem(Classify item) {
        this.item = item;
    }

    public ArrayList<Tag> getItems() {
        return items;
    }

    public void setItems(ArrayList<Tag> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Result{" +
                "item=" + item +
                ", items=" + items +
                '}';
    }

    class Classify {
        private ArrayList<Tag> lv1_tag_list;
        private ArrayList<Tag> lv2_tag_list;

        public ArrayList<Tag> getLv1_tag_list() {
            return lv1_tag_list;
        }

        public void setLv1_tag_list(ArrayList<Tag> lv1_tag_list) {
            this.lv1_tag_list = lv1_tag_list;
        }

        public ArrayList<Tag> getLv2_tag_list() {
            return lv2_tag_list;
        }

        public void setLv2_tag_list(ArrayList<Tag> lv2_tag_list) {
            this.lv2_tag_list = lv2_tag_list;
        }

        @Override
        public String toString() {
            return "item{" +
                    "lv1_tag_list=" + lv1_tag_list +
                    ", lv2_tag_list=" + lv2_tag_list +
                    '}';
        }
    }

    class Tag {
        String tag;
        double score;

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return "Tag{" +
                    "tag='" + tag + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}
