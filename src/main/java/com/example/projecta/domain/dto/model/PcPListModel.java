package com.example.projecta.domain.dto.model;

import com.example.projecta.domain.dto.entity.PcP;
import com.example.projecta.domain.dto.entity.TandCP;

import java.util.List;

public class PcPListModel {

    List<PcP> pc;

    List<PcP> laptop;

    List<PcP> console;

    List<PcP> all;

    public PcPListModel() {
    }

    public List<PcP> getPc() {
        return pc;
    }

    public void setPc(List<PcP> pc) {
        this.pc = pc;
    }

    public List<PcP> getLaptop() {
        return laptop;
    }

    public void setLaptop(List<PcP> laptop) {
        this.laptop = laptop;
    }

    public List<PcP> getConsole() {
        return console;
    }

    public void setConsole(List<PcP> console) {
        this.console = console;
    }

    public List<PcP> getAll() {
        return all;
    }

    public void setAll(List<PcP> all) {
        this.all = all;
    }
}
