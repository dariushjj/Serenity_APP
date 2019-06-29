package com.serenity.dao;

import com.serenity.dao.impl.SongSheetDaoImpl;
import com.serenity.model.SongSheet;

import org.litepal.LitePal;

import java.util.List;


public class SongSheetDao implements SongSheetDaoImpl {
    public List<SongSheet> findAllSongSheets(){
        List<SongSheet> songSheets = LitePal.where("user_id = ?",
                String.valueOf(new UserDao().getCurrentUser().getId())).find(SongSheet.class);
        return songSheets;
    }
}
