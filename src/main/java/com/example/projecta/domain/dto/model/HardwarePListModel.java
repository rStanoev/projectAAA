package com.example.projecta.domain.dto.model;

import com.example.projecta.domain.dto.entity.HardwareP;
import com.example.projecta.domain.dto.entity.PeripheralP;

import java.util.List;

public class HardwarePListModel {

    List<HardwareP> cpus;

    List<HardwareP> gpus;

    List<HardwareP> motherboards;

    List<HardwareP> hdds;

    List<HardwareP> ssds;

    List<HardwareP> powerSuplys;

    List<HardwareP> cases;

    List<HardwareP> etc;

    List<HardwareP> all;

    public List<HardwareP> getCpus() {
        return cpus;
    }

    public void setCpus(List<HardwareP> cpus) {
        this.cpus = cpus;
    }

    public List<HardwareP> getGpus() {
        return gpus;
    }

    public void setGpus(List<HardwareP> gpus) {
        this.gpus = gpus;
    }

    public List<HardwareP> getMotherboards() {
        return motherboards;
    }

    public void setMotherboards(List<HardwareP> motherboards) {
        this.motherboards = motherboards;
    }

    public List<HardwareP> getHdds() {
        return hdds;
    }

    public void setHdds(List<HardwareP> hdds) {
        this.hdds = hdds;
    }

    public List<HardwareP> getSsds() {
        return ssds;
    }

    public void setSsds(List<HardwareP> ssds) {
        this.ssds = ssds;
    }

    public List<HardwareP> getPowerSuplys() {
        return powerSuplys;
    }

    public void setPowerSuplys(List<HardwareP> powerSuplys) {
        this.powerSuplys = powerSuplys;
    }

    public List<HardwareP> getCases() {
        return cases;
    }

    public void setCases(List<HardwareP> cases) {
        this.cases = cases;
    }

    public List<HardwareP> getEtc() {
        return etc;
    }

    public void setEtc(List<HardwareP> etc) {
        this.etc = etc;
    }

    public List<HardwareP> getAll() {
        return all;
    }

    public void setAll(List<HardwareP> all) {
        this.all = all;
    }
}
