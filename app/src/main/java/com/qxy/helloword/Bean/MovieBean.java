package com.qxy.helloword.Bean;

import java.util.List;

public class MovieBean {
    public String active_time;
    public List<M> list;

    public static class M{
        public  final int type=1;
        public List<String> actors=null;
        public List<String> areas=null;
        public List<String> directors=null;
        public String hot;
        public String name;
        public String name_en;
        public String poster;
        public List<String> tags=null;
        public String release_date;
    }
}
