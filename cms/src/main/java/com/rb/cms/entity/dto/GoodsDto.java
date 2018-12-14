package com.rb.cms.entity.dto;

/**
 * @author xujiping
 * @date 2018/6/19 18:39
 */
public class GoodsDto {

    private Long id;
    private String name;
    private Integer categoryId;
    private String category;
    private Long sellerId;
    private String seller;
    private Long spu;
    private Long commentCount;
    private String banner;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Long getSpu() {
        return spu;
    }

    public void setSpu(Long spu) {
        this.spu = spu;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "GoodsDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", mallCategory='" + category + '\'' +
                ", sellerId=" + sellerId +
                ", seller='" + seller + '\'' +
                ", spu=" + spu +
                ", commentCount=" + commentCount +
                ", banner='" + banner + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
