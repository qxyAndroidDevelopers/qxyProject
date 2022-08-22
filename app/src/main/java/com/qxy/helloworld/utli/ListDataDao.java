package com.qxy.helloworld.utli;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.qxy.helloworld.bean.ListBean;

import java.util.List;

@Dao
public interface ListDataDao {
    @Insert
    void insertAll(ListBean bean);

    @Delete
    void delete(ListBean bean);

    @Query("SELECT * FROM listData")
    List<ListBean> getAll();
}
