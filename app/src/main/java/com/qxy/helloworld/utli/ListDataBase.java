package com.qxy.helloworld.utli;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.qxy.helloworld.bean.ListBean;


@Database(entities = ListBean.class,version = 1)
public abstract class ListDataBase extends RoomDatabase{
    public abstract ListDataDao getListDataDao();
}
