package com.serenity.dao.impl;

import com.serenity.model.Song;
import com.serenity.model.SongSheet;

public interface SongDaoImpl {
    boolean addSongToSongSheet(SongSheet songSheet, Song song);

}
