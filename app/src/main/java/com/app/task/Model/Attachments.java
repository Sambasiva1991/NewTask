package com.app.task.Model;

import java.io.Serializable;

public class Attachments implements Serializable {


    private int id;
    private String attachmentUrl;
    private String orgId;
    private String userId;
    private String thumbnailUrl;
    private String mimeType;
    private String updatedAt;
    private String createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }



    //            "attachments":[{
//        "id":2437,
//                "attachmentUrl":
//        "https:\/\/s3.ap-south-1.amazonaws.com\/chase-media.dev\/1629134472668-image.png",
//                "thumbnailUrl":null,
//                "mimeType":"IMAGE",
//                "updatedAt":"2021-08-16T17:21:15.126+00:00",
//                "createdAt":"2021-08-16T17:21:15.126+00:00",
//                "orgId":"orgu1",
//                "userId":"orgu1.917758838822"
//    }]

}
