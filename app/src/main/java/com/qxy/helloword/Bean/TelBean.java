package com.qxy.helloword.Bean;

import java.util.List;

public class TelBean {
    public String active_time;
    public List<S> list;
    public String error_code;

    public static class S{
        public  final int type=2;
        public List<String> actors;
        public List<String> directors;
        public String hot;
        public String name;
        public String poster;
        public String release_date;
        public String name_en;
    }
}
