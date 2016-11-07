package com.herzen.tipcalc.fragments;


import com.herzen.tipcalc.entity.TipRecord;
/**
 * Created by herzen on 10/10/16.
 */
public interface TipHistoryListFragmentListener {
    void addToList(TipRecord record);
    void clearList();
    void initList();

}
