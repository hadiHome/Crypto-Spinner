package com.cryptospin.ui.models;

import com.google.gson.annotations.SerializedName;

public class Wallet {
    @SerializedName("polc")
    String polc;
    @SerializedName("dodge")
    String dodge;
    @SerializedName("ada")
    String ada;
    @SerializedName("alu")
    String alu;
    @SerializedName("xrp")
    String xrp;
    @SerializedName("ftm")
    String ftm;
    @SerializedName("usdt")
    String usdt;

    public String getAda() {
        return ada;
    }

    public String getAlu() {
        return alu;
    }

    public String getDodge() {
        return dodge;
    }

    public String getFtm() {
        return ftm;
    }

    public String getPolc() {
        return polc;
    }

    public String getUsdt() {
        return usdt;
    }

    public String getXrp() {
        return xrp;
    }

    public void setAda(String ada) {
        this.ada = ada;
    }

    public void setAlu(String alu) {
        this.alu = alu;
    }

    public void setDodge(String dodge) {
        this.dodge = dodge;
    }

    public void setFtm(String ftm) {
        this.ftm = ftm;
    }

    public void setPolc(String polc) {
        this.polc = polc;
    }

    public void setUsdt(String usdt) {
        this.usdt = usdt;
    }

    public void setXrp(String xrp) {
        this.xrp = xrp;
    }
}
