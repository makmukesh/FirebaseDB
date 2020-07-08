package com.vpipl.firebasedb;

public class ImageUploadInfo {

    public String prodid;
    public String prodname;
    public String prodmrp;
    public String proddp;
    public String imageURL;

    //public ImageUploadInfo(String url,String prodname,String prodmrp,String proddp) {
    public ImageUploadInfo(String url,String prodmrp,String proddp,String prodname,String prodid) {
        this.imageURL= url;
        this.prodid = prodid;
        this.prodname = prodname;
        this.prodmrp = prodmrp;
        this.proddp = proddp;
    }

    public String getProdid() {
        return prodid;
    }

    public String getProdname() {
        return prodname;
    }

    public String getProdmrp() {
        return prodmrp;
    }

    public String getProddp() {
        return proddp;
    }

    public String getImageURL() {
        return imageURL;
    }

}