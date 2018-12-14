package com.frontend.media.controller.dto;

/**
 * @author xujiping
 * @date 2018/10/20 11:22
 */
public class ImageTab {

    private Long id;

    private String url;

    private boolean cover;

    private String content;

    private int seq;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isCover() {
        return cover;
    }

    public void setCover(boolean cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return "ImageTab{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", cover=" + cover +
                ", content='" + content + '\'' +
                ", seq=" + seq +
                '}';
    }
}
