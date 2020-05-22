package model.entities;

import java.io.Serializable;

public class CompanyDetailList implements Serializable {

    private int numUserShares;
    private int companyId;
    private String companyName;
    private int[] shareIdOpen;
    private float[] valueOpen;
    private int[] shareIdClose;
    private float[] valueClose;
    private float[] maxValue;
    private float[] minValue;
    private int[] minutesBefore;

    public CompanyDetailList(int numCompanies) {

        this.shareIdOpen = new int[numCompanies];
        this.valueOpen = new float[numCompanies];
        this.shareIdClose = new int[numCompanies];
        this.valueClose = new float[numCompanies];
        this.maxValue = new float[numCompanies];
        this.minValue = new float[numCompanies];
        this.minutesBefore = new int[numCompanies];
    }

    public CompanyDetailList() {
    }

    public int getNumUserShares() {
        return numUserShares;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int[] getShareIdOpen() {
        return shareIdOpen;
    }

    public float[] getValueOpen() {
        return valueOpen;
    }

    public int[] getShareIdClose() {
        return shareIdClose;
    }

    public float[] getValueClose() {
        return valueClose;
    }

    public float[] getMaxValue() {
        return maxValue;
    }

    public float[] getMinValue() {
        return minValue;
    }

    public int[] getMinutesBefore() {
        return minutesBefore;
    }

    public void setNumUserShares(int numUserShares) {
        this.numUserShares = numUserShares;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setShareIdOpen(int i, int shareIdOpen) {
        this.shareIdOpen[i] = shareIdOpen;
    }

    public void setValueOpen(int i, float valueOpen) {
        this.valueOpen[i] = valueOpen;
    }

    public void setShareIdClose(int i, int shareIdClose) {
        this.shareIdClose[i] = shareIdClose;
    }

    public void setValueClose(int i, float valueClose) {
        this.valueClose[i] = valueClose;
    }

    public void setMaxValue(int i, float maxValue) {
        this.maxValue[i] = maxValue;
    }

    public void setMinValue(int i, float minValue) {
        this.minValue[i] = minValue;
    }

    public void setMinutesBefore(int i, int minutesBefore) {
        this.minutesBefore[i] = minutesBefore;
    }
}
