package com.example.projecta.domain.dto.model;



import com.example.projecta.domain.dto.entity.PeripheralP;

import java.util.List;

public class PeripheralPListModel {

    List<PeripheralP> keyboards;

    List<PeripheralP> mouses;

    List<PeripheralP> headsets;

    List<PeripheralP> monitors;

    List<PeripheralP> speakers;

    List<PeripheralP> controllers;

    List<PeripheralP> etc;

    List<PeripheralP> all;

    public List<PeripheralP> getKeyboards() {
        return keyboards;
    }

    public void setKeyboards(List<PeripheralP> keyboards) {
        this.keyboards = keyboards;
    }

    public List<PeripheralP> getMouses() {
        return mouses;
    }

    public void setMouses(List<PeripheralP> mouses) {
        this.mouses = mouses;
    }

    public List<PeripheralP> getHeadsets() {
        return headsets;
    }

    public void setHeadsets(List<PeripheralP> headsets) {
        this.headsets = headsets;
    }

    public List<PeripheralP> getMonitors() {
        return monitors;
    }

    public void setMonitors(List<PeripheralP> monitors) {
        this.monitors = monitors;
    }

    public List<PeripheralP> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<PeripheralP> speakers) {
        this.speakers = speakers;
    }

    public List<PeripheralP> getControllers() {
        return controllers;
    }

    public void setControllers(List<PeripheralP> controllers) {
        this.controllers = controllers;
    }

    public List<PeripheralP> getEtc() {
        return etc;
    }

    public void setEtc(List<PeripheralP> etc) {
        this.etc = etc;
    }

    public List<PeripheralP> getAll() {
        return all;
    }

    public void setAll(List<PeripheralP> all) {
        this.all = all;
    }
}
