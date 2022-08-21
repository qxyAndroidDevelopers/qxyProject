package com.qxy.helloword.Bean;

import java.util.List;

public class VarietyBean {
    public String active_time;
    public List<V> list;
    public static class V{
        public  final int type=3;
        public List<String> directors;
        public String hot;
        public String name;
        public String name_en;
        public String poster;
        public String release_date;
    }
}
