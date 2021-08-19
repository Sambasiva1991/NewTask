package com.app.task.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "user")
public class PostInfo implements Serializable {

    @PrimaryKey
    private int postId;
    private int parentPostId;
    private String text;
    private String type;
    private String orgId;
    private String createdAt;

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    private String ratio;

    private ArrayList<Attachments> attachmentsArrayList;

    private int commentsCount;
    private int sharesCount;

    private String userName;
    private String name;
    private String photoUrl;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getParentPostId() {
        return parentPostId;
    }

    public void setParentPostId(int parentPostId) {
        this.parentPostId = parentPostId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public ArrayList<Attachments> getAttachmentsArrayList() {
        return attachmentsArrayList;
    }

    public void setAttachmentsArrayList(ArrayList<Attachments> attachmentsArrayList) {
        this.attachmentsArrayList = attachmentsArrayList;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getSharesCount() {
        return sharesCount;
    }

    public void setSharesCount(int sharesCount) {
        this.sharesCount = sharesCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }



//    {
//        "postId":1324,
//            "parentPostId":0,
//            "text":"T n mix iOS ",
//            "type":"IMAGE",
//            "orgId":"orgu1",
//            "createdAt":"2021-08-16T17:21:15.123+00:00",
//
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
//
//        "commentsCount":0,
//            "sharesCount":0,
//            "tags":[],
//        "follower":false,
//            "firstLike":null,
//
//            "createdBy":{
//        "userName":"orgu1.917758838822",
//                "name":"Praveen lukaku",
//                "photoUrl":
//        "https:\/\/s3.ap-south-1.amazonaws.com\/chase-media.dev\/1625671511557-file.png"
//    },
//
//        "sharedby":null,
//            "likes":0,
//            "tagscount":0,
//            "liked":false,
//            "parentCreatedAt":null,
//            "groups":[""],
//        "category":"REGULAR",
//            "ratio":"0.8",
//            "reportedAbuse":false
//    }

    }
